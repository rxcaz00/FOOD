package mx.ssmxli.food.controller;

import lombok.Data;
import mx.ssmxli.food.constant.ViewConstant;
import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.entity.ContenidoRecibo;
import mx.ssmxli.food.entity.Promocion;
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


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Data
@Controller
@RequestMapping("/venta")
public class VentaController {
    private String telefono;
    private String usuario = "prueba";
    private List<ContenidoRecibo> contenidosRecibo;

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
        ReciboModel reciboModel = new ReciboModel();
        ClienteModel clienteModel = new ClienteModel();
        contenidosRecibo = new ArrayList<>();
        List<AlimentoModel> alimentos = alimentoService.listAllAlimentos();
        List<PromocionModel> allPromociones = promocionService.listAllPromociones();

        //Revisa la fecha actual y solo añade las promociones validas el dia de hoy
        LocalDate hoy = LocalDate.now();
        List<PromocionModel> promociones = new ArrayList<>();
        for (PromocionModel promo: allPromociones) {
            //En vez de parsear de String a Date las fechas de inicio y fin, simplemente obtengo la entidad promocion a
            //partir de el ID de PromocionModel.
            //Tenia una funcion a traves de promocionService para buscar a traves del ID del modelo, pero para reducir las
            //consultas a base de datos preferi convertir de PromocionModel a Promocion.
            Promocion temp = null;
            try {
                temp = promocionService.findPromocionById(promocionService.convertPromocionModel2Promocion(promo).getId());
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Parsea las fechas de inicio y de fin de la promocion. La fecha de la promocion esta en Date
            //pero la fecha actual esta en LocalDate.

            //Se necesita obtener el Instant de la fecha, luego definir la Zona a traves del ID de la zona predeterminada del sistema.
            //Por ultimo se parsea a LocalDate.
            LocalDate fechaI = temp.getFechaI().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaF = temp.getFechaF().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            //Compara las fechas de la promocion con la de hoy. Si hoy es igual o mayor a la fecha de inicio y menor a la fecha
            // de fin entonces se pasa a la siguiente condicion
            if ((hoy.isAfter(fechaI)||hoy.isEqual(fechaI))&&(hoy.isBefore(fechaF)||hoy.isEqual(fechaF)))
                //Calendar.DAY_OF_WEEK regresa el dia de la semana en formato numero del 1 al 7, de Lunes a Domingo
                if (promo.getDias()[(hoy.getDayOfWeek().getValue() - 1)]) //Al ser de 1 a 7, le restamos 1 para que empiece en 0
                    //Como la vigencia y disponibilidad de la promocion son validas, entonces se agregara al model
                    promociones.add(promo);
        }//end foreach

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
        reciboModel.setUsuario(usuarioRepository.findUsuarioByUsuario(usuario));
        reciboModel.setContenidosRecibo(contenidosRecibo);

        log.info("Method: addVenta() -- Params: " + reciboModel.toString());
        if(ventaService.addRecibo(reciboModel) != null)
            model.addAttribute("result", 1);
        else
            model.addAttribute("result",0);

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
        System.out.println(temporalContenidoRecibo.getAlimento().getNombre() + " " + temporalContenidoRecibo.getPrecio());
        contenidosRecibo.add(temporalContenidoRecibo);
        return alimentoService.findAlimentoByIdModel(alimento.getId());
    }
}
