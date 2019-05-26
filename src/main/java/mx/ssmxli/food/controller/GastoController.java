package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.GastoModel;
import mx.ssmxli.food.service.GastoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/gasto")
public class GastoController {
    @Autowired
    @Qualifier("gastoServiceImpl")
    private GastoService gastoService;

    private static final Log log = LogFactory.getLog(PromocionController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/gasto/muestraGasto";
    }


    //Guarda los gastos
    @PostMapping("/addgasto")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de gastos
    public String addGasto(@ModelAttribute(name = "gastoModel") GastoModel gastoModel,
                               Model model) throws Exception {

        log.info("Method: addGasto() -- Params: "+gastoModel.toString());

        if(gastoService.addGasto(gastoModel) != null){
            model.addAttribute("result", 1);
            //muestra un mensaje si se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }

        return "redirect:/gasto/muestraGasto";
    }
 /* @param model
     *
             * Te redirecciona a la direccion que indica el String de retorno
     *
             * @return String
     *
             * @author Danya
*/
    @GetMapping("/muestraGasto")
    public String sistema(Model model){
        GastoModel gastoModel = new GastoModel();
        model.addAttribute("gastoModel", gastoModel);
        return ViewConstant.GASTO_FORM;
    }
}