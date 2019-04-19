package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class CorteCajaModel {
    private int id;
    private String fecha;
    private double caja;
    private double corte;
    private double venta;
    private double tarjeta;
    private double pago;
    private double compra;
    private double diferencia;
    private String elaboro;
    private String reviso;

    public CorteCajaModel(){

    }
}
