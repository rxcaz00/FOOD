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

    @Override
    public PromocionModel addPromocion(PromocionModel promocionModel) throws Exception {
        Promocion temp=promocionConverter.convertToPromocionModel2Promocion(promocionModel);
        Promocion promocion = promocionRepository.save(temp);
        return promocionConverter.convertPromocion2PromocionModel(promocion);
    }

    @Override
    public List<PromocionModel> listAllPromociones() {
        List<Promocion> promociones = promocionRepository.findAll();
        List<PromocionModel> promocionesModel = new ArrayList();
        for(Promocion promocion : promociones){
            promocionesModel.add(promocionConverter.convertPromocion2PromocionModel(promocion));

        }
        return promocionesModel;
    }

    @Override
    public Promocion findPromocionById(int id) {
        return promocionRepository.findById(id);
    }

    public PromocionModel findPromocionByIdModel(int id){
        return promocionConverter.convertPromocion2PromocionModel(findPromocionById(id));
    }

    @Override
    public void removePromocion(int id) {
        Promocion promocion  = findPromocionById(id);
        if(promocion != null){
            promocionRepository.delete(findPromocionById(id));
        }
    }
}
