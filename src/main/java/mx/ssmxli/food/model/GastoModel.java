package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class GastoModel {
    public int id;
    public double monto;
    public String fecha;
    public String descripcion;

    public GastoModel(){

    }
}
