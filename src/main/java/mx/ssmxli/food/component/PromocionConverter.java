package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.entity.Promocion;
import mx.ssmxli.food.model.AlimentoModel;
import mx.ssmxli.food.model.PromocionModel;
import mx.ssmxli.food.service.AlimentoService;
import mx.ssmxli.food.service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("promocionConverter")
public class PromocionConverter {
    @Autowired
    @Qualifier("alimentoServiceImpl")
    private AlimentoService alimentoService;

    /**
     *
     * @param promocionModel
     *
     * Convierte un objeto de tipo "PromocionModel" a un objeto de "Promocion"
     *
     * @return promocion
     * @throws Exception
     * @author Diana
     */
    public Promocion convertPromocionModel2Promocion(PromocionModel promocionModel)throws Exception {
        String fecha = promocionModel.getFechaI();
        Date fech = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        String fecha1 = promocionModel.getFechaF();
        Date fech1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);
        Promocion promocion = new Promocion();
        List<Alimento> alimentos = new ArrayList<>();

        StringBuilder disponibilidad = new StringBuilder();
        String [] letra = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        boolean[] arreglo = promocionModel.getDias();
        for(int x = 0; x < promocionModel.getDias().length; x++) {
            if (arreglo[x])
                disponibilidad.append(letra[x] + ";");
            else
                disponibilidad.append("-;");
        }

        promocion.setId(promocionModel.getId());
        promocion.setNombre(promocionModel.getNombre());
        promocion.setFechaI(fech);
        promocion.setFechaF(fech1);
        promocion.setPrecio(promocionModel.getPrecio());
        promocion.setDisponibilidad(disponibilidad.toString());
        for(AlimentoModel alimentoModel : promocionModel.getAlimentos()){
            alimentos.add(alimentoService.convertAlimentoModel2Alimento(alimentoModel));
        }
        promocion.setAlimentos(alimentos);
        return promocion;
    }

    /**
     *
     * @param promocion
     *
     * Convierte un objeto de tipo "Promocion" a un objeto de tipo "PromocionModel"
     *
     * @return promocionModel
     * @author Diana
     */
    public PromocionModel convertPromocion2PromocionModel(Promocion promocion){
        boolean [] arreglo = new boolean [7];
        PromocionModel promocionModel = new PromocionModel();
        List<AlimentoModel> alimentoModels = new ArrayList<>();

        promocionModel.setId(promocion.getId());
        promocionModel.setNombre(promocion.getNombre());
        promocionModel.setFechaI(
                new SimpleDateFormat("yyyy-MM-dd")
                        .format(promocion.getFechaI()));
        promocionModel.setFechaF(
                new SimpleDateFormat("yyyy-MM-dd")
                        .format(promocion.getFechaF()));
        promocionModel.setPrecio(promocion.getPrecio());
        String [] split = promocion.getDisponibilidad().split(";");
        StringBuilder disponibilidad = new StringBuilder();
        for (int y = 0; y<promocionModel.getDias().length; y++){
            if(!split[y].equals("-")){
                arreglo[y] = true;
                disponibilidad.append(split[y] + ", ");
            }
            else{
                arreglo[y]= false;
            }
        }

        String disponibilidadF = disponibilidad.toString().substring(0,disponibilidad.length() - 2);
        promocionModel.setDisponibilidad(disponibilidadF);
        promocionModel.setDias(arreglo);

        for(Alimento alimento : promocion.getAlimentos()){
            alimentoModels.add(alimentoService.convertAlimento2AlimentoModel(alimento));
        }
        promocionModel.setAlimentos(alimentoModels);

        return promocionModel;
    }
}