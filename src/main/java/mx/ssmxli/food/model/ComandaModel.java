package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Recibo;

@Data
public class ComandaModel {

    private int id;
    private String fecha;
    private int numeroMesa;
    private String notas;
    private int recibo; //ID del recibo

    public ComandaModel(){

    }
}
