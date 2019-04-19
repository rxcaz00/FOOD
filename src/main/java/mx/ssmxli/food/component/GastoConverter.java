package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Gasto;
import mx.ssmxli.food.model.GastoModel;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("gastoConverter")
public class GastoConverter {
    public Gasto convertGastoModel2Gasto(GastoModel gastoModel) {
        //String fecha = gastoModel.getFecha();
        //Date fech = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        Gasto gasto = new Gasto();
        gasto.setDescripcion(gastoModel.getDescripcion());
        gasto.setId(gastoModel.getId());
        gasto.setMonto(gastoModel.getMonto());
        gasto.setFecha(gastoModel.getFecha());
        gasto.setTipo(gastoModel.getTipo());
        return gasto;
    }
    public GastoModel convertGasto2GastoModel(Gasto gasto){
        GastoModel gastoModel = new GastoModel();
        gastoModel.setId(gasto.getId());
        gastoModel.setDescripcion(gasto.getDescripcion());
        gastoModel.setFecha(gasto.getFecha());
        gastoModel.setTipo(gasto.getTipo());
        gastoModel.setMonto(gasto.getMonto());
        return gastoModel;
    }
}
