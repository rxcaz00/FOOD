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

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteService clienteService;

    private static final Log log = LogFactory.getLog(ClienteController.class);

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/cliente/showCliente";
    }

    @GetMapping("/clienteForm")
    public String redirectClienteForm(Model model,
                                      @RequestParam(name = "telefono", required = false) String telefono){
        ClienteModel clienteModel = new ClienteModel();
        if(telefono != null){
            clienteModel = clienteService.findClienteByTelModel(telefono);
        }
        model.addAttribute("clienteModel", clienteModel);
        return ViewConstant.CLIENTE_FORM;
    }

    @PostMapping("/addcliente")
    //El ModelAttribute corresponde con el th:object que utilizamos en la vista de contactform
    public String addCliente(@ModelAttribute(name = "clienteModel")ClienteModel clienteModel,
                             Model model){
        log.info("Method: addCliente() -- Params: "+clienteModel.toString());
        if(clienteService.addCliente(clienteModel) != null){
            model.addAttribute("result", 1);//esto es para que se muestre un mensaje de que se agregó éxitosamente
        }else{
            model.addAttribute("result", 0);
        }
        return "redirect:/cliente/showCliente";
    }

    @GetMapping("/showCliente")
    public ModelAndView showCliente(){
        ModelAndView mav = new ModelAndView(ViewConstant.CLIENTE);
        mav.addObject("cliente", clienteService.listAllClientes());
        return mav;
    }

    @GetMapping("/removeCliente")
    public ModelAndView removeContact(@RequestParam(name = "telefono", required = true) String telefono){
        clienteService.removeCliente(telefono);
        return showCliente();
    }


}
