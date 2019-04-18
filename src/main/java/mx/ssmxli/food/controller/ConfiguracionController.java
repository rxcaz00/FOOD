package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.ConfiguracionModel;
import mx.ssmxli.food.service.ConfiguracionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/configuracion")
public class ConfiguracionController {

    @Autowired
    @Qualifier("configuracionService")
    private ConfiguracionService configuracionService;

    private static final Log log = LogFactory.getLog(ConfiguracionController.class);

    @GetMapping("/cancel")
    public String cancel() {
        return "redirect:/configuracion/sistema";
    }

    @GetMapping("/addConfig")
    public String addConfig(@ModelAttribute(name = "configuracionModel")ConfiguracionModel configuracionModel, Model model) {
        log.info("Method: addConfig() -- Params: " + configuracionModel.toString());
        if(configuracionService.addConfiguracion(configuracionModel) != null)
            model.addAttribute("result",1);
        else
            model.addAttribute("result",0);
        return "redirect:/configuracion/sistema";
    }

    @GetMapping("/sistema")
    public String sistema() {
        return ViewConstant.CONFIG_SISTEMA;
    }
}
