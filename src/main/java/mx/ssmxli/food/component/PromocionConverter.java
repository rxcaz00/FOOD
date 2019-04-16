package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Promocion;
import mx.ssmxli.food.model.PromocionModel;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("promocionConverter")
public class PromocionConverter {

    public Promocion convertToPromocionModel2Promocion(PromocionModel promocionModel)throws Exception {
        String fecha = promocionModel.getFechaI();
        Date fech = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        String fecha1 = promocionModel.getFechaF();
        Date fech1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);

        Promocion promocion = new Promocion();
        promocion.setId(promocionModel.getId());
        promocion.setNombre(promocionModel.getNombre());
        promocion.setFechaI(fech);
        promocion.setFechaF(fech1);
        promocion.setPrecio(promocionModel.getPrecio());
        promocion.setDisponibilidad(promocionModel.getDisponibilidad());
        promocion.setAlimentos(promocionModel.getAlimentos());
        return promocion;
    }

    public PromocionModel convertPromocion2PromocionModel(Promocion promocion){
        PromocionModel promocionModel = new PromocionModel();
        promocionModel.setId(promocion.getId());
        promocionModel.setNombre(promocion.getNombre());
        promocionModel.setFechaI(promocion.getFechaI().toString());
        promocionModel.setFechaF(promocion.getFechaF().toString());
        promocionModel.setPrecio(promocion.getPrecio());
        promocionModel.setDisponibilidad(promocion.getDisponibilidad());
        promocionModel.setAlimentos(promocion.getAlimentos());
        return promocionModel;
    }
}

