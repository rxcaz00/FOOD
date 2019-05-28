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

import java.util.ArrayList;
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

    private List<AlimentoModel> alimentos = new ArrayList<>();
    private static final Log log = LogFactory.getLog(PromocionController.class);

    /**
     * Te redirecciona a la direccion que indica el String de retorno
     *
     * @return String
     * @author Diana
     * */
    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/promociones/registrarPromocion";
    }

    /**
     * @param model
     *
     * Método que carga la información a mostrar en la vista
     *
     * @return String
     * @author Diana
     */
    @GetMapping("/registrarPromocion")
    public String inicio(Model model){
        PromocionModel promocionModel = new PromocionModel();
        alimentos = new ArrayList<>();
        List<AlimentoModel> alimentoModels = alimentoService.listAllAlimentosHabilitados();
        model.addAttribute("promocionModel",promocionModel);
        model.addAttribute("alimentoModels", alimentoModels);
        model.addAttribute("repeat", false);
        return ViewConstant.PROMOCION_NEW;
    }

    //Guarda los registros
    @PostMapping(value = "/addpromocion", params = "action=Guardar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de registrarPromocion
    public String addPromocion(@ModelAttribute(name = "promocionModel")PromocionModel promocionModel,
                              Model model) throws Exception {
        log.info("Method: addPromocion() -- Params: " + promocionModel.toString());
        promocionModel.setAlimentos(alimentos);//sale alimento = 0
        if(promocionService.addPromocion(promocionModel) != null){
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);

        }

        return "redirect:/promociones/consultaPromociones";
    }

    /*REEMPLAZADO POR SOLUCION SIN JSON MAS ABAJO
    @RequestMapping(value = "/addAlimento", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AlimentoModel addAlimento(@RequestBody int alimentoId){
        AlimentoModel alimento = alimentoService.findAlimentoByIdModel(alimentoId);
        alimentos.add(alimento);
        return alimentoService.findAlimentoByIdModel(alimento.getId());
    }*/

    /**
     * @param model
     * @param id (@RequestParam, required = false)
     *
     * Modifica la promocion en base al ID.
     * Regresa un ModelAndView, donde la vista es la constante de ViewConstant y el modelo es una lista de todas las promociones.
     *
     *
     * @return ModelAndView
     *
     * @author Diana
     * */
    @GetMapping("/modificarPromocion")
    public String redirectModificarPromocion(Model model,
                                            @RequestParam(name = "id") int id){
        PromocionModel promocionModel = promocionService.findPromocionByIdModel(id);
        alimentos = promocionModel.getAlimentos();
        List<AlimentoModel> alimentoModels = alimentoService.listAllAlimentosHabilitados();
        model.addAttribute("promocionModel",promocionModel);
        model.addAttribute("alimentoModels", alimentoModels);
        model.addAttribute("alimentos", alimentos);
        model.addAttribute("repeat", false);//Aunque sea una bandera para cuando se repita el alimento en la lista,
                                                  //se debe de mandar al model para evitar excepciones
        return ViewConstant.PROMOCION_UPDATE;
    }

    /**
     * Método que regresa una vista la cual es la consulta de promociones,
     * y un modelo que son todas las promociones registradas en el sistema.
     *
     * @return ModelAndView Regresa la vista y los objetos.
     *
     * @author Roberto
     */
    @GetMapping("/consultaPromociones")
    public ModelAndView showPromociones() {
        ModelAndView mav = new ModelAndView(ViewConstant.SHOW_PROMOCION);
        mav.addObject("promociones",promocionService.listAllPromociones());
        return mav;
    }

    /**
     * Recibe un ID de Alimento como variable de Path. Busca el AlimentoModel en base al alimentoId.
     * Al encontrarlo, busca que la lista alimentos no lo tenga ya guardado. En caso de que si, la bandera repeat cambia a true,
     * pero en caso contrario el AlimentoModel se añade a alimentos. Se envia repeat y alimentos al modelo, y la informacion se
     * manda al fragment contenidoPromocion.
     *
     * @param alimentoId
     * @param model
     * @return String
     * @author Andrés
     * */
    @GetMapping(value="/addAlimento/{id}")
    public String addAlimento(Model model, @PathVariable("id")int alimentoId){
        AlimentoModel alimento = alimentoService.findAlimentoByIdModel(alimentoId);
        boolean repeat = false;
        log.info("Method addAlimento() -- Found AlimentoModel: " + alimento.toString());
        if(alimentos.contains(alimento)) {
            repeat = true;
            log.warn("Method addAlimento() -- AlimentoModel: " + alimento.toString() + " already in alimentos");
        }else{
            alimentos.add(alimento);
            log.info("Method addAlimento() -- Updated alimentos: " + alimentos.toString());
        }

        model.addAttribute("repeat", repeat);
        model.addAttribute("alimentos", alimentos);

        return "fragments :: contenidoPromocion";
    }

    /**
     * Recibe un ID de alimento, y al encontrarlo en alimentos, lo elimina de la lista
     * Regresa la lista alimentos actualizada
     *
     * @param model
     * @param alimentoId
     * @return String
     * @author Andrés
     * */
    @GetMapping(value="/removeAlimento/{id}")
    public String removeAlimento(Model model, @PathVariable("id")int alimentoId){
        AlimentoModel temp = new AlimentoModel();
        boolean found = false;

        for(AlimentoModel alimentoModel : alimentos){
            if(alimentoModel.getId() == alimentoId) {
                temp = alimentoModel;
                found = true;
            }
        }

        if(found) {
            alimentos.remove(temp);
            log.info("Method removeAlimento() -- Removed AlimentoModel: " + temp.toString());
        }

        model.addAttribute("alimentos", alimentos);
        model.addAttribute("repeat", false);

        return "fragments :: contenidoPromocion";
    }

    /**
     * Manda a llamar alimentos a traves del model. Envia la informacion al fragment contenidoPromocion
     *
     * @param model
     * @return String
     * @author Andrés
     * */
    @GetMapping(value="/getAlimentos")
    public String getAlimentos(Model model){
        model.addAttribute("alimentos", alimentos);
        return "fragments :: contenidoPromocion";
    }
}