package mx.ssmxli.food.Entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correo")
    private String correo;
    @Column(name = "RFC")
    private String RFC;

    public Cliente(String nombre, String direccion, String telefono, String correo, String RFC) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.RFC = RFC;
    }

    public Cliente(){}
}
