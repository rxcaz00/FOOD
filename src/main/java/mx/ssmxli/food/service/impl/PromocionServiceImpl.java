package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.PromocionConverter;
import mx.ssmxli.food.entity.Promocion;
import mx.ssmxli.food.model.PromocionModel;
import mx.ssmxli.food.repository.PromocionRepository;
import mx.ssmxli.food.service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service("promocionServiceImpl")
public class PromocionServiceImpl implements PromocionService {

    @Autowired
    @Qualifier("promocionRepository")
    private PromocionRepository promocionRepository;

    @Autowired
    @Qualifier("promocionConverter")
    private PromocionConverter promocionConverter;

    public PromocionModel addPromocion(PromocionModel promocionModel) throws Exception {
        Promocion temp = promocionConverter.convertPromocionModel2Promocion(promocionModel);
        Promocion promocion = promocionRepository.save(temp);
        return promocionConverter.convertPromocion2PromocionModel(promocion);
    }

    public List<PromocionModel> listAllPromociones() {
        List<Promocion> promociones = promocionRepository.findAll();
        List<PromocionModel> promocionesModel = new ArrayList();
        for(Promocion promocion : promociones)
            promocionesModel.add(promocionConverter.convertPromocion2PromocionModel(promocion));
        return promocionesModel;
    }

    public Promocion findPromocionById(int id) {
        return promocionRepository.findById(id);
    }

    public PromocionModel findPromocionByIdModel(int id){
        return promocionConverter.convertPromocion2PromocionModel(findPromocionById(id));
    }

    public Promocion convertPromocionModel2Promocion(PromocionModel promocionModel) throws Exception{
        return promocionConverter.convertPromocionModel2Promocion(promocionModel);
    }

    /**
     * Similar a listAllPromociones, pero solo regresa las promociones que su Dia de la semana y su periodo
     * de vigencia sean validos.
     *
     * @return List<PromocionModel>
     * @author Andrés
     */
    public List<PromocionModel> listAllPromocionesValidas(){
        LocalDate hoy = LocalDate.now();
        List<PromocionModel> allPromociones = listAllPromociones();
        List<PromocionModel> promociones = new ArrayList<>();
        for (PromocionModel promo: allPromociones) {
            //En vez de parsear de String a Date las fechas de inicio y fin, simplemente obtengo la entidad promocion a
            //partir de el ID de PromocionModel.
            //Tenia una funcion a traves de promocionService para buscar a traves del ID del modelo, pero para reducir las
            //consultas a base de datos preferi convertir de PromocionModel a Promocion.
            Promocion temp = null;
            try {
                temp = findPromocionById(convertPromocionModel2Promocion(promo).getId());
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Parsea las fechas de inicio y de fin de la promocion. La fecha de la promocion esta en Date
            //pero la fecha actual esta en LocalDate.

            //Se necesita obtener el Instant de la fecha, luego definir la Zona a traves del ID de la zona predeterminada del sistema.
            //Por ultimo se parsea a LocalDate.
            LocalDate fechaI = temp.getFechaI().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaF = temp.getFechaF().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            //Compara las fechas de la promocion con la de hoy. Si hoy es igual o mayor a la fecha de inicio y menor a la fecha
            // de fin entonces se pasa a la siguiente condicion
            if ((hoy.isAfter(fechaI)||hoy.isEqual(fechaI))&&(hoy.isBefore(fechaF)||hoy.isEqual(fechaF)))
                //Calendar.DAY_OF_WEEK regresa el dia de la semana en formato numero del 1 al 7, de Lunes a Domingo
                if (promo.getDias()[(hoy.getDayOfWeek().getValue() - 1)]) //Al ser de 1 a 7, le restamos 1 para que empiece en 0
                    //Como la vigencia y disponibilidad de la promocion son validas, entonces se agregara al model
                    promociones.add(promo);
        }//end foreach

        return promociones;
    }
}