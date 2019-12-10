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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/configuracion")
public class ConfiguracionController {

    @Autowired
    @Qualifier("configuracionServiceImpl")
    private ConfiguracionService configuracionService;

    private static final Log log = LogFactory.getLog(ConfiguracionController.class);

    @GetMapping("/cancel")
    /**
     * Te redirecciona a la direccion que indica el String de retorno
     *
     * @return String
     * @author Roberto
     * */
    public String cancel() {
        return "redirect:/configuracion/sistema";
    }

    @PostMapping(value = "/addConfig")
    /**
     * @param configuracionModel
     * @param model
     *
     * Método para guardar la configuración del sistema "FOOD".
     *
     * @return String
     * @author Roberto
     */
    public String addConfig(@ModelAttribute(name = "configuracionModel")ConfiguracionModel configuracionModel, Model model) {
        log.info("Method: addConfig() -- Params: " + configuracionModel.toString());
        if(configuracionService.addConfiguracion(configuracionModel) != null)
            model.addAttribute("result",1);
        else
            model.addAttribute("result",0);
        return "redirect:/configuracion/sistema";
    }

    @GetMapping(value = "/cargarConfig")
    /**
     * @param configuracionModel
     * @param model
     *
     * Método para cargar la última configuración del sistema "FOOD".
     *
     * @return String
     * @author Roberto
     */
    public String load(@ModelAttribute(name = "configuracionModel")ConfiguracionModel configuracionModel, Model model) {
        configuracionModel = configuracionService.findLastConfiguracion();
        configuracionModel.setId(configuracionModel.getId() + 1);
        model.addAttribute("configuracionModel",configuracionModel);
        return ViewConstant.CONFIG_SISTEMA;
    }

    @GetMapping("/sistema")
    /**
     * @param model
     *
     * Método que carga la información a mostrar en la vista
     *
     * @return String
     * @author Roberto
     */
    public String sistema(Model model) {
        ConfiguracionModel configuracionModel = new ConfiguracionModel();
        model.addAttribute("configuracionModel",configuracionModel);
        return ViewConstant.CONFIG_SISTEMA;
    }
}
