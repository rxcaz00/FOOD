package mx.ssmxli.food.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class ContenidoPromocionModel {
    private int id;
    private double precio;
    @ToString.Exclude
    private List<ContenidoReciboModel> contenidosRecibo;
    private int idRecibo;
    private int idPromocion;
    private String nombrePromocion;

    public ContenidoPromocionModel(){}
}
