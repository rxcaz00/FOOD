package mx.ssmxli.food.Entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "password")
    private String password;
    @Column(name = "nivel")
    private String nivel;

    public Usuario(){}

    public Usuario(String usuario, String password, String nivel) {
        this.usuario = usuario;
        this.password = password;
        this.nivel = nivel;
    }
}
