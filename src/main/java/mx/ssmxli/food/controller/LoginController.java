package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.UsuarioModel;
import mx.ssmxli.food.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    @Qualifier("securityServiceImpl")
    private SecurityService securityService;

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        UsuarioModel usuarioModel = new UsuarioModel();
        model.addAttribute("usarioModel",usuarioModel);

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return ViewConstant.LOGIN;
    }

}


