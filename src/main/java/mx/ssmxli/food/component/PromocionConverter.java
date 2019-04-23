package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Promocion;
import mx.ssmxli.food.model.PromocionModel;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("promocionConverter")
public class PromocionConverter {
//Conviersion de Modelo a Entidad
    public Promocion convertPromocionModel2Promocion(PromocionModel promocionModel)throws Exception {
        String fecha = promocionModel.getFechaI();
        Date fech = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        String fecha1 = promocionModel.getFechaF();
        Date fech1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);
        Promocion promocion = new Promocion();
        //funcion para tomar el valor del dia que selecciono
        StringBuilder disponibilidad = new StringBuilder();
        String [] letra = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        boolean[] arreglo = promocionModel.getDias();
        for(int x = 0; x < promocionModel.getDias().length; x++) {
            if (arreglo[x])
                disponibilidad.append(letra[x] + ";");//separar por ;
            else
                disponibilidad.append("-;");
        }

        promocion.setId(promocionModel.getId());
        promocion.setNombre(promocionModel.getNombre());
        promocion.setFechaI(fech);
        promocion.setFechaF(fech1);
        promocion.setPrecio(promocionModel.getPrecio());
        promocion.setDisponibilidad(disponibilidad.toString());
        promocion.setAlimentos(promocionModel.getAlimentos());
        return promocion;
    }
//Conversion de Entidad a Modelo
    public PromocionModel convertPromocion2PromocionModel(Promocion promocion){
        boolean [] arreglo = new boolean [7];
        PromocionModel promocionModel = new PromocionModel();
        promocionModel.setId(promocion.getId());
        promocionModel.setNombre(promocion.getNombre());
        promocionModel.setFechaI(promocion.getFechaI().toString());
        promocionModel.setFechaF(promocion.getFechaF().toString());
        promocionModel.setPrecio(promocion.getPrecio());
        String [] split = promocion.getDisponibilidad().split(";");//funcion para separa los dias por ;
        for (int y = 0; y<promocionModel.getDias().length; y++){
            if(!split[y].equals("-")){//tomar el dia que selecciono como true
                arreglo[y] = true;
            }
            else{
                arreglo[y]= false;
            }

        }
        promocionModel.setDias(arreglo);
        promocionModel.setAlimentos(promocion.getAlimentos());
        return promocionModel;
    }
}

