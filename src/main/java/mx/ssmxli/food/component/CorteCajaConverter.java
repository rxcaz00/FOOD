package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.CorteCaja;
import mx.ssmxli.food.entity.Usuario;
import mx.ssmxli.food.model.CorteCajaModel;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("corteCajaConverter")
public class CorteCajaConverter {
    public CorteCaja convertCorteCajaModel2CorteCaja(CorteCajaModel corteCajaModel)throws Exception {
        String fecha = corteCajaModel.getFecha();
        Date fech = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        CorteCaja corteCaja = new CorteCaja();

        corteCaja.setId(corteCajaModel.getId());
        corteCaja.setFecha(fech);
        corteCaja.setCaja(corteCajaModel.getCaja());
        corteCaja.setCorte(corteCajaModel.getCorte());
        corteCaja.setVenta(corteCajaModel.getVenta());
        corteCaja.setTarjeta(corteCajaModel.getTarjeta());
        corteCaja.setDiferencia(corteCajaModel.getDiferencia());

        /*corteCaja.setElaboro(corteCajaModel.getElaboro());
        corteCaja.setReviso(corteCajaModel.getReviso());*/
        return corteCaja;
    }
    public CorteCajaModel convertCorteCaja2CorteCajaModel(CorteCaja corteCaja){
        CorteCajaModel corteCajaModel = new CorteCajaModel();
        corteCajaModel.setId(corteCaja.getId());
        corteCajaModel.setFecha(corteCaja.getFecha().toString());
        corteCajaModel.setCaja(corteCaja.getCaja());
        corteCajaModel.setCorte(corteCaja.getCorte());
        corteCajaModel.setVenta(corteCaja.getVenta());
        corteCajaModel.setTarjeta(corteCaja.getTarjeta());
        corteCajaModel.setDiferencia(corteCaja.getDiferencia());
        return corteCajaModel;
    }


}
