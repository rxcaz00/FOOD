package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.model.AlimentoModel;
import org.springframework.stereotype.Component;

@Component("alimentoConverter")
public class AlimentoConverter {
    public Alimento convertToAlimentoModel2Alimento(AlimentoModel alimentoModel){
        Alimento alimento = new Alimento();
        alimento.setId(alimentoModel.getId());
        alimento.setIngredientes(alimentoModel.getIngredientes());
        alimento.setNombre(alimentoModel.getNombre());
        alimento.setPrecios(alimentoModel.getPrecios());
        alimento.setPromociones(alimentoModel.getPromociones());
        return alimento;
    }

    public AlimentoModel convertAlimento2AlimentoModel(Alimento alimento){
        AlimentoModel alimentoModel = new AlimentoModel();
        alimentoModel.setId(alimento.getId());
        alimentoModel.setIngredientes(alimento.getIngredientes());
        alimentoModel.setNombre(alimento.getNombre());
        alimentoModel.setPrecios(alimento.getPrecios());
        alimentoModel.setPromociones(alimento.getPromociones());
        return alimentoModel;
    }
}
