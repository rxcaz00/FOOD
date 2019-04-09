package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Promocion;

import java.util.HashSet;
import java.util.Set;

@Data
public class AlimentoModel {
    public int id;
    public String nombre;
    public String ingredientes;
    private Set<Promocion> promociones = new HashSet<Promocion>();

    public AlimentoModel(){

    }
}
