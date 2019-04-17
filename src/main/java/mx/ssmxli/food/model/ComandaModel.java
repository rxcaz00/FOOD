package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class ComandaModel {

    public int id;
    public String fecha;
    public int numeroMesa;
    public String notas;

    public ComandaModel(){

    }
}
