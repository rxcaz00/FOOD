package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.PromocionModel;
import mx.ssmxli.food.service.PromocionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/promociones")
public class PromocionController {

    @Autowired
    @Qualifier("promocionServiceImpl")
    private PromocionService promocionService;


    private static final Log log = LogFactory.getLog(PromocionController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/promocion/showPromocion";
    }

    @GetMapping("/promocionForm")
    public String redirectPromocionForm(Model model,
                                      @RequestParam(name = "id", required = false) int id){
        PromocionModel promocionModel = new PromocionModel();
        if(id != 0){
            promocionModel = promocionService.findPromocionByIdModel(id);
        }
        model.addAttribute("promocionModel", promocionModel);
        return ViewConstant.PROMOCION_FORM;
    }

    @PostMapping("/addpromocion")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de contactform
    public String addPromocion(@ModelAttribute(name = "promocionModel")PromocionModel promocionModel,
                              Model model) throws Exception {
        log.info("Method: addPromocion() -- Params: "+promocionModel.toString());
        if(promocionService.addPromocion(promocionModel) != null){
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }
        return "redirect:/promocion/showPromocion";
    }

    @GetMapping("/showPromociones")
    public ModelAndView showPromocion(){
        ModelAndView mav = new ModelAndView(ViewConstant.PROMOCION);
        mav.addObject("promocion", promocionService.listAllPromociones());
        return mav;
    }

    @GetMapping("/removePromocion")
    public ModelAndView removePromocion(@RequestParam(name = "id", required = true) int id){
        promocionService.removePromocion(id);
        return showPromocion();
    }


}


