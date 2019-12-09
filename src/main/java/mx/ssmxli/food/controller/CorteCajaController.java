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
     * @author Diana, Roberto
     */
    public String inicio(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") int id){
        CorteCajaModel corteCajaModel = new CorteCajaModel();

        if(id == 0) {
            ConfiguracionModel configuracionModel = configuracionService.findLastConfiguracion();
            corteCajaModel.setDineroInicial(configuracionModel.getDineroInicial());
            corteCajaModel.setEfectivo(ventaService.getTotal('E'));
            corteCajaModel.setTarjeta(ventaService.getTotal('T'));
            corteCajaModel.setCompra(gastoService.getTotal('C'));
            corteCajaModel.setPago(gastoService.getTotal('P'));

            model.addAttribute("corteCajaModel", corteCajaModel);

            return ViewConstant.CORTE_CAJA;
        }
        else {
            corteCajaModel = corteCajaService.findCorteCajaByIdModel(id);

            model.addAttribute("corteCajaModel", corteCajaModel);

            return ViewConstant.EDITAR_CORTE;
        }
    }

    @GetMapping("/historial")
    /**
     * Regresa un ModelAndView, donde la vista es la constante de ViewConstant y el modelo es una lista de todos los
     * cortes de cajas
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
    public String addCorteCaja(@ModelAttribute(name = "corteCajaModel")CorteCajaModel corteCajaModel, Model model) throws Exception {
        log.info("Method: addCorteCaja() -- Params: " + corteCajaModel.toString());
        if (corteCajaService.addCorteCaja(corteCajaModel) != null) {
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        } else {
            model.addAttribute("result", 0);
        }
        return "redirect:/corteCaja/historial";
    }

    /**
     *
     * @param model
     * @param id
     * @return
     * @author Roberto
     */
    @GetMapping("/consultaCorteCaja")
    public ModelAndView consultaCorteCaja(Model model, @RequestParam(name = "id", required = false) int id) {
        ModelAndView mav = new ModelAndView(ViewConstant.CONSULTA_CORTE);

        CorteCajaModel corteCajaModel = corteCajaService.findCorteCajaByIdModel(id);

        model.addAttribute("corte", corteCajaModel);

        return mav;
    }

    @GetMapping("/aceptarCorte")
    public String aceptarCorteCaja(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") int id) throws Exception {
        CorteCajaModel corteCajaModel = corteCajaService.findCorteCajaByIdModel(id);
        if (corteCajaService.addCorteCaja(corteCajaModel) != null) {
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        } else {
            model.addAttribute("result", 0);
        }

        return "redirect:/corteCaja/historial";
    }

}
