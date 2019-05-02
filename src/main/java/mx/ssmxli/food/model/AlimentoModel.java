package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Promocion;

import java.util.HashSet;
import java.util.Set;

@Data
public class AlimentoModel {
    private int id;
    private String nombre;
    private String ingredientes;
    private double precio;
    private String categoria;
    private String tamano;

    public AlimentoModel(){

    }
}
