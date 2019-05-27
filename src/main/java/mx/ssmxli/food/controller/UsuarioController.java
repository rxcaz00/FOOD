package mx.ssmxli.food.controller;


import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.UsuarioModel;
import mx.ssmxli.food.service.SecurityService;
import mx.ssmxli.food.service.UsuarioService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;

    @Autowired
    @Qualifier("securityServiceImpl")
    private SecurityService securityService;

    private static final Log log = LogFactory.getLog(PromocionController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/usuarios/" + ViewConstant.SHOW_USUARIO;
    }

    @GetMapping("/registrarUsuario")
    /**
     * @param Model
     *
     * Regresa una instancia del UsuarioModel para ser utilizado en la vista
     *
     * @return String regresa la vista
     *
     * @author Roberto
     */
    public String inicio(Model model) {
        UsuarioModel usuarioModel = new UsuarioModel();
        model.addAttribute("usuarioModel", usuarioModel);
        return ViewConstant.USUARIO_NEW;
    }

    //Guarda usuario
    @PostMapping(value = "/addUsuario")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de usuarios
    public String addUsuario(@ModelAttribute(name = "usuarioModel") UsuarioModel usuarioModel,
                           Model model) {

        log.info("Method: addUsuario() -- Params: "+usuarioModel.toString());

        if(usuarioService.addUsuario(usuarioModel) != null){
            model.addAttribute("result", 1);
            //muestra un mensaje si se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }

        return "redirect:/usuarios/consultaUsuarios";
    }

     /**
     *
     * Te redirecciona a la direccion que indica el String de retorno
     *
     * @return String
     *
     * @author Danya
     */
    @GetMapping("/consultaUsuarios")
    public ModelAndView showUsuarios() {
        ModelAndView mav = new ModelAndView(ViewConstant.SHOW_USUARIO);
        mav.addObject("currentUser", securityService.findLoggedInUsername());
        mav.addObject("usuarios",usuarioService.listAllUsuarios());
        return mav;
    }
    @GetMapping("/modificarUsuario")
    /**
     * @param Model
     * @param int (@RequestParam, required = false)
     *
     * Modifica el usuario en base al ID.
     * Regresa un ModelAndView, donde la vista es la constante de ViewConstant y el modelo es una lista de todos los usuarios.
     *
     *
     * @return ModelAndView
     *
     * @author Diana
     * */
    public String redirectModificarUsuario(Model model,
                                           @RequestParam(name = "usuario", required = false) String usuario){
        UsuarioModel usuarioModel = new UsuarioModel();
        if(usuario!= null){
            usuarioModel = usuarioService.findUsuarioByUsuarioModel(usuario);
        }
        model.addAttribute("usuarioModel", usuarioModel);
        return ViewConstant.USUARIO_UPDATE;
    }


}
