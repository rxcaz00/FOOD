package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Recibo;

import java.util.Set;

@Data
public class ClienteModel {
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String RFC;
    private int puntos;
    private Set<Recibo> recibos;

    public ClienteModel(){

    }
}
