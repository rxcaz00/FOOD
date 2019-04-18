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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gasto")
public class GastoController {
    @Autowired
    @Qualifier("gastoServiceImpl")
    private GastoService gastoService;


    private static final Log log = LogFactory.getLog(PromocionController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/gasto/showGasto";
    }

    @GetMapping("/gastoForm")
    public String redirectGastonForm(Model model,
                                     @RequestParam(name = "id", required = false) int id){
        GastoModel gastoModel = new GastoModel();
        if(id != 0){
            gastoModel = gastoService.findGastoByIdModel(id);
        }
        model.addAttribute("gastoModel", gastoModel);
        return ViewConstant.GASTO_FORM;
    }

    @PostMapping("/addgasto")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de contactform
    public String addGasto(@ModelAttribute(name = "gastoModel")GastoModel gastoModel,
                               Model model) throws Exception {
        log.info("Method: addGasto() -- Params: "+gastoModel.toString());
        if(gastoService.addGasto(gastoModel) != null){
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }
        return "redirect:/gasto/showGasto";
    }

    @GetMapping("/showAGasto")
    public ModelAndView showGasto(){
        ModelAndView mav = new ModelAndView(ViewConstant.GASTO_FORM);
        mav.addObject("gasto", gastoService.listAllGastos());
        return mav;
    }
}