package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Precio;
import mx.ssmxli.food.entity.Promocion;

import java.util.HashSet;
import java.util.Set;

@Data
public class AlimentoModel {
    public int id;
    public String nombre;
    public String ingredientes;
    private Set<Promocion> promociones = new HashSet<Promocion>();
    private Set<Precio> precios = new HashSet<Precio>();

    public AlimentoModel(){

    }
}
