package mx.ssmxli.food.controller;


import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.PrecioModel;
import mx.ssmxli.food.service.PrecioService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/precio")
public class PrecioController {

    @Autowired
    @Qualifier("precioServiceImpl")
    private PrecioService precioService;



    private static final Log log = LogFactory.getLog(PrecioController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/precio/showPrecio";
    }

    @GetMapping("/precioForm")
    public String redirectPrecioForm(Model model,
                                     @RequestParam(name = "id", required = false) int id){
        PrecioModel precioModel = new PrecioModel();
        if(id != 0){
            precioModel = precioService.findPrecioByIdModel(id);
        }
        model.addAttribute("precioModel", precioModel);
        return ViewConstant.PRECIO_FORM;
    }

    @PostMapping("/addprecio")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de precioform
    public String addContact(@ModelAttribute(name = "precioModel")PrecioModel precioModel,
                             Model model){
        log.info("Method: addPrecio() -- Params: "+precioModel.toString());
        if(precioService.addPrecio(precioModel) != null){
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }
        return "redirect:/precio/showPrecio";
    }

    @GetMapping("/showPrecio")
    public ModelAndView showPrecio(){
        ModelAndView mav = new ModelAndView(ViewConstant.PRECIO);
        mav.addObject("precio", precioService.listAllPrecios());
        return mav;
    }

    @GetMapping("/removePrecio")
    public ModelAndView removePrecio(@RequestParam(name = "id", required = true) int id){
        precioService.removePrecio(id);
        return showPrecio();
    }


}
