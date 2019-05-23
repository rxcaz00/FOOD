package mx.ssmxli.food.controller;


import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.UsuarioModel;
import mx.ssmxli.food.service.UsuarioService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;

    private static final Log log = LogFactory.getLog(PromocionController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/usuario/registrarUsuario";
    }

    //Guarda usuario
    @PostMapping(value = "/addusuario", params = "action=guardar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de usuarios
    public String addUsuario(@ModelAttribute(name = "usuarioModel") UsuarioModel usuarioModel,
                           Model model) {

        log.info("Method: addGasto() -- Params: "+usuarioModel.toString());

        if(usuarioService.addUsuario(usuarioModel) != null){
            model.addAttribute("result", 1);
            //muestra un mensaje si se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }

        return "redirect:/usuario/consultaUsuario";
    }

    /* @param model
     *
     * Te redirecciona a la direccion que indica el String de retorno
     *
     * @return String
     *
     * @author Danya
     */
    @GetMapping("/consultaUsuario")
    public ModelAndView showUsuarios() {
        ModelAndView mav = new ModelAndView(ViewConstant.SHOW_USUARIO);
        mav.addObject("usuarios",usuarioService.listAllUsuarios());
        return mav;
    }



}
