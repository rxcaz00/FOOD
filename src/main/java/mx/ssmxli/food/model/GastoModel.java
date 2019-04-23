package mx.ssmxli.food.model;

import lombok.Data;

import java.util.Date;

@Data
public class GastoModel {
    public int id;
    public double monto;
    public String fecha;
    public String descripcion;
    public char tipo;

    public GastoModel(){

    }
}
