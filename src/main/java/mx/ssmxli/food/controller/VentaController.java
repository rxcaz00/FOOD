package mx.ssmxli.food.controller;

import lombok.Data;
import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.model.ClienteModel;
import mx.ssmxli.food.model.ReciboModel;
import mx.ssmxli.food.repository.ClienteRepository;
import mx.ssmxli.food.repository.UsuarioRepository;
import mx.ssmxli.food.service.ClienteService;
import mx.ssmxli.food.service.VentaService;
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

    private static final Log log = LogFactory.getLog(VentaController.class);

    @GetMapping("/cancel")
    public String cancel() {
        return "redirect:/venta/venta";
    }

    @GetMapping("/venta")
    public String venta(Model model) {
        ReciboModel reciboModel = new ReciboModel();
        ClienteModel clienteModel = new ClienteModel();
        model.addAttribute("reciboModel",reciboModel);
        model.addAttribute("clienteModel",clienteModel);
        return ViewConstant.VENTA;
    }

    @PostMapping(value = "/addCliente", params = "action=registrar")
    public String addCliente(@ModelAttribute(name = "clienteModel")ClienteModel clienteModel, Model model){
        log.info("Method: addCliente() -- Params: "+clienteModel.toString());
        if(clienteService.addCliente(clienteModel) != null)
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        else
            model.addAttribute("result", 0);

        return "redirect:/venta/venta";
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

        return "redirect:/venta/venta";
    }
}
