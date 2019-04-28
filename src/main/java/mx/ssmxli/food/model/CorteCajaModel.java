package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Usuario;

@Data
public class CorteCajaModel {
    private int id;
    private String fecha;
    private double dineroInicial;
    private double corte;
    private double efectivo;
    private double tarjeta;
    private double diferencia;
    private double pago;
    private double compra;
    private String elaboro;
    private String reviso;

    public CorteCajaModel(){

    }
}
