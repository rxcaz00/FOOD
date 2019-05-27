package mx.ssmxli.food.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
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

    @ToString.Exclude
    @OneToMany(mappedBy = "elaboro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CorteCaja> corteCajaElaborados;//CorteCaja que este usuario ha elaborado

    @ToString.Exclude
    @OneToMany(mappedBy = "reviso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CorteCaja> corteCajaRevisados;//CorteCaja que este usuario ha revisado

    @ToString.Exclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Recibo> recibos;

    public Usuario(String usuario, String password, String nivel) {
        this.usuario = usuario;
        this.password = password;
        this.nivel = nivel;
    }

    public Usuario(){

    }
}
