package mx.ssmxli.food.controller;

import lombok.Data;
import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.AlimentoModel;
import mx.ssmxli.food.model.ClienteModel;
import mx.ssmxli.food.model.PromocionModel;
import mx.ssmxli.food.model.ReciboModel;
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

import java.util.List;

@Data
@Controller
@RequestMapping("/venta")
public class VentaController {
    private String telefono;
    private String usuario = "prueba";

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

    @GetMapping("")
    public String venta(Model model) {
        ReciboModel reciboModel = new ReciboModel();
        ClienteModel clienteModel = new ClienteModel();
        List<AlimentoModel> alimentos = alimentoService.listAllAlimentos();
        List<PromocionModel> promociones = promocionService.listAllPromociones();
        model.addAttribute("reciboModel",reciboModel);
        model.addAttribute("clienteModel",clienteModel);
        model.addAttribute("alimentos",alimentos);
        model.addAttribute("promociones",promociones);
        return ViewConstant.VENTA;
    }

    @PostMapping(value = "/addCliente", params = "action=registrar")
    public String addCliente(@ModelAttribute(name = "clienteModel")ClienteModel clienteModel, Model model){
        log.info("Method: addCliente() -- Params: "+clienteModel.toString());
        if(clienteService.addCliente(clienteModel) != null)
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        else
            model.addAttribute("result", 0);

        return "redirect:/venta";
    }


    @PostMapping(value = "/addCliente", params = "action=buscar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de clienteform
    public String findCliente(@ModelAttribute(name = "clienteModel")ClienteModel clienteModel, Model model) {
        List<ClienteModel> clientes = clienteService.listAllClientes();
        for (ClienteModel c : clientes) {
            if (c.getTelefono().equals(clienteModel.getTelefono())) {
                model.addAttribute("clienteModel", c);
                telefono = c.getTelefono();
            }
        }
        return ViewConstant.VENTA;
    }

    @PostMapping("/addVenta")
    public String addVenta(@ModelAttribute(name = "reciboModel")ReciboModel reciboModel, Model model) {
        reciboModel.setCliente(clienteRepository.findByTelefono(telefono));
        reciboModel.setUsuario(usuarioRepository.findUsuarioByUsuario(usuario));
        log.info("Method: addVenta() -- Params: " + reciboModel.toString());
        if(ventaService.addRecibo(reciboModel) != null)
            model.addAttribute("result", 1);
        else
            model.addAttribute("result",0);

        return "redirect:/venta";
    }

    /**
     * Añade el alimento seleccionado en el SELECT o INPUT TEXT de venta.html a la lista de compras.
     * El id lo recibe del HTML en forma de JSON.
     *
     * @param @RequestBody int
     * @author Andrés
     * */
    @RequestMapping(value = "/addAlimento", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AlimentoModel addAlimento(@RequestBody int alimentoId){
        System.out.println("Felicidades");
        return alimentoService.findAlimentoByIdModel(alimentoId);
    }
}
