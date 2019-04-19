package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class GastoModel {
    private int id;
    private double monto;
    private String fecha;
    private String descripcion;

    public GastoModel(){

    }
}
