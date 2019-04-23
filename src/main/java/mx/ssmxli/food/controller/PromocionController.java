package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.model.AlimentoModel;
import mx.ssmxli.food.model.PromocionModel;
import mx.ssmxli.food.service.AlimentoService;
import mx.ssmxli.food.service.PromocionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/promociones")
public class PromocionController {

    @Autowired
    @Qualifier("promocionServiceImpl")
    private PromocionService promocionService;

    @Autowired
    @Qualifier("alimentoServiceImpl")
    private AlimentoService alimentoService;


    private static final Log log = LogFactory.getLog(PromocionController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/promociones/registrarPromocion";
    }

    @GetMapping("/registrarPromocion")
    public String inicio(Model model){
        PromocionModel promocionModel = new PromocionModel();
        List<AlimentoModel> alimentoModels = alimentoService.listAllAlimentos();
        model.addAttribute("promocionModel",promocionModel);
        model.addAttribute("alimentoModels", alimentoModels);
        return ViewConstant.PROMOCION_NEW;
    }

    //Guarda los registros
    @PostMapping(value = "/addpromocion", params = "action=Guardar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de registrarPromocion
    public String addPromocion(@ModelAttribute(name = "promocionModel")PromocionModel promocionModel,
                              Model model) throws Exception {
        log.info("Method: addPromocion() -- Params: "+promocionModel.toString());
        if(promocionService.addPromocion(promocionModel) != null){
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }
        return "redirect:/promociones/registrarPromocion";
    }


}