package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.entity.Usuario;
import mx.ssmxli.food.model.CorteCajaModel;
import mx.ssmxli.food.service.CorteCajaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/corteCaja")
public class CorteCajaController {

    @Autowired
    @Qualifier("corteCajaServiceImpl")
    private CorteCajaService corteCajaService;


    private static final Log log = LogFactory.getLog(CorteCajaController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/corteCaja/actual";
    }

    @GetMapping("/actual")
    public String inicio(Model model){
        CorteCajaModel corteCajaModel = new CorteCajaModel();
        model.addAttribute("corteCajaModel",corteCajaModel);
        return ViewConstant.CORTE_CAJA;
    }


    @PostMapping(value = "/addcorteCaja", params = "action=guardar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de CorteCaja
    public String addCorteCaja(@ModelAttribute(name = "corteCajaModel")CorteCajaModel corteCajaModel,
                               Model model) throws Exception {
        log.info("Method: addCorteCaja() -- Params: "+corteCajaModel.toString());
        Usuario recibo = new Usuario();
        recibo.setId(1);
        recibo.setNivel("gerente");
        recibo.setPassword("1111");
        recibo.setUsuario("prueba");
        if(corteCajaService.addCorteCaja(corteCajaModel) != null){
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }
        return "redirect:/corteCaja/actual";
    }



}
