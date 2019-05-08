package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Promocion;

import java.util.HashSet;
import java.util.Set;

@Data
public class AlimentoModel {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private String tamano;
    private boolean habilitado;

    public AlimentoModel(){

    }
}
