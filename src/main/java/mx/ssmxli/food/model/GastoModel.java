package mx.ssmxli.food.model;

import lombok.Data;

import java.util.Date;

@Data
public class GastoModel {
    private int id;
    private double monto;
    private String fecha;
    private String descripcion;
    private char tipo;
    private String usuario;


    public GastoModel(){

    }
}
