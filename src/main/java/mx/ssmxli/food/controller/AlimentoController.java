package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.AlimentoModel;
import mx.ssmxli.food.model.sequence.CategoriaSequenceModel;
import mx.ssmxli.food.model.sequence.NombreSequenceModel;
import mx.ssmxli.food.model.sequence.TamanoSequenceModel;
import mx.ssmxli.food.service.AlimentoService;
import mx.ssmxli.food.service.IdManagerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
//17
@Controller
@RequestMapping("/alimentos")
public class AlimentoController {

    @Autowired
    @Qualifier("alimentoServiceImpl")
    private AlimentoService alimentoService;

    @Autowired
    @Qualifier("idManagerServiceImpl")
    private IdManagerService idManagerService;

    private static final Log log = LogFactory.getLog(AlimentoController.class);

    @GetMapping("/cancel")
    //11
    /**
     * Te redirecciona a la direccion que indica el String de retorno
     *
     * @return String
     * @author Danya
     * */
    public String cancel(){
        return "redirect:/alimentos/consultaAlimentos";
    }


    @GetMapping("/registrarAlimento")
    /**
     * @param Model
     * @param int (@RequestParam, required = false, defaultValue = 0)
     *
     * Carga la información a mostrar en la vista de registrarAlimento. Instancia el AlimentoModel que recibira los datos.
     * Consulta todos las categorías, nombres y tamaños de las tablas sequence. Si recibe un ID, lo busca y lo carga en
     * AlimentoModel. Por ultimo añade las tres listas y el AlimentoModel al Model de la vista.
     *
     * @return String
     *
     * @author Danya
     * @author Andrés
     * */
    public String RedirectRegistrarAlimento(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") int id){
        AlimentoModel alimentoModel = new AlimentoModel();
        //Se traen todos las categorias, los nombres y tamaños habilitados
        List<CategoriaSequenceModel> categorias = idManagerService.listAllEnabledCategorias();
        List<NombreSequenceModel> nombres = idManagerService.listAllEnabledNombres();
        List<TamanoSequenceModel> tamanos = idManagerService.listAllEnabledTamanos();

        //Si el ID es diferente a 0, significa que se hará una modificación
        if(id != 0){
            alimentoModel = alimentoService.findAlimentoByIdModel(id);
        }

        model.addAttribute("categorias", categorias);
        model.addAttribute("nombres", nombres);
        model.addAttribute("tamanos", tamanos);
        model.addAttribute("alimentoModel", alimentoModel);
        model.addAttribute("id", id);

        //Si id es igual a 0, se llamara al template de registrar, de otra manera se llamara al de modificar
        if(id == 0)
            return ViewConstant.ALIMENTO_NEW;
        else
            return ViewConstant.ALIMENTO_UPDATE;
    }
//18
    @PostMapping("/addAlimento")
    /**
     * @param AlimentoModel (@ModelAttribute)
     * Primero construye su ID en base a la categoría, nombre y tamaño del AlimentoModel que recibió.
     * Después añade el alimento a la base de datos y añade un entero de nombre resultRegistro al modelo.
     * Cuando resultRegistro es 1 entonces el registro fue exitoso, cuando es 0 entonces hubo un error de registro.
     * @return String
     * @author Danya
     * */
    public String addAlimento(@ModelAttribute(name = "alimentoModel")AlimentoModel alimentoModel,
                             Model model){
        log.info("Method: addAlimento() -- Params: "+alimentoModel.toString());
        //Si el ID es igual a 0 significa que es alimento nuevo, si no es un alimento ya creado
        if(alimentoModel.getId() == 0){
            //Crear el ID en base a la categoria, nombre y tamaño
            alimentoModel.setId(idManagerService.createID(alimentoModel.getCategoria_Valor(),alimentoModel.getNombre_Valor(),alimentoModel.getTamano_Valor()));
            alimentoModel.setHabilitado(true); //El alimento esta habilitado de manera predeterminada
        }
        if(alimentoService.addAlimento(alimentoModel) != null)
            model.addAttribute("resultRegistro", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        else
            model.addAttribute("resultRegistro", 0);
        return "redirect:/alimentos/"+ViewConstant.SHOW_ALIMENTO;
    }

    @GetMapping("/consultaAlimentos")
    /**
     * Regresa un ModelAndView, donde la vista es la constante de ViewConstant y el modelo es una lista de todos los
     * alimentos.
     * @return ModelAndView
     * @author Danya
     * */
    public ModelAndView showAlimento(){
        ModelAndView mav = new ModelAndView(ViewConstant.SHOW_ALIMENTO);
        mav.addObject("alimentos", alimentoService.listAllAlimentos());//Añadir todos los alimentos al modelo
        return mav;
    }
//17


    /*
        Los alimentos ya no se eliminan, solo se inhabilitan. Si dejamos este codigo sin descomentar, es posible eliminarlo ingresando
        el id directamente en el URL. Se deja el código por si se desea implementar la eliminación en un futuro.

    @GetMapping("/removeAlimento")
    /
     * @param int (@RequestParam required)
     * Elimina el alimento en base al ID que recibe. Como salida manda a llamar a showAlimento(), que de igual manera
     * esta regresando un ModelAndView.
     * @return ModelAndView
     * @author Danya
     * /
    public ModelAndView removeAlimento(@RequestParam(name = "id", required = true) int id){
        alimentoService.removeAlimento(id);//Eliminar el alimento por ID
        return showAlimento();
    }
    */
}
//52