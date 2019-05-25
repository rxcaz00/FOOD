package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.model.AlimentoModel;
import mx.ssmxli.food.model.PromocionModel;
import mx.ssmxli.food.service.AlimentoService;
import mx.ssmxli.food.service.PromocionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/promociones")
public class PromocionController {

    @Autowired
    @Qualifier("promocionServiceImpl")
    private PromocionService promocionService;

    @Autowired
    @Qualifier("alimentoServiceImpl")
    private AlimentoService alimentoService;

    private Set<Alimento> alimentos;
    private static final Log log = LogFactory.getLog(PromocionController.class);

    @GetMapping("/cancel")
    /**
     * Te redirecciona a la direccion que indica el String de retorno
     *
     * @return String
     * @author Diana
     * */
    public String cancel(){
        return "redirect:/promociones/registrarPromocion";
    }

    @GetMapping("/registrarPromocion")
    /**
     * @param model
     *
     * Método que carga la información a mostrar en la vista
     *
     * @return String
     * @author Diana
     */
    public String inicio(Model model){
        PromocionModel promocionModel = new PromocionModel();
        alimentos = new HashSet<>();
        List<AlimentoModel> alimentoModels = alimentoService.listAllAlimentos();
        model.addAttribute("promocionModel",promocionModel);
        model.addAttribute("alimentoModels", alimentoModels);
        return ViewConstant.PROMOCION_NEW;
    }

    //Guarda los registros
    @PostMapping(value = "/addpromocion", params = "action=Guardar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de registrarPromocion
    public String addPromocion(@ModelAttribute(name = "promocionModel")PromocionModel promocionModel,
                              Model model) throws Exception {
        log.info("Method: addPromocion() -- Params: "+promocionModel.toString());
        promocionModel.setAlimentos(alimentos);//sale alimento = 0
        if(promocionService.addPromocion(promocionModel) != null){
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);

        }

        return "redirect:/promociones/consultaPromociones";
    }

    @RequestMapping(value = "/addAlimento", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AlimentoModel addAlimento(@RequestBody int alimentoId){
        Alimento alimento = alimentoService.findAlimentoById(alimentoId);
        alimentos.add(alimento);
        return alimentoService.findAlimentoByIdModel(alimento.getId());
    }
    @GetMapping("/modificarPromocion")
    /**
     * @param Model
     * @param int (@RequestParam, required = false)
     *
     * Modifica la promocion en base al ID.
     * Regresa un ModelAndView, donde la vista es la constante de ViewConstant y el modelo es una lista de todas las promociones.
     *
     *
     * @return ModelAndView
     *
     * @author Diana
     * */
    public String redirectModificarPromocion(Model model,
                                            @RequestParam(name = "id", required = false) int id){
        PromocionModel promocionModel = new PromocionModel();
        if(id != 0){
            promocionModel = promocionService.findPromocionByIdModel(id);
        }
        model.addAttribute("promocionModel", promocionModel);
        return ViewConstant.PROMOCION_UPDATE;
    }
    @GetMapping("/consultaPromociones")
    /**
     *
     * Método que regresa una vista la cual es la consulta de promociones,
     * y un modelo que son todas las promociones registradas en el sistema.
     *
     * @return ModelAndView Regresa la vista y los objetos.
     *
     * @author Roberto
     */
    public ModelAndView showPromociones() {
        ModelAndView mav = new ModelAndView(ViewConstant.SHOW_PROMOCION);
        mav.addObject("promociones",promocionService.listAllPromociones());
        return mav;
    }


}