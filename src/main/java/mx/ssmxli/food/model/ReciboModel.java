package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class ReciboModel {
    private int id;
    private String fecha;
    private String notas;
    private double subtotal;
    private double total;

    public ReciboModel(){

    }
}
