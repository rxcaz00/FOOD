package mx.ssmxli.food.service;

import mx.ssmxli.food.entity.Promocion;
import mx.ssmxli.food.model.PromocionModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("promocionService")
public interface PromocionService {
    public abstract PromocionModel addPromocion(PromocionModel promocionModel) throws Exception;

    public abstract List<PromocionModel> listAllPromociones();

    public abstract Promocion findPromocionById(int id);

    public abstract PromocionModel findPromocionByIdModel(int id);

    public abstract Promocion convertPromocionModel2Promocion(PromocionModel promocionModel) throws Exception;
}


