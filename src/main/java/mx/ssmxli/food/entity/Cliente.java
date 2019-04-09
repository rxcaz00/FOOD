package mx.ssmxli.food.entity;


import lombok.Data;

import javax.persistence.*;

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
    @Column(name = "RFC", length = 13)
    private String RFC;

    public Cliente(String nombre, String direccion, String telefono, String correo, String RFC) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.RFC = RFC;
    }

    public Cliente(String nombre, String direccion, String correo, String telefono){
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Cliente(){}
}
