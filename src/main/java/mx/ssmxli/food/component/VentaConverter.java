package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Recibo;
import mx.ssmxli.food.model.ReciboModel;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("ventaConverter")
public class VentaConverter {
    public Recibo convertReciboModel2Recibo(ReciboModel reciboModel) {
        Recibo recibo = new Recibo();
        recibo.setCliente(reciboModel.getCliente());
        recibo.setFecha(new Date());
        recibo.setId(reciboModel.getId());
        recibo.setMetodoPago(reciboModel.getMetodoPago());
        recibo.setNotas(reciboModel.getNotas());
        recibo.setSubtotal(reciboModel.getSubtotal());
        recibo.setTotal(reciboModel.getTotal());
        recibo.setUsuario(reciboModel.getUsuario());

        return recibo;
    }

    public ReciboModel convertRecibo2ReciboModel(Recibo recibo) {
        ReciboModel reciboModel = new ReciboModel();
        reciboModel.setCliente(recibo.getCliente());
        reciboModel.setFecha(recibo.getFecha().toString());
        reciboModel.setId(recibo.getId());
        reciboModel.setMetodoPago(recibo.getMetodoPago());
        reciboModel.setNotas(recibo.getNotas());
        reciboModel.setSubtotal(recibo.getSubtotal());
        reciboModel.setTotal(recibo.getTotal());
        reciboModel.setUsuario(recibo.getUsuario());
        return reciboModel;
    }
}
