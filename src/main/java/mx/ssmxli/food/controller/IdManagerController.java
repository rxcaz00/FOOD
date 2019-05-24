package mx.ssmxli.food.controller;

import mx.ssmxli.food.component.IdManagerConverter;
import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.service.IdManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/idManager")
public class IdManagerController {
    @Autowired
    @Qualifier("idManagerServiceImpl")
    private IdManagerService idManagerService;

    @Autowired
    @Qualifier("idManagerConverter")
    private IdManagerConverter idManagerConverter;

    //NOMBRES

    /**
     * Regresa un ModelAndView con la constante de consulta de nombre y un objeto con todos los NombreSequence registrados
     *
     * @return ModelAndView
     * @author Andrés
     * */
    @GetMapping("/showNombres")
    public ModelAndView showNombres(){
        ModelAndView mav = new ModelAndView(ViewConstant.ID_SHOW_NOMBRE);
        mav.addObject("nombres", idManagerService.listAllNombre());
        return mav;
    }

    //CATEGORIAS

    //TAMAÑOS
}
