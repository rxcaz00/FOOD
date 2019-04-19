package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.model.AlimentoModel;
import org.springframework.stereotype.Component;

@Component("alimentoConverter")
public class AlimentoConverter {
    public Alimento convertAlimentoModel2Alimento(AlimentoModel alimentoModel){
        Alimento alimento = new Alimento();
        alimento.setId(alimentoModel.getId());
        alimento.setIngredientes(alimentoModel.getIngredientes());
        alimento.setNombre(alimentoModel.getNombre());
        alimento.setPromociones(alimentoModel.getPromociones());
        alimento.setPrecio(alimentoModel.getPrecio());
        alimento.setCategoria(alimentoModel.getCategoria());
        return alimento;
    }

    public AlimentoModel convertAlimento2AlimentoModel(Alimento alimento){
        AlimentoModel alimentoModel = new AlimentoModel();
        alimentoModel.setId(alimento.getId());
        alimentoModel.setIngredientes(alimento.getIngredientes());
        alimentoModel.setNombre(alimento.getNombre());
        alimentoModel.setPromociones(alimento.getPromociones());
        alimentoModel.setPrecio(alimento.getPrecio());
        alimentoModel.setCategoria(alimento.getCategoria());
        return alimentoModel;
    }
}
