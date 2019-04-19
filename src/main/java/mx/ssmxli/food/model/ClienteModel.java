package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class ClienteModel {
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String RFC;

    public ClienteModel(){

    }
}
