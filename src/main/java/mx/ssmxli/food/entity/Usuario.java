package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "password")
    private String password;
    @Column(name = "nivel")
    private String nivel;//Indica que autoridad tiene el usuario.
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    /*
    @Column(name = "apellidoPaterno")
    private String apellidoPaterno;
    @Column(name = "apellidoMaterno")
    private String apellidoMaterno;
     */

    @OneToMany(mappedBy = "elaboro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CorteCaja> corteCajaElaborados;//CorteCaja que este usuario ha elaborado

    @OneToMany(mappedBy = "reviso", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CorteCaja> corteCajaRevisados;//CorteCaja que este usuario ha revisado

    public Usuario(String usuario, String password, String nivel) {
        this.usuario = usuario;
        this.password = password;
        this.nivel = nivel;
    }

    public Usuario(){

    }
}
