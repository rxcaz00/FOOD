package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.model.AlimentoModel;
import mx.ssmxli.food.service.IdManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("alimentoConverter")
public class AlimentoConverter {
    @Autowired
    @Qualifier("idManagerServiceImpl")
    private IdManagerService idManagerService;

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
        alimento.setNombreSequence(idManagerService.findNombreByValor(alimentoModel.getNombre_Valor()));
        alimento.setPrecio(alimentoModel.getPrecio());
        alimento.setCategoriaSequence(idManagerService.findCategoriaByValor(alimentoModel.getCategoria_Valor()));
        alimento.setTamanoSequence(idManagerService.findTamanoByValor(alimentoModel.getTamano_Valor()));
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
        alimentoModel.setNombre(alimento.getNombreSequence().getNombre());
        alimentoModel.setNombre_Valor(alimento.getNombreSequence().getValor());
        alimentoModel.setPrecio(alimento.getPrecio());
        alimentoModel.setCategoria(alimento.getCategoriaSequence().getNombre());
        alimentoModel.setCategoria_Valor(alimento.getCategoriaSequence().getValor());
        alimentoModel.setTamano(alimento.getTamanoSequence().getNombre());
        alimentoModel.setTamano_Valor(alimento.getTamanoSequence().getValor());
        alimentoModel.setHabilitado(alimento.isHabilitado());
        return alimentoModel;
    }
}

//34
