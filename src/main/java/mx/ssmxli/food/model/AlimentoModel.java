package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Promocion;

import java.util.HashSet;
import java.util.Set;

@Data
public class AlimentoModel {
    private int id;
    private int nombre_Valor;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private int categoria_Valor;
    private String tamano;
    private int tamano_Valor;
    private boolean habilitado;

    public AlimentoModel(){

    }
}

//17