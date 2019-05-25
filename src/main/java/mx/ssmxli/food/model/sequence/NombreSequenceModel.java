package mx.ssmxli.food.model.sequence;

import lombok.Data;

@Data
public class NombreSequenceModel {
    private int valor;
    private String nombre;
    private boolean habilitado;
   /*private CategoriaSequenceModel categoria;*/
    private int valor_Categoria;
    private String nombre_Categoria;

    public NombreSequenceModel() {
    }
}
