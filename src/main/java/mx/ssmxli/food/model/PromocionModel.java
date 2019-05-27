package mx.ssmxli.food.model;

import lombok.Data;


import java.util.List;

@Data
public class PromocionModel {
    private int id;
    private String nombre;
    private String fechaI;
    private String fechaF;
    private double precio;
    private String disponibilidad;
    private List<AlimentoModel> alimentos;
    private boolean [] dias = {false,false,false,false,false,false,false};//se asigna el valor de false


    public PromocionModel(){

    }
}
