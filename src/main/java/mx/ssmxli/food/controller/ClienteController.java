package mx.ssmxli.food.controller;

import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.entity.Cliente;
import mx.ssmxli.food.model.ClienteModel;
import mx.ssmxli.food.service.ClienteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteService clienteService;

    private static final Log log = LogFactory.getLog(ClienteController.class);

    @GetMapping("/cancel")
    /**
     * Te redirecciona a la direccion que indica el String de retorno
     *
     * @return String
     *
     * @author Roberto
     * */
    public String cancel(){
        return "redirect:/clientes/showCliente";
    }

    @GetMapping("/newCliente")
    /**
     *
     * @param model
     *
     * Te redirecciona a la direccion que indica el String de retorno
     *
     * @return String
     *
     * @author Roberto
     * */
    public String redirectClienteForm(Model model){
        ClienteModel clienteModel = new ClienteModel();
        model.addAttribute("clienteModel", clienteModel);
        return ViewConstant.VENTA;
    }

    /*
    @PostMapping(value = "/addCliente", params = "action=registrar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de clienteform
    public String addCliente(@ModelAttribute(name = "clienteModel")ClienteModel clienteModel, Model model){
        log.info("Method: addCliente() -- Params: "+clienteModel.toString());
        if(clienteService.addCliente(clienteModel) != null)
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        else
            model.addAttribute("result", 0);

        return "redirect:/clientes/newCliente";
    }

    @PostMapping(value = "/addCliente", params = "action=buscar")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de clienteform
    public String findCliente(@ModelAttribute(name = "clienteModel")ClienteModel clienteModel, Model model) {
        List<ClienteModel> clientes = clienteService.listAllClientes();
        for (ClienteModel c : clientes) {
            if (c.getTelefono().equals(clienteModel.getTelefono())) {
                model.addAttribute("clienteModel", c);
            }
        }
        return ViewConstant.VENTA;
    }
     */
}