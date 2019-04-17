package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @Column(name = "telefono")
    private String telefono;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "correo")
    private String correo;

    @Column(name = "RFC", length = 15)
    private String RFC;

    @Column(name = "puntos")
    private int puntos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Recibo> recibos;

    public Cliente(){

    }
}
