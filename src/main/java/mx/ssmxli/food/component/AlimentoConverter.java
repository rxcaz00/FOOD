package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.model.AlimentoModel;
import org.springframework.stereotype.Component;

@Component("alimentoConverter")
public class AlimentoConverter {
    /**
     *
     * @param alimentoModel
     *
     * Convierte un objeto de tipo "AlimentoModel" a un objeto de tipo "Alimento"
     *
     * @return Alimento
     * @author Danya
     */
    public Alimento convertAlimentoModel2Alimento(AlimentoModel alimentoModel){
        Alimento alimento = new Alimento();
        alimento.setId(alimentoModel.getId());
        alimento.setDescripcion(alimentoModel.getDescripcion());
        alimento.setNombre(alimentoModel.getNombre());
        alimento.setPrecio(alimentoModel.getPrecio());
        alimento.setCategoria(alimentoModel.getCategoria());
        alimento.setTamano(alimentoModel.getTamano());
        alimento.setHabilitado(alimentoModel.isHabilitado());
        return alimento;
    }

    /**
     *
     * @param alimento
     *
     * Convierte un objeto de tipo "Alimento" a un objeto de tipo "AlimentoModel"
     *
     * @return alimentoModel
     * @author Danya
     */
    public AlimentoModel convertAlimento2AlimentoModel(Alimento alimento){
        AlimentoModel alimentoModel = new AlimentoModel();
        alimentoModel.setId(alimento.getId());
        alimentoModel.setDescripcion(alimento.getDescripcion());
        alimentoModel.setNombre(alimento.getNombre());
        alimentoModel.setPrecio(alimento.getPrecio());
        alimentoModel.setCategoria(alimento.getCategoria());
        alimentoModel.setTamano(alimento.getTamano());
        alimentoModel.setHabilitado(alimento.isHabilitado());
        return alimentoModel;
    }
}
