package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Usuario;

@Data
public class CorteCajaModel {
    private int id;
    private String fecha;
    private double caja;
    private double corte;
    private double venta;
    private double tarjeta;
    private double diferencia;
    private double pago;
    private double compra;
    private Usuario elaboro;
    private Usuario reviso;

    public CorteCajaModel(){

    }
}
