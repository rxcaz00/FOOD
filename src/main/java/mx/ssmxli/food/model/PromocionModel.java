package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.entity.Promocion;

import java.util.HashSet;
import java.util.Set;
@Data
public class PromocionModel {
    private int id;
    private String nombre;
    private String fechaI;
    private String fechaF;
    private double precio;
    private String disponibilidad;
    private Set<Alimento> alimentos = new HashSet<Alimento>();
    private boolean [] dias = {false,false,false,false,false,false,false};//se asigna el valor de false


    public PromocionModel(){

    }
}
