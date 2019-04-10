package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.entity.Promocion;

import java.util.HashSet;
import java.util.Set;
@Data
public class PromocionModel {
    public int id;
    public String fechaI;
    public String getFechaF;
    public double precio;
    public String disponibilidad;
    private Set<Alimento> alimentos = new HashSet<Alimento>();

    public PromocionModel(){

    }
}
