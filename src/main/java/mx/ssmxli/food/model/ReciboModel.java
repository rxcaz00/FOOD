package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class ReciboModel {
    public int id;
    public String fecha;
    public String notas;
    public double subtotal;
    public double total;

    public ReciboModel(){

    }
}
