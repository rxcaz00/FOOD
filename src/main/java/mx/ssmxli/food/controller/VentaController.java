package mx.ssmxli.food.controller;

import lombok.Data;
import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.entity.ContenidoPromocion;
import mx.ssmxli.food.entity.ContenidoRecibo;
import mx.ssmxli.food.entity.Promocion;
import mx.ssmxli.food.model.*;
import mx.ssmxli.food.repository.ClienteRepository;
import mx.ssmxli.food.repository.UsuarioRepository;
import mx.ssmxli.food.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

@Data
@Controller
@RequestMapping("/venta")
public class VentaController {

    private String telefono = "";//Telefono del cliente actual
    private double total = 0.0;//Total de la venta
    private List<ContenidoReciboModel> contenidosRecibo = new ArrayList<>();
    private List<ContenidoPromocionModel> contenidosPromocion = new ArrayList<>();
    private ReciboModel reciboModel;
    //Estos metodos se utilizan para añadir un ID temporal a los contenidosRecibo y contenidosPromocion
    //Para poder ponerlos y quitarlos de el total
    private int contadorContenidoRecibo = 1;
    private int contadorContenidoPromocion = 1;

    @Autowired
    @Qualifier("ventaServiceImpl")
    private VentaService ventaService;

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteService clienteService;

    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;

    @Autowired
    @Qualifier("alimentoServiceImpl")
    private AlimentoService alimentoService;

    @Autowired
    @Qualifier("promocionServiceImpl")
    private PromocionService promocionService;

    @Autowired
    @Qualifier("securityServiceImpl")
    private SecurityService securityService;

    private static final Log log = LogFactory.getLog(VentaController.class);

    @GetMapping("/cancel")
    public String cancel() {
        return "redirect:/venta";
    }

    /**
     * Crea los modelos necesarios para empezar la venta. Llena de información los modelos de Alimentos y Promocion.
     * Aunque recibe todas las promociones registradas, solo manda a la vista las que son validas el dia de hoy.
     *
     * @author Roberto
     * @author Andrés
     * */
    @GetMapping("")
    public String venta(Model model) {
        //Reinicializacion de variables globales
        reciboModel = new ReciboModel();
        telefono = "";
        contenidosRecibo = new ArrayList<>();
        contenidosPromocion = new ArrayList<>();
        contadorContenidoRecibo = 1;
        contadorContenidoPromocion = 1;
        total = 0.0;

        ClienteModel clienteModel = new ClienteModel();

        List<AlimentoModel> alimentos = alimentoService.listAllAlimentosHabilitados();//Lista con TODOS los alimentos habilitados
        List<PromocionModel> promociones = promocionService.listAllPromocionesValidas();//Lista con TODAS las promociones validas

        model.addAttribute("reciboModel",reciboModel);
        model.addAttribute("clienteModel",clienteModel);
        model.addAttribute("alimentos",alimentos);
        model.addAttribute("promociones",promociones);
        model.addAttribute("found",true);
        model.addAttribute("registrado",false);
        return ViewConstant.VENTA;
    }//end venta

    @PostMapping(value = "/addCliente")
    public String addCliente(@ModelAttribute(name = "clienteModel")ClienteModel clienteModel, Model model){
        log.info("Method: addCliente() -- Params: "+clienteModel.toString());
        if(clienteService.addCliente(clienteModel) != null)
            model.addAttribute("registrado", true);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        else
            model.addAttribute("registrado", true);

        return "redirect:/"+ViewConstant.VENTA;
    }//end addCliente


   /* Reemplazado por la solucion con Fragments de abajo
   @PostMapping(value = "/addCliente", params = "action=buscar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de clienteform
    public String findCliente(@ModelAttribute(name = "clienteModel")ClienteModel clienteModel, Model model) {
        List<ClienteModel> clientes = clienteService.listAllClientes();
        for (ClienteModel c : clientes) {
            if (c.getTelefono().equals(clienteModel.getTelefono())) {
                model.addAttribute("clienteModel", c);
                log.info("Method: findCliente() -- Params: " + c.toString());
                telefono = c.getTelefono();
                break;
            }
        }
        return ViewConstant.VENTA + " :: #cliente";
    }//end findCliente*/



   /**
    * Realiza un GET en base al telefono ingresado como parametro.
    * Busca al cliente al que pertenezca el telefono (si es que le pertence a alguien).
    * Regresa el resultado al fragment cliente, actualizando solo ese fragmento.
    *
    *
    * @param model
    * @param tel
    * @return "fragments :: cliente"
    * @author Andrés
    * */
    @GetMapping(value = "/findCliente/{tel}")
    public String findCliente(Model model, @PathVariable("tel")String tel){
        boolean found = false;
        List<ClienteModel> clientes = clienteService.listAllClientes();
        ClienteModel clienteModel = new ClienteModel();

        for(ClienteModel c : clientes){
            if(c.getTelefono().equals(tel)){
                clienteModel = c;
                found = true;
                telefono = c.getTelefono();
                break;
            }
        }

        if(!found)
            clienteModel.setTelefono(tel);

        model.addAttribute("found", found);
        model.addAttribute("clienteModel", clienteModel);

        return "fragments :: cliente";
    }

    /**
     * POST que recibe un modelo del recibo, que contiene tipoOrden, metodoPago y dineroRecibido.
     * Los demas atributos los recibe mediante otros métodos o los calcula en el Service.
     * Al terminar redirecciona a
     *
     * @param model
     * @param reciboModel
     * @return "redirect:/venta"
     * @author Andrés
     * */
    @PostMapping("/addVenta")
    public String addVenta(@ModelAttribute(name = "reciboModel")ReciboModel reciboModel, Model model) {
        reciboModel.setCliente(telefono);
        reciboModel.setUsuario(securityService.findLoggedInUsername());

        log.info("Method: addVenta() -- Params: " + reciboModel.toString());

        ReciboModel reciboModelGuardado = ventaService.addRecibo(reciboModel, contenidosRecibo, contenidosPromocion);

        if(reciboModelGuardado != null) {
            model.addAttribute("result", 1);
            model.addAttribute("reciboModelResultado", reciboModelGuardado);
        }else
            model.addAttribute("result",0);

        contenidosRecibo = new ArrayList<>();

        return "redirect:/venta";
    }//end addVenta

    /**
     * Añade el alimento seleccionado en el SELECT o INPUT TEXT de venta.html a la lista de compras.
     * El id lo recibe a traves de una variable del Path. Retorna un String con la direccion al fragmento contenidosRecibo.
     *
     * A partir de la informacion recibida construye la entidad contenidoRecibo y la añade a la variable global contenidosRecibo,
     * además de añadir la versión más nueva al model
     *
     * @param alimentoId
     * @param model
     * @return "fragments :: contenidosRecibo"
     * @author Andrés
     * */
    @GetMapping(value = "/addAlimento/{alimentoId}")
    public String addAlimento(@PathVariable(name = "alimentoId")int alimentoId, Model model){
        Alimento alimento = alimentoService.findAlimentoById(alimentoId);
        ContenidoRecibo temporalContenidoRecibo = new ContenidoRecibo();

        temporalContenidoRecibo.setId(contadorContenidoRecibo);
        contadorContenidoRecibo++;
        temporalContenidoRecibo.setAlimento(alimento);
        temporalContenidoRecibo.setPrecio(alimento.getPrecio());
        total += temporalContenidoRecibo.getPrecio();
        log.info("Method addAlimento() -- Values: ContenidoRecibo" + temporalContenidoRecibo.toString() + "| Updated Total: " + total);
        contenidosRecibo.add(ventaService.convertContenidoRecibo2ContenidoReciboModel(temporalContenidoRecibo));

        model.addAttribute("contenidosPromocion", contenidosPromocion);
        model.addAttribute("contenidosRecibo", contenidosRecibo);
        model.addAttribute("total", total);

        return "fragments :: contenidosRecibo";
    }

    /**
     * Elimina el contenidoRecibo de la lista de contenidosRecibo, ademas de restar su precio del total.
     * Recibe el id de contenidoRecibo a traves de una variable de Path.
     *
     * @param model
     * @param contenidoReciboId
     * @author Andrés
     * */
    @GetMapping(value = "/removeAlimento/{contenidoReciboId}")
    public String removeAlimento(@PathVariable(name = "contenidoReciboId")int contenidoReciboId, Model model){
        ContenidoReciboModel tempCRM = new ContenidoReciboModel();
        boolean found = false;
        log.info("Method removeAlimento() -- Param: contenidoReciboId = " + contenidoReciboId);
        //Itera en todos los ContenidoReciboModel en contenidosRecibo.
        for(ContenidoReciboModel contenidoReciboModel : contenidosRecibo){
            //Si el contenidoReciboID es igual al ID del contenidoReciboModel actual entonces...
            if(contenidoReciboModel.getId() == contenidoReciboId) {
                found = true;//La bandera cambia a true
                tempCRM = contenidoReciboModel;//Se guarda el contenidoReciboModel actual en tempCRM
                break;//Sale, porque ya encontro la coincidencia y no tiene sentido seguir
            }
        }

        //Si la bandera found es verdad, entonces...
        if(found){
            total -= tempCRM.getPrecio();//Al total se le resta el precio de tempCRM
            log.info("Method removeAlimento() -- Updated total = " + total);
            contenidosRecibo.remove(tempCRM);//tempCRM se elimina de la lista contenidosRecibo
            log.info("Method removeAlimento() -- Removed ContenidoReciboModel: " + tempCRM.toString() + " from contenidosRecibo");
        }

        /*
        * ¿Por que no se elimina en el foreach en el momento que se encuentra la coincidencia de ID?
        * Si se realizara de esa manera, saltaria la excepcion ConcurrentModification, que sale al modificar un objeto
        * por el cual estamos iterando.
        * */

        model.addAttribute("contenidosPromocion", contenidosPromocion);
        model.addAttribute("contenidosRecibo", contenidosRecibo);
        model.addAttribute("total", total);

        return "fragments :: contenidosRecibo";
    }

    @GetMapping(value = "/addPromocion/{promocionId}")
    public String addPromocion(Model model, @PathVariable("promocionId")int promocionId){
        PromocionModel promocionTemp = promocionService.findPromocionByIdModel(promocionId);
        ContenidoPromocionModel tempCPM = new ContenidoPromocionModel();
        List<ContenidoReciboModel> contenidosReciboPromo = new ArrayList<>();
        ContenidoReciboModel contenidoRecibo;

        tempCPM.setId(contadorContenidoPromocion);
        contadorContenidoPromocion++;
        tempCPM.setIdPromocion(promocionTemp.getId());
        tempCPM.setNombrePromocion(promocionTemp.getNombre());
        tempCPM.setPrecio(promocionTemp.getPrecio());

        for(AlimentoModel alimento : promocionTemp.getAlimentos()){
            contenidoRecibo = new ContenidoReciboModel();
            contenidoRecibo.setId(contadorContenidoRecibo);
            contadorContenidoRecibo++;
            contenidoRecibo.setIdAlimento(alimento.getId());
            contenidoRecibo.setNombreAlimento(alimento.getCategoria() + ' ' + alimento.getNombre() + ' ' + alimento.getTamano());
            contenidoRecibo.setDescripcionAlimento(alimento.getDescripcion());
            contenidoRecibo.setPrecio(0.0);
            contenidosReciboPromo.add(contenidoRecibo);
        }
        tempCPM.setContenidosRecibo(contenidosReciboPromo);

        total += tempCPM.getPrecio();
        contenidosPromocion.add(tempCPM);

        model.addAttribute("contenidosPromocion", contenidosPromocion);
        model.addAttribute("contenidosRecibo", contenidosRecibo);
        model.addAttribute("total", total);

        return "fragments :: contenidosRecibo";
    }

    @GetMapping(value = "/removePromocion/{promocionId}")
    public String removePromocion(Model model,@PathVariable("promocionId")int promocionId){
        ContenidoPromocionModel tempCPM = new ContenidoPromocionModel();
        boolean found = false;//Bandera para verificar si tempCPM existe en contenidosPromocion
        log.info("Method removePromocion() -- Params promocionId: " + promocionId);

        for(ContenidoPromocionModel contenidoPromocionModel : contenidosPromocion){
            //Si el contenidoReciboID es igual al ID del contenidoReciboModel actual entonces...
            if(contenidoPromocionModel.getId() == promocionId) {
                found = true;//La bandera cambia a true
                tempCPM = contenidoPromocionModel;//Se guarda el contenidoPromocionModel actual en tempCPM
                break;//Sale, porque ya encontro la coincidencia y no tiene sentido seguir
            }
        }

        if(found){
            total -= tempCPM.getPrecio();//Se reduce el precio del total
            log.info("Method removeAlimento() -- Updated total = " + total);
            contenidosPromocion.remove(tempCPM);//Se elimina el ContenidoPromocionModel de contenidosPromocion
            log.info("Method removeAlimento() -- Removed ContenidoReciboModel: " + tempCPM.toString() + " from contenidosPromocion");
        }

        model.addAttribute("contenidosPromocion", contenidosPromocion);
        model.addAttribute("contenidosRecibo", contenidosRecibo);
        model.addAttribute("total", total);

        return "fragments :: contenidosRecibo";
    }

}
