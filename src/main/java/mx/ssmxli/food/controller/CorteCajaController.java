package mx.ssmxli.food.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.ConfiguracionModel;
import mx.ssmxli.food.model.CorteCajaModel;
import mx.ssmxli.food.model.PdfModel;
import mx.ssmxli.food.service.CorteCajaService;
import mx.ssmxli.food.service.ConfiguracionService;
import mx.ssmxli.food.service.GastoService;
import mx.ssmxli.food.service.VentaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/corteCaja")
public class CorteCajaController {

    @Autowired
    @Qualifier("corteCajaServiceImpl")
    private CorteCajaService corteCajaService;

    @Autowired
    @Qualifier("configuracionServiceImpl")
    private ConfiguracionService configuracionService;

    @Autowired
    @Qualifier("gastoServiceImpl")
    private GastoService gastoService;

    @Autowired
    @Qualifier("ventaServiceImpl")
    private VentaService ventaService;

    private static final Log log = LogFactory.getLog(CorteCajaController.class);

    @GetMapping("/cancel")
    /**
     * Te redirecciona a la direccion que indica el String de retorno
     *
     * @return String
     * @author Diana
     * */
    public String cancel(){
        return "redirect:/corteCaja/actual";
    }

    @GetMapping("/actual")
    /**
     * @param model
     *
     * Método que carga la información a mostrar en la vista
     *
     * @return String
     * @author Diana
     */
    public String inicio(Model model){
        CorteCajaModel corteCajaModel = new CorteCajaModel();
        ConfiguracionModel configuracionModel = configuracionService.findLastConfiguracion();
        corteCajaModel.setDineroInicial(configuracionModel.getDineroInicial());
        corteCajaModel.setEfectivo(ventaService.getTotal('E'));
        corteCajaModel.setTarjeta(ventaService.getTotal('T'));
        corteCajaModel.setCompra(gastoService.getTotal('C'));
        corteCajaModel.setPago(gastoService.getTotal('P'));
        model.addAttribute("corteCajaModel",corteCajaModel);

        return ViewConstant.CORTE_CAJA;
    }

    @GetMapping("/historial")
    /**
     * Regresa un ModelAndView, donde la vista es la constante de ViewConstant y el modelo es una lista de todos los
     * cortes de cajas, ademas de los pdf's de cada corte.
     * @return ModelAndView
     * @author Roberto
     * */
    public ModelAndView showHistorial() {
        ModelAndView mav = new ModelAndView(ViewConstant.CORTE_CAJA_HISTORIAL);
        mav.addObject("cortes",corteCajaService.listAllCorteCajas());
        mav.addObject("pdfs",createPDF(corteCajaService.listAllCorteCajas()));
        return mav;
    }

    //Guarda los registros
    @PostMapping(value = "/addcorteCaja", params = "action=guardar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de CorteCaja
    public String addCorteCaja(@ModelAttribute(name = "corteCajaModel")CorteCajaModel corteCajaModel, Model model) throws Exception {
        log.info("Method: addCorteCaja() -- Params: "+corteCajaModel.toString());
        if(corteCajaService.addCorteCaja(corteCajaModel) != null){
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }
        return "redirect:/corteCaja/historial";
    }

    /**
     *
     * Método que genera archivos PDF's individuales con la información del corte de caja
     * @param cortes Recibe todos los cortes de caja
     * @return Regresa una lista con las rutas de los PDF's
     *
     * @author Roberto
     */
    public List<PdfModel> createPDF(List<CorteCajaModel> cortes) {
        log.info("Method: createPDF() -- Inicio");

        List<PdfModel> pdfs = new ArrayList();

        //Declarando las tablas para llenar la información
        PdfPTable header; //Encabezado
        PdfPTable table; //Contenido
        PdfPTable footer; //Pie de página
        PdfPCell cell; //Declarando la celda que se llenará con la información
        float[] columnWidths = {1, 1}; //Ancho de las columnas de la tabla.

        //Se utiliza para delimitar el número de decimales que se mostrarán
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        try {
            //Se asigna el ancho de las columnas de la tabla

            //Se instancia la imagen del logo de la empresa
            Image img = Image.getInstance("src\\main\\resources\\static\\imgs\\logo.jpg");
            img.scaleToFit(100,100); //Se le agrega dimensión
            img.setAlignment(Element.ALIGN_LEFT); //Se le agrega alineación

            //Se crean fuentes para dar formato al contenido
            Font font = FontFactory.getFont(FontFactory.HELVETICA,16);
            Font titles = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);

            Chunk texto; //Se crea una entidad Chunk que servirá para insertar texto

            //Se crea una variable para poner texto en el encabezado del documento
            Paragraph nombre;

            //Empieza el ciclo que por cada corte de caja registrado llenará los datos
            for(CorteCajaModel corte : cortes) {
                header = new PdfPTable(2);
                table = new PdfPTable(2);
                footer = new PdfPTable(2);

                header.setWidths(columnWidths);
                table.setWidths(columnWidths);
                footer.setWidths(columnWidths);

                header.setWidthPercentage(100); //Al encabezado se le agrega el ancho de acuerdo al tamaño de la página
                header.setSpacingAfter(0); //Se agrega el espacio que habrá de separación despues del elemento

                //A la tabla de contenido se le agregan propiedades
                table.setWidthPercentage(70); //Procentaje de ancho
                table.setSpacingBefore(5); //Espacio antes del elemento
                table.setSpacingAfter(5); //Espacio después del elemento

                footer.setWidthPercentage(100); //Al pie de página se le agrega el ancho de la página
                footer.setSpacingAfter(0);
                footer.setSpacingBefore(0);

                //Se instancía el documento
                Document doc = new Document();
                doc.setMargins(15, 15, 15, 15); //Se declaran los margenes
                doc.setPageSize(new Rectangle(612,396)); //Se declara el tamaño

                //Se crea el documento con el nombre "Corte0 + 'id del corte'.pdf"
                PdfWriter.getInstance(doc, new FileOutputStream("src\\main\\resources\\static\\pdf\\Corte0" + corte.getId() + ".pdf"));

                cell = new PdfPCell(); //Se instancía la celda
                cell.setBorder(0); //Se elimina el borde de la celda
                cell.setPaddingLeft(10); //La separación entre el borde y el texto
                cell.setHorizontalAlignment(Element.ALIGN_LEFT); //Se alinea horizontalmente
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //Se alinea verticalmente
                cell.addElement(img); //Se agrega la imagen
                header.addCell(cell); //Se agrega la celda al encabezado

                nombre = new Paragraph(20); //Se instancia la variable para el texto con separación entre renglones de 20
                nombre.setAlignment(Element.ALIGN_RIGHT); //Se alinea el texto
                nombre.setFont(FontFactory.getFont(FontFactory.HELVETICA,30)); //Se agrega la fuente
                nombre.add("La Pizzería MX\n"); //Se agrega texto
                nombre.setFont(FontFactory.getFont(FontFactory.HELVETICA,20));
                nombre.add("Corte de caja: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

                cell = new PdfPCell(); //Se instancía la celda
                cell.setBorder(0); //Se elimina el borde de la celda
                cell.setPaddingLeft(10); //La separación entre el borde y el texto
                cell.setHorizontalAlignment(Element.ALIGN_LEFT); //Se alinea horizontalmente
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //Se alinea verticalmente
                cell.addElement(nombre); //Se agrega el párrafo a la variable celda
                header.addCell(cell); //Se agrega al encabezado

                //Agregando titulo de "Dinero inicial"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph("Dinero inicial: ",titles));
                table.addCell(cell); //Se agrega a la tabla de contenido

                //Agregando valores de "Dinero inicial"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph(df.format(corte.getDineroInicial()) + "", font));
                table.addCell(cell); //Se agrega a la tabla de contenido


                //Agregando titulo de "Corte"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph("Corte:",titles));
                table.addCell(cell); //Se agrega a la tabla de contenido

                //Agregando valores de "Corte"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph(df.format(corte.getCorte()) + "", font));
                table.addCell(cell); //Se agrega a la tabla de contenido


                //Agregando titulo de "Efectivo"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph("Efectivo:",titles));
                table.addCell(cell); //Se agrega a la tabla de contenido

                //Agregando valores de "Efectivo"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph(df.format(corte.getEfectivo()) + "", font));
                table.addCell(cell); //Se agrega a la tabla de contenido


                //Agregando titulo de "Tarjeta"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph("Tarjeta:",titles));
                table.addCell(cell); //Se agrega a la tabla de contenido

                //Agregando valores de "Tarjeta"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph(df.format(corte.getTarjeta()) + "", font));
                table.addCell(cell); //Se agrega a la tabla de contenido


                //Agregando titulo de "Pagos"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph("Pagos:",titles));
                table.addCell(cell); //Se agrega a la tabla de contenido

                //Agregando valores de "Pagos"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph(df.format(corte.getPago()) + "", font));
                table.addCell(cell); //Se agrega a la tabla de contenido


                //Agregando titulo de "Compras"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph("Compras:",titles));
                table.addCell(cell); //Se agrega a la tabla de contenido

                //Agregando valores de "Compras"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph(df.format(corte.getCompra()) + "", font));
                table.addCell(cell); //Se agrega a la tabla de contenido


                //Agregando titulo de "Diferencia"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph("Diferencia:",titles));
                table.addCell(cell); //Se agrega a la tabla de contenido

                //Agregando valores de "Diferencia"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph(df.format(corte.getDiferencia()) + "", font));
                table.addCell(cell); //Se agrega a la tabla de contenido

                //Agregando titulo y valores de "Elaboro"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph("Elaborado por: " + corte.getElaboro(), titles));
                footer.addCell(cell); //Se agrega al pie de página

                //Agregando titulo y valores de "Reviso"
                cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(new Paragraph("Revisado por: " + corte.getReviso(), titles));
                footer.addCell(cell); //Se agrega al pie de página

                doc.open(); //Abriendo el documento
                doc.add(header); //Agregando el encabezado
                doc.add(table); //Agregando tabla de contenido
                doc.add(footer); //Agregando pie de página
                doc.close(); //Cerrando documento

                //Se agrega una instancia de PdfModel que servirá
                //para mandar la información y acceder al archivo.
                PdfModel pdf = new PdfModel();
                pdf.setId(corte.getId());
                pdf.setRuta("/pdf/Corte0" + corte.getId() + ".pdf");
                pdfs.add(pdf);
            }
        } catch (FileNotFoundException e) {
            log.error("Method: createPDF() -- File Not Found Exception");
        } catch (DocumentException e) {
            log.error("Method: createPDF() -- Document Exception");
        } catch (IOException e) {
            log.error("Method: createPDF() -- IO Exception");
        }// catch (URISyntaxException e) {

        //}
        log.info("Method: createPDF() -- Fin");
        return pdfs;
    }

}
