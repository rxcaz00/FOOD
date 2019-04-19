package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.AlimentoModel;
import mx.ssmxli.food.service.AlimentoService;
import mx.ssmxli.food.service.IdManagerService;
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
@RequestMapping("/alimentos")
public class AlimentoController {

    @Autowired
    @Qualifier("alimentoServiceImpl")
    private AlimentoService alimentoService;

    @Autowired
    @Qualifier("idManagerServiceImpl")
    private IdManagerService idManagerService;

    private static final Log log = LogFactory.getLog(AlimentoController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/alimentos/showAlimento";
    }

    @GetMapping("/registrarAlimento")
    public String RedirectRegistrarAlimento(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") int id){
        AlimentoModel alimentoModel = new AlimentoModel();
        if(id != 0){
            alimentoModel = alimentoService.findAlimentoByIdModel(id);
        }
        model.addAttribute("alimentoModel", alimentoModel);
        return ViewConstant.ALIMENTO_NEW;
    }

    @PostMapping("/addAlimento")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de alimentoform
    public String addAlimento(@ModelAttribute(name = "alimentoModel")AlimentoModel alimentoModel,
                             Model model){
        log.info("Method: addAlimento() -- Params: "+alimentoModel.toString());
        //Crear el ID en base a la categoria, nombre y tamaño
        alimentoModel.setId(idManagerService.createID(alimentoModel.getCategoria(),alimentoModel.getNombre(),alimentoModel.getTamano()));
        if(alimentoService.addAlimento(alimentoModel) != null)
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        else
            model.addAttribute("result", 0);
        return "redirect:/alimentos/showAlimento";
    }

    @GetMapping("/showAlimento")
    public ModelAndView showAlimento(){
        ModelAndView mav = new ModelAndView(ViewConstant.VENTA);
        mav.addObject("alimento", alimentoService.listAllAlimentos());
        return mav;
    }

    @GetMapping("/removeAlimento")
    public ModelAndView removeAlimento(@RequestParam(name = "id", required = true) int id){
        alimentoService.removeAlimento(id);
        return showAlimento();
    }
}