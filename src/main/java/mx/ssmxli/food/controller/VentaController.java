package mx.ssmxli.food.controller;

import lombok.Data;
import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.entity.ContenidoRecibo;
import mx.ssmxli.food.model.*;
import mx.ssmxli.food.repository.ClienteRepository;
import mx.ssmxli.food.repository.UsuarioRepository;
import mx.ssmxli.food.service.AlimentoService;
import mx.ssmxli.food.service.ClienteService;
import mx.ssmxli.food.service.PromocionService;
import mx.ssmxli.food.service.VentaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Controller
@RequestMapping("/venta")
public class VentaController {
    private String telefono;
    private String usuario = "prueba";
    private List<ContenidoReciboModel> contenidosRecibo = new ArrayList<>();
    private ReciboModel reciboModel;

    @Autowired
    @Qualifier("ventaServiceImpl")
    private VentaService ventaService;

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteService clienteService;

    @Autowired
    @Qualifier("clienteRepository")
    private ClienteRepository clienteRepository;

    @Autowired
    @Qualifier("usuarioRepository")
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Qualifier("alimentoServiceImpl")
    private AlimentoService alimentoService;

    @Autowired
    @Qualifier("promocionServiceImpl")
    private PromocionService promocionService;

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
        reciboModel = new ReciboModel();
        ClienteModel clienteModel = new ClienteModel();

        List<AlimentoModel> alimentos = alimentoService.listAllAlimentosHabilitados();//Lista con TODOS los alimentos habilitados
        List<PromocionModel> promociones = promocionService.listAllPromocionesValidas();//Lista con TODAS las promociones validas

        model.addAttribute("reciboModel",reciboModel);
        model.addAttribute("clienteModel",clienteModel);
        model.addAttribute("alimentos",alimentos);
        model.addAttribute("promociones",promociones);
        return ViewConstant.VENTA;
    }//end venta

    @PostMapping(value = "/addCliente", params = "action=registrar")
    public String addCliente(@ModelAttribute(name = "clienteModel")ClienteModel clienteModel, Model model){
        log.info("Method: addCliente() -- Params: "+clienteModel.toString());
        if(clienteService.addCliente(clienteModel) != null)
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        else
            model.addAttribute("result", 0);

        return "redirect:/venta";
    }//end addCliente


    @PostMapping(value = "/addCliente", params = "action=buscar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de clienteform
    public String findCliente(@ModelAttribute(name = "clienteModel")ClienteModel clienteModel, Model model) {
        List<ClienteModel> clientes = clienteService.listAllClientes();
        for (ClienteModel c : clientes) {
            if (c.getTelefono().equals(clienteModel.getTelefono())) {
                model.addAttribute("clienteModel", c);
                telefono = c.getTelefono();
                break;
            }
        }
        return ViewConstant.VENTA;
    }//end findCliente

    @PostMapping("/addVenta")
    public String addVenta(@ModelAttribute(name = "reciboModel")ReciboModel reciboModel, Model model) {
        reciboModel.setCliente(clienteRepository.findByTelefono(telefono));
        //reciboModel.setUsuario(usuarioRepository.findUsuarioByUsuario(usuario));

        //log.info("Method: addVenta() -- Params: " + reciboModel.toString());
        if(ventaService.addRecibo(reciboModel, contenidosRecibo) != null)
            model.addAttribute("result", 1);
        else
            model.addAttribute("result",0);

        contenidosRecibo = new ArrayList<>();

        return "redirect:/venta";
    }//end addVenta

    /**
     *
     * Añade el alimento seleccionado en el SELECT o INPUT TEXT de venta.html a la lista de compras.
     * El id lo recibe del HTML en forma de JSON. De igual manera regresa un AlimentoModel en formato JSON.
     *
     * A partir de la informacion recibida construye la entidad contenidoRecibo y la añade a la variable global contenidosRecibo
     *
     * @param @RequestBody int
     * @return @ResponseBody AlimentoModel
     * @author Andrés
     * */
    @RequestMapping(value = "/addAlimento", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AlimentoModel addAlimento(@RequestBody int alimentoId){
        Alimento alimento = alimentoService.findAlimentoById(alimentoId);
        ContenidoRecibo temporalContenidoRecibo = new ContenidoRecibo();

        temporalContenidoRecibo.setAlimento(alimento);
        temporalContenidoRecibo.setPrecio(alimento.getPrecio());
        System.out.println(temporalContenidoRecibo.getAlimento().getNombreSequence() + " " + temporalContenidoRecibo.getPrecio());
        contenidosRecibo.add(ventaService.convertContenidoRecibo2ContenidoReciboModel(temporalContenidoRecibo));

        return alimentoService.findAlimentoByIdModel(alimento.getId());
    }

    /**
     *
     * @author Andrés
     * */
    @RequestMapping(value = "/findPromocion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody PromocionModel addPromocion(@RequestBody int promocionID){
        PromocionModel promocion = promocionService.findPromocionByIdModel(promocionID);

        System.out.println(promocion.getNombre() + promocion.getAlimentos());

        return promocion;
    }

}
