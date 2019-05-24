package mx.ssmxli.food.model.sequence;

import lombok.Data;

@Data
public class TamanoSequenceModel {
    private int valor;
    private String nombre;
    private boolean enabled;
    private CategoriaSequenceModel categoria;
    /*private int valor_Categoria;
    private String nombre_Categoria;*/

    public TamanoSequenceModel() {
    }
}
