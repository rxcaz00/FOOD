package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class ContenidoReciboModel {
    private int id;
    private double precio;
    private String nombreAlimento;
    private int idAlimento;
    private int idRecibo;
    private int idContenidoPromocion;
    private String nombreContenidoPromocion;
    private int idIngredienteExtra;
    private String nombreIngredienteExtra;

    public ContenidoReciboModel(){}
}
