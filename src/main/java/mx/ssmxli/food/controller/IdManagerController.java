package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.sequence.CategoriaSequenceModel;
import mx.ssmxli.food.model.sequence.NombreSequenceModel;
import mx.ssmxli.food.model.sequence.TamanoSequenceModel;
import mx.ssmxli.food.service.IdManagerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/idManager")
public class IdManagerController {
    @Autowired
    @Qualifier("idManagerServiceImpl")
    private IdManagerService idManagerService;

    private static final Log log = LogFactory.getLog(IdManagerController.class);

    //CATEGORIAS
        //Consulta
    @GetMapping("/consultaCategorias")
    public ModelAndView showCategorias(){
        ModelAndView mav = new ModelAndView(ViewConstant.ID_SHOW_CATEGORIA);
        mav.addObject("categorias",idManagerService.listAllCategorias());
        return mav;
    }

        //RegistroForm
    @GetMapping("/registrarCategoria")
    public String redirectRegistrarCategoria(Model model, @RequestParam(name = "valor", required = false, defaultValue = "0") int valor){
        log.info("Method: registrarCategoria() -- Params: " + valor);

        CategoriaSequenceModel categoriaModel = new CategoriaSequenceModel();

        if(valor != 0)
            categoriaModel = idManagerService.findCategoriaModelByValor(valor);

        model.addAttribute("categoriaModel", categoriaModel);

        return ViewConstant.ID_NEW_CATEGORIA;
    }

        //Registro
    @PostMapping("/addCategoria")
    public String addCategoria(@ModelAttribute(name = "categoriaModel")CategoriaSequenceModel categoriaModel, Model model){
        log.info("Method: addCategoria() -- Params: " + categoriaModel.toString());
        idManagerService.addCategoria(categoriaModel);

        return "redirect:/idManager/" + ViewConstant.ID_SHOW_CATEGORIA;
    }

        //Registro AJAX
    @RequestMapping(value = "/ajaxAddCategoria", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CategoriaSequenceModel addCategoria(@RequestBody String categoriaNombre){
        CategoriaSequenceModel categoriaModel = new CategoriaSequenceModel();

        categoriaModel.setNombre(categoriaNombre);
        categoriaModel.setHabilitado(true);

        return idManagerService.addCategoria(categoriaModel);
    }

        //Cancelar
    @GetMapping("/cancelCategoria")
    public String cancelCategoria(){
        return "redirect:/idManager/" + ViewConstant.ID_SHOW_CATEGORIA;
    }

    //NOMBRES
        //Consulta
    /**
     * Regresa un ModelAndView con la constante de consulta de nombre y un objeto con todos los NombreSequence registrados
     *
     * @return ModelAndView
     * @author Andrés
     * */
    @GetMapping("/consultaNombres")
    public ModelAndView showNombres(){
        ModelAndView mav = new ModelAndView(ViewConstant.ID_SHOW_NOMBRE);
        mav.addObject("nombres", idManagerService.listAllNombres());
       /* Quite esta parte por problemas a la hora de mostrarlos por la categoria seleccionada
        mav.addObject("exists", idManagerService.categoriaExists());*/
        mav.addObject("exists", true); /*Eliminar esta parte si se desea utilizar la version con relaciones*/
        return mav;
    }

        //RegistroForm
    @GetMapping("/registrarNombre")
    public String redirectRegistrarNombre(Model model, @RequestParam(name = "valor", required = false, defaultValue = "0")int valor){
        NombreSequenceModel nombreModel = new NombreSequenceModel();
        boolean edit = false;//Esta variable nos permitira saber en el HTML si se va a editar o no
                            //Si es modificacion, no se puede cambiar la categoria.
        List<CategoriaSequenceModel> categoriaModels = new ArrayList<>();//Lista donde se guardan los modelos de categoria habilitados

        if(valor != 0) {
            nombreModel = idManagerService.findNombreModelByValor(valor);
            /*edit = true;*/
        }/*Quite esta parte por problemas a la hora de mostrarlos por la categoria seleccionada
        else{
            //Solo cuando no se va a editar se consultan todas las categorias habilitadas
            //Esto para evitar hacer una consulta a la BD cuando son datos que no se mostraran
            categoriaModels = idManagerService.listAllEnabledCategorias();
        }*/

        model.addAttribute("nombreModel", nombreModel);
        /*model.addAttribute("edit", edit);*/
        /*Quite esta parte por problemas a la hora de mostrarlos por la categoria seleccionada
        model.addAttribute("categorias", categoriaModels);*/

        return ViewConstant.ID_NEW_NOMBRE;
    }

        //Registro
    @PostMapping("/addNombre")
    public String addNombre(@ModelAttribute(name = "nombreModel") NombreSequenceModel nombreModel, Model model){
        log.info("Method: addNombre() -- Params = " + nombreModel.toString());
        idManagerService.addNombre(nombreModel);

        return "redirect:/idManager/" + ViewConstant.ID_SHOW_NOMBRE;
    }

        //CancelarRegistro
    @GetMapping("/cancelNombre")
    public String cancelNombre(){
        return "redirect:/idManager/" + ViewConstant.ID_SHOW_NOMBRE;
    }

        //Registro AJAX
    @RequestMapping(value = "/ajaxAddNombre", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody NombreSequenceModel addNombre(@RequestBody String nombreNombre){
        NombreSequenceModel nombreModel = new NombreSequenceModel();

        /*Quite esta parte por problemas a la hora de mostrarlos por la categoria seleccionada
        String[] splitted = datos.split("~");
        nombreModel.setNombre(splitted[0]);
        nombreModel.setValor_Categoria(Integer.parseInt(splitted[1]));*/

        nombreModel.setNombre(nombreNombre);
        nombreModel.setHabilitado(true);

        return idManagerService.addNombre(nombreModel);
    }

    //TAMAÑOS
        //Consulta
    @GetMapping("/consultaTamanos")
    public ModelAndView showTamanos(){
        ModelAndView mav = new ModelAndView(ViewConstant.ID_SHOW_TAMANO);
        mav.addObject("tamanos", idManagerService.listAllEnabledTamanos());
        /*Quite esta parte por problemas a la hora de mostrarlos por la categoria seleccionada
        mav.addObject("exists", idManagerService.categoriaExists());*/
        mav.addObject("exists",true);/*Eliminar esta parte si se desea utilizar la version con relaciones*/
        return mav;
    }

        //RegistroForm
    @GetMapping("/registrarTamano")
    public String redirectRegistrarTamano(Model model, @RequestParam(name = "valor", required = false, defaultValue = "0")int valor){
        TamanoSequenceModel tamanoModel = new TamanoSequenceModel();
        /*Quite esta parte por problemas a la hora de mostrarlos por la categoria seleccionada
        boolean edit = false;//Esta variable nos permitira saber en el HTML si se va a editar o no
        //Si es modificacion, no se puede cambiar la categoria.
        List<CategoriaSequenceModel> categoriaModels = new ArrayList<>();//Lista donde se guardan los modelos de categoria habilitados*/

        if(valor != 0) {
            tamanoModel = idManagerService.findTamanoModelByValor(valor);
            /*edit = true;*/
        }/*Quite esta parte por problemas a la hora de mostrarlos por la categoria seleccionada
        else{
            //Solo cuando no se va a editar se consultan todas las categorias habilitadas
            //Esto para evitar hacer una consulta a la BD cuando son datos que no se mostraran
            categoriaModels = idManagerService.listAllEnabledCategorias();
        }*/

        model.addAttribute("tamanoModel", tamanoModel);
        /*model.addAttribute("edit", edit);*/
        /*Quite esta parte por problemas a la hora de mostrarlos por la categoria seleccionada
        model.addAttribute("categorias", categoriaModels);*/

        return ViewConstant.ID_NEW_TAMANO;
    }

        //Registro
    @PostMapping("/addTamano")
    public String addTamano(@ModelAttribute(name = "tamanoModel") TamanoSequenceModel tamanoModel, Model model){
        log.info("Method: addTamano() -- Params = " + tamanoModel.toString());
        idManagerService.addTamano(tamanoModel);

        return "redirect:/idManager/" + ViewConstant.ID_SHOW_TAMANO;
    }

        //Registro AJAX
    @RequestMapping(value = "/ajaxAddTamano", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody TamanoSequenceModel addTamano(@RequestBody String tamanoNombre){
        TamanoSequenceModel tamanoModel = new TamanoSequenceModel();

        tamanoModel.setNombre(tamanoNombre);
        tamanoModel.setHabilitado(true);

        return idManagerService.addTamano(tamanoModel);
    }

        //Cancelar Registro
    @GetMapping("/cancelTamano")
    public String cancelTamano(){
        return "redirect:/idManager/" + ViewConstant.ID_SHOW_TAMANO;
    }
}
