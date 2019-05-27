package mx.ssmxli.food.model;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioModel {
    private String usuario;
    private String nombre;
    private String apellidos;
    /*
    private String apellidoPaterno;
    private String apellidoMaterno;
     */
    private String password;
    private String nivel;
    private List<ReciboModel> recibos;

    public UsuarioModel(){

    }
}
