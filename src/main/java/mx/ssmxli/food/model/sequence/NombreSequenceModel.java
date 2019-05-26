package mx.ssmxli.food.model.sequence;

import lombok.Data;

@Data
public class NombreSequenceModel {
    private int valor;
    private String nombre;
    private boolean habilitado;
   /*private CategoriaSequenceModel categoria;*/
    /*Quite esa parte por problemas a la hora de mostrarlos por la categoria seleccionada
    private int valor_Categoria;
    private String nombre_Categoria;*/

    public NombreSequenceModel() {
    }
}
