package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class ComandaModel {

    private int id;
    private String fecha;
    private int numeroMesa;
    private String notas;

    public ComandaModel(){

    }
}
