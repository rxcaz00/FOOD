package mx.ssmxli.food.model;

import lombok.Data;

@Data
public class UsuarioModel {
    public int id;
    public String usuario;
    public String password;
    public String nivel;

    public UsuarioModel(){

    }
}
