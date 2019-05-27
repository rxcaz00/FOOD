package mx.ssmxli.food.model;

import lombok.Data;
import mx.ssmxli.food.entity.Recibo;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

@Data
public class ClienteModel {
    private String nombre;
    private String apellidos;
    /*
    private String apellidoPaterno;
    private String apellidoMaterno;
     */
    private String direccion;
    private String telefono;
    private String correo;
    private String RFC;
    private double puntos;
    private List<ReciboModel> recibos;

    public ClienteModel(){

    }
}
