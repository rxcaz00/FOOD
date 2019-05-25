package mx.ssmxli.food.model.sequence;

import lombok.Data;
import mx.ssmxli.food.entity.sequence.TamanoSequence;

import java.util.List;

@Data
public class CategoriaSequenceModel {
    private int valor;
    private String nombre;
    private boolean habilitado;
    private List<NombreSequenceModel> nombreSequenceModels;
    private List<TamanoSequenceModel> tamanoSequenceModels;

    public CategoriaSequenceModel() {
    }
}
