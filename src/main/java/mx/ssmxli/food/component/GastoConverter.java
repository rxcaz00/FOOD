package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Gasto;
import mx.ssmxli.food.model.GastoModel;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("gastoConverter")
public class GastoConverter {
    /**
     *
     * @param gastoModel
     *
     * Convierte un objeto de tipo "GastoModel" a un objeto de tipo "Gasto"
     *
     * @return gasto
     * @throws Exception
     * @author Danya
     */
    public Gasto convertGastoModel2Gasto(GastoModel gastoModel) throws Exception {

        Gasto gasto = new Gasto();
        gasto.setFecha(new Date());
        gasto.setDescripcion(gastoModel.getDescripcion());
        gasto.setId(gastoModel.getId());
        gasto.setMonto(gastoModel.getMonto());
        gasto.setTipo(gastoModel.getTipo());
        return gasto;

    }

    /**
     *
     * @param gasto
     *
     * Convierte un objeto de tipo "Gasto" a un objeto de tipo "GastoModel"
     *
     * @return gastoModel
     * @author Danya
     */
    public GastoModel convertGasto2GastoModel(Gasto gasto){
        GastoModel gastoModel = new GastoModel();
        gastoModel.setId(gasto.getId());
        gastoModel.setDescripcion(gasto.getDescripcion());
        gastoModel.setFecha(gasto.getFecha().toString());
        gastoModel.setMonto(gasto.getMonto());
        return gastoModel;
    }
}
