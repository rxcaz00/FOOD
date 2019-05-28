package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Comanda;
import mx.ssmxli.food.entity.ContenidoPromocion;
import mx.ssmxli.food.entity.ContenidoRecibo;
import mx.ssmxli.food.entity.Recibo;
import mx.ssmxli.food.model.ComandaModel;
import mx.ssmxli.food.model.ContenidoPromocionModel;
import mx.ssmxli.food.model.ContenidoReciboModel;
import mx.ssmxli.food.model.ReciboModel;
import mx.ssmxli.food.repository.AlimentoRepository;
import mx.ssmxli.food.repository.ContenidoPromocionRepository;
import mx.ssmxli.food.repository.ReciboRepository;
import mx.ssmxli.food.service.ClienteService;
import mx.ssmxli.food.service.PromocionService;
import mx.ssmxli.food.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("ventaConverter")
public class VentaConverter {
    @Autowired
    @Qualifier("reciboRepository")
    private ReciboRepository reciboRepository;

    @Autowired
    @Qualifier("alimentoRepository")
    private AlimentoRepository alimentoRepository;

    @Autowired
    @Qualifier("contenidoPromocionRepository")
    private ContenidoPromocionRepository contenidoPromocionRepository;

    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteService clienteService;

    @Autowired
    @Qualifier("promocionServiceImpl")
    private PromocionService promocionService;


    //Recibo
    /**
     *
     * @param reciboModel
     *
     * Convierte un objeto de tipo "ReciboModel" a un objeto de tipo "Recibo"
     *
     * @return recibo
     * @author Roberto
     */
    public Recibo convertReciboModel2Recibo(ReciboModel reciboModel) {
        Recibo recibo = new Recibo();
        List<ContenidoRecibo> contenidosRecibo = new ArrayList<>();

        recibo.setCliente(clienteService.findClienteByTelefono(reciboModel.getCliente()));
        recibo.setFecha(new Date());
        recibo.setId(reciboModel.getId());
        recibo.setMetodoPago(reciboModel.getMetodoPago());
        recibo.setTipoOrden(reciboModel.getTipoOrden());
        recibo.setNotas(reciboModel.getNotas());
        recibo.setSubtotal(reciboModel.getSubtotal());
        recibo.setTotal(reciboModel.getTotal());
        recibo.setDineroRecibido(reciboModel.getDineroRecibido());
        recibo.setDireccionDeEnvio(reciboModel.getDireccionDeEnvio());
        recibo.setNumeroMesa(reciboModel.getNumeroMesa());
        recibo.setUsuario(usuarioService.findUsuarioByUsuario(reciboModel.getUsuario()));
        try {
            for (ContenidoReciboModel contReciboModel : reciboModel.getContenidosRecibo()) {
                contenidosRecibo.add(convertContenidoReciboModel2ContenidoRecibo(contReciboModel));
            }
        }catch(NullPointerException npe){
            contenidosRecibo = new ArrayList<>();
        }
        recibo.setContenidosRecibo(contenidosRecibo);

        return recibo;
    }

    /**
     *
     * @param recibo
     *
     * Convierte un objeto de tipo "Recibo" a un objeto de tipo "ReciboModel"
     *
     * @return reciboModel
     * @author Roberto
     * @author Andrés
     */
    public ReciboModel convertRecibo2ReciboModel(Recibo recibo) {
        ReciboModel reciboModel = new ReciboModel();
        List<ContenidoReciboModel> contenidoReciboModels = new ArrayList<>();
        String telefono = "";

        //Si el cliente es diferente de null, osea si hay uno asignado al recibo, entonces...
        if(recibo.getCliente() != null)
            telefono = recibo.getCliente().getTelefono(); //Asignar el telefono del cliente a la variable cliente

        /*
        * Se realizo el codigo anterior para evitar un NullPointerException, ocasionado por querer acceder
        * a un telefono de un cliente null
        * */

        reciboModel.setCliente(telefono);
        reciboModel.setFecha(recibo.getFecha().toString());
        reciboModel.setId(recibo.getId());
        reciboModel.setMetodoPago(recibo.getMetodoPago());
        reciboModel.setTipoOrden(recibo.getTipoOrden());
        reciboModel.setNotas(recibo.getNotas());
        reciboModel.setSubtotal(recibo.getSubtotal());
        reciboModel.setTotal(recibo.getTotal());
        reciboModel.setDineroRecibido(recibo.getDineroRecibido());
        reciboModel.setNumeroMesa(recibo.getNumeroMesa());
        reciboModel.setDireccionDeEnvio(recibo.getDireccionDeEnvio());
        reciboModel.setUsuario(recibo.getUsuario().getUsuario());
        reciboModel.setNombreUsuario(recibo.getUsuario().getNombre() + " " + recibo.getUsuario().getApellidos());
        for (ContenidoRecibo contRecibo : recibo.getContenidosRecibo()) {
            contenidoReciboModels.add(convertContenidoRecibo2ContenidoReciboModel(contRecibo));
        }
        reciboModel.setContenidosRecibo(contenidoReciboModels);

        return reciboModel;
    }

    //Contenido Recibo
    public ContenidoReciboModel convertContenidoRecibo2ContenidoReciboModel(ContenidoRecibo contenidoRecibo){
        ContenidoReciboModel contenidoReciboModel = new ContenidoReciboModel();

        contenidoReciboModel.setId(contenidoRecibo.getId());
        contenidoReciboModel.setPrecio(contenidoRecibo.getPrecio());
        contenidoReciboModel.setIdAlimento(contenidoRecibo.getAlimento().getId());
        contenidoReciboModel.setNombreAlimento(contenidoRecibo.getAlimento().getCategoriaSequence().getNombre() + " " +
                contenidoRecibo.getAlimento().getNombreSequence().getNombre() + " " + contenidoRecibo.getAlimento().getTamanoSequence().getNombre());
        contenidoReciboModel.setDescripcionAlimento(contenidoRecibo.getAlimento().getDescripcion());
        if(contenidoRecibo.getRecibo() != null)
            contenidoReciboModel.setIdRecibo(contenidoRecibo.getRecibo().getId());
        if(contenidoRecibo.getContenidoPromocion() != null)
            contenidoReciboModel.setIdContenidoPromocion(contenidoRecibo.getContenidoPromocion().getId());

        return contenidoReciboModel;
    }

    public ContenidoRecibo convertContenidoReciboModel2ContenidoRecibo(ContenidoReciboModel contenidoReciboModel){
        ContenidoRecibo contenidoRecibo = new ContenidoRecibo();

        contenidoRecibo.setId(contenidoReciboModel.getId());
        contenidoRecibo.setPrecio(contenidoReciboModel.getPrecio());
        contenidoRecibo.setAlimento(alimentoRepository.findById(contenidoReciboModel.getIdAlimento()));
        if(contenidoReciboModel.getIdRecibo() != -1)
            contenidoRecibo.setRecibo(reciboRepository.findReciboById(contenidoReciboModel.getIdRecibo()));
        if(contenidoReciboModel.getIdContenidoPromocion() != -1)
            contenidoRecibo.setContenidoPromocion(contenidoPromocionRepository.findById(contenidoReciboModel.getIdContenidoPromocion()));

        return contenidoRecibo;
    }

    public ContenidoPromocionModel convertContenidoPromocion2ContenidoPromocionModel(ContenidoPromocion contenidoPromocion){
        ContenidoPromocionModel contenidoPromocionModel = new ContenidoPromocionModel();
        List<ContenidoReciboModel> contenidoReciboModels = new ArrayList<>();

        contenidoPromocionModel.setId(contenidoPromocion.getId());
        contenidoPromocionModel.setIdRecibo(contenidoPromocion.getRecibo().getId());
        contenidoPromocionModel.setIdPromocion(contenidoPromocion.getPromocion().getId());
        contenidoPromocionModel.setNombrePromocion(contenidoPromocion.getPromocion().getNombre());
        contenidoPromocionModel.setPrecio(contenidoPromocion.getPrecio());
        for(ContenidoRecibo contenidoRecibo : contenidoPromocion.getContenidosRecibo()){
            contenidoReciboModels.add(convertContenidoRecibo2ContenidoReciboModel(contenidoRecibo));
        }
        contenidoPromocionModel.setContenidosRecibo(contenidoReciboModels);

        return contenidoPromocionModel;
    }

    public ContenidoPromocion convertContenidoPromocionModel2ContenidoPromcion(ContenidoPromocionModel contenidoPromocionModel){
        ContenidoPromocion contenidoPromocion = new ContenidoPromocion();
        List<ContenidoRecibo> contenidoRecibos = new ArrayList<>();

        contenidoPromocion.setId(contenidoPromocionModel.getId());
        contenidoPromocion.setPrecio(contenidoPromocionModel.getPrecio());
        contenidoPromocion.setRecibo(reciboRepository.findReciboById(contenidoPromocionModel.getIdRecibo()));
        contenidoPromocion.setPromocion(promocionService.findPromocionById(contenidoPromocionModel.getIdPromocion()));
        for(ContenidoReciboModel contenidoReciboModel : contenidoPromocionModel.getContenidosRecibo()){
            contenidoRecibos.add(convertContenidoReciboModel2ContenidoRecibo(contenidoReciboModel));
        }
        contenidoPromocion.setContenidosRecibo(contenidoRecibos);

        return contenidoPromocion;
    }

    /*Por no tener funcion ni atributos suficientes para diferenciarlo del Recibo, se decidio no incorporar la comanda.
    Hay oportunidad de realizar cosas con la Comanda en un futuro, por lo que no se eliminan sus métodos
    public ComandaModel convertComanda2ComandaModel(Comanda comanda){
        ComandaModel comandaModel = new ComandaModel();

        comandaModel.setId(comanda.getId());
        comandaModel.setRecibo(convertRecibo2ReciboModel(comanda.getRecibo()));
        comandaModel.setNumeroMesa(comanda.getNumeroMesa());

        return comandaModel;
    }

    public Comanda convertComandaModel2Comanda(ComandaModel comandaModel){
        Comanda comanda = new Comanda();

        comanda.setId(comandaModel.getId());
        comanda.setNumeroMesa(comandaModel.getNumeroMesa());
        comanda.setRecibo(convertReciboModel2Recibo(comandaModel.getRecibo()));

        return comanda;
    }*/
}
