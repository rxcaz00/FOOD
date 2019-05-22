package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.ContenidoRecibo;
import mx.ssmxli.food.entity.Recibo;
import mx.ssmxli.food.model.ContenidoReciboModel;
import mx.ssmxli.food.model.ReciboModel;
import mx.ssmxli.food.repository.AlimentoRepository;
import mx.ssmxli.food.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("ventaConverter")
public class VentaConverter {
    @Autowired
    @Qualifier("ventaRepository")
    VentaRepository ventaRepository;

    @Autowired
    @Qualifier("alimentoRepository")
    AlimentoRepository alimentoRepository;

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

        recibo.setCliente(reciboModel.getCliente());
        recibo.setFecha(new Date());
        recibo.setId(reciboModel.getId());
        recibo.setMetodoPago(reciboModel.getMetodoPago());
        recibo.setTipoOrden(reciboModel.getTipoOrden());
        recibo.setNotas(reciboModel.getNotas());
        recibo.setSubtotal(reciboModel.getSubtotal());
        recibo.setTotal(reciboModel.getTotal());
        recibo.setUsuario(reciboModel.getUsuario());
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

        reciboModel.setCliente(recibo.getCliente());
        reciboModel.setFecha(recibo.getFecha().toString());
        reciboModel.setId(recibo.getId());
        reciboModel.setMetodoPago(recibo.getMetodoPago());
        reciboModel.setTipoOrden(recibo.getTipoOrden());
        reciboModel.setNotas(recibo.getNotas());
        reciboModel.setSubtotal(recibo.getSubtotal());
        reciboModel.setTotal(recibo.getTotal());
        reciboModel.setUsuario(recibo.getUsuario());
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
        contenidoReciboModel.setNombreAlimento(contenidoRecibo.getAlimento().getNombre());

        return contenidoReciboModel;
    }

    public ContenidoRecibo convertContenidoReciboModel2ContenidoRecibo(ContenidoReciboModel contenidoReciboModel){
        ContenidoRecibo contenidoRecibo = new ContenidoRecibo();

        contenidoRecibo.setId(contenidoReciboModel.getId());
        contenidoRecibo.setPrecio(contenidoReciboModel.getPrecio());
        contenidoRecibo.setRecibo(ventaRepository.findReciboById(contenidoReciboModel.getIdRecibo()));
        contenidoRecibo.setAlimento(alimentoRepository.findById(contenidoReciboModel.getIdAlimento()));

        return contenidoRecibo;
    }
}
