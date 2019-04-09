package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class ClienteModel {
    public String nombre;
    public String direccion;
    public String telefono;
    public String correo;
    public String RFC;

    public ClienteModel(){

    }
}
