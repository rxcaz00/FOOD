package mx.ssmxli.food.model;

import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.entity.Promocion;

import java.util.HashSet;
import java.util.Set;

public class PromocionModel {
    public int id;
    public String fechaI;
    public String getFechaF;
    public double precio;
    private Set<Alimento> alimentos = new HashSet<Alimento>();

    public PromocionModel(){

    }
}
