package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class UsuarioModel {
    private int id;
    private String usuario;
    private String password;
    private String nivel;

    public UsuarioModel(){

    }
}
