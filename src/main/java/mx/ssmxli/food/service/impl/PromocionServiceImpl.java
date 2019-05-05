package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.PromocionConverter;
import mx.ssmxli.food.entity.Promocion;
import mx.ssmxli.food.model.PromocionModel;
import mx.ssmxli.food.repository.PromocionRepository;
import mx.ssmxli.food.service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
}