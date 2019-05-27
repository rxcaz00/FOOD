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
public class LoginController extends Thread {
    @Autowired
    @Qualifier("securityServiceImpl")
    private SecurityService securityService;

    private int c = 0;

    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        UsuarioModel usuarioModel = new UsuarioModel();
        model.addAttribute("usuarioModel",usuarioModel);
        if (error != null) {
            c++;
            if(c >= 3) {
                model.addAttribute("error", "Bloqueado");
                run();
                c = 0;
            }
            else
                model.addAttribute("error", "Usuario o contraseña incorrecto");
        }

        if (logout != null)
            model.addAttribute("message", "Has cerrado sesión");

        return ViewConstant.LOGIN;
    }

}


