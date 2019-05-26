package mx.ssmxli.food.model.sequence;

import lombok.Data;
import mx.ssmxli.food.entity.sequence.TamanoSequence;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoriaSequenceModel {
    private int valor;
    private String nombre;
    private boolean habilitado;

    /*Quite esa parte por problemas a la hora de mostrarlos por la categoria seleccionada
    private List<NombreSequenceModel> nombreSequenceModels = new ArrayList<>();
    private List<TamanoSequenceModel> tamanoSequenceModels = new ArrayList<>();*/

    public CategoriaSequenceModel() {
    }
}
