package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String inicio(Model model) {
        return ViewConstant.LOGIN;
    }
}
