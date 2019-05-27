package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Comanda;
import mx.ssmxli.food.entity.ContenidoRecibo;
import mx.ssmxli.food.entity.Recibo;
import mx.ssmxli.food.model.ComandaModel;
import mx.ssmxli.food.model.ContenidoReciboModel;
import mx.ssmxli.food.model.ReciboModel;
import mx.ssmxli.food.repository.AlimentoRepository;
import mx.ssmxli.food.repository.ReciboRepository;
import mx.ssmxli.food.service.ClienteService;
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
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteService clienteService;

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
     */
    public ReciboModel convertRecibo2ReciboModel(Recibo recibo) {
        ReciboModel reciboModel = new ReciboModel();
        List<ContenidoReciboModel> contenidoReciboModels = new ArrayList<>();

        reciboModel.setCliente(recibo.getCliente().getTelefono());
        reciboModel.setFecha(recibo.getFecha().toString());
        reciboModel.setId(recibo.getId());
        reciboModel.setMetodoPago(recibo.getMetodoPago());
        reciboModel.setTipoOrden(recibo.getTipoOrden());
        reciboModel.setNotas(recibo.getNotas());
        reciboModel.setSubtotal(recibo.getSubtotal());
        reciboModel.setTotal(recibo.getTotal());
        reciboModel.setUsuario(recibo.getUsuario().getUsuario());
        for (ContenidoRecibo contRecibo : recibo.getContenidosRecibo()) {
            contenidoReciboModels.add(convertContenidoRecibo2ContenidoReciboModel(contRecibo));
        }
        reciboModel.setContenidosRecibo(contenidoReciboModels);

        return reciboModel;
    }

    public ContenidoReciboModel convertContenidoRecibo2ContenidoReciboModel(ContenidoRecibo contenidoRecibo){
        ContenidoReciboModel contenidoReciboModel = new ContenidoReciboModel();

        contenidoReciboModel.setId(contenidoRecibo.getId());
        contenidoReciboModel.setPrecio(contenidoRecibo.getPrecio());
        contenidoReciboModel.setIdAlimento(contenidoRecibo.getAlimento().getId());
        contenidoReciboModel.setNombreAlimento(contenidoRecibo.getAlimento().getNombreSequence().getNombre());

        return contenidoReciboModel;
    }

    public ContenidoRecibo convertContenidoReciboModel2ContenidoRecibo(ContenidoReciboModel contenidoReciboModel){
        ContenidoRecibo contenidoRecibo = new ContenidoRecibo();

        contenidoRecibo.setId(contenidoReciboModel.getId());
        contenidoRecibo.setPrecio(contenidoReciboModel.getPrecio());
        contenidoRecibo.setRecibo(reciboRepository.findReciboById(contenidoReciboModel.getIdRecibo()));
        contenidoRecibo.setAlimento(alimentoRepository.findById(contenidoReciboModel.getIdAlimento()));

        return contenidoRecibo;
    }

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
    }
}
