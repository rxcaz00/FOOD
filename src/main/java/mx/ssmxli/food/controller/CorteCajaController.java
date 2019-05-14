package mx.ssmxli.food.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.entity.Usuario;
import mx.ssmxli.food.model.ConfiguracionModel;
import mx.ssmxli.food.model.CorteCajaModel;
import mx.ssmxli.food.service.CorteCajaService;
import mx.ssmxli.food.service.ConfiguracionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        model.addAttribute("corteCajaModel",corteCajaModel);

        return ViewConstant.CORTE_CAJA;
    }

    @GetMapping("/historial")
    /**
     * Regresa un ModelAndView, donde la vista es la constante de ViewConstant y el modelo es una lista de todos los
     * cortes de cajas.
     * @return ModelAndView
     * @author Roberto
     * */
    public ModelAndView showHistorial() {
        ModelAndView mav = new ModelAndView(ViewConstant.CORTE_CAJA_HISTORIAL);
        mav.addObject("cortes",corteCajaService.listAllCorteCajas());
        return mav;
    }

    //Guarda los registros
    @PostMapping(value = "/addcorteCaja", params = "action=guardar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de CorteCaja
    public String addCorteCaja(@ModelAttribute(name = "corteCajaModel")CorteCajaModel corteCajaModel,
                               Model model) throws Exception {
        log.info("Method: addCorteCaja() -- Params: "+corteCajaModel.toString());
        if(corteCajaService.addCorteCaja(corteCajaModel) != null){
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }
        return "redirect:/corteCaja/actual";
    }

    @GetMapping("/createPDF")
    public String createPDF(/*Model model, @RequestParam(name = "id", required = false) int id*/) {
        /*
        CorteCajaModel corteCajaModel = new CorteCajaModel();
        if(id != 0) {
            corteCajaModel = corteCajaService.findCorteCajaByIdModel(id);
        }
        */
        try {
            FileOutputStream archivo;
            File file = new File("C:\\Users\\admin\\IdeaProjects\\FOOD\\src\\main\\resources\\static\\pdf\\reporte.pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("C:\\Users\\admin\\IdeaProjects\\FOOD\\src\\main\\resources\\static\\imgs\\logo.jpg");
            img.setAlignment(Element.ALIGN_LEFT);
            img.scaleToFit(100,100);
            doc.add(img);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        } catch (FileNotFoundException e) {

        } catch (DocumentException e) {

        } catch (IOException e) {

        }
        return ViewConstant.CORTE_CAJA_HISTORIAL;
    }

}
