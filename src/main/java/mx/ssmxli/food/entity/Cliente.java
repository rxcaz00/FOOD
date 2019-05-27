package mx.ssmxli.food.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @Column(name = "telefono", unique = true, nullable = false, length = 10)
    private String telefono;
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
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "correo", nullable = true)
    private String correo;//Correo del cliente, que puede ser opcional
    @Column(name = "RFC", length = 15, nullable = true)
    private String RFC;//El RFC del cliente, que puede ser opcional
    @Column(name = "puntos")
    private double puntos;//Se obtiene un porcentaje de la compra como puntos.

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Recibo> recibos;//Las compras que ha realizado el cliente

    public Cliente(String nombre, String direccion, String telefono, String correo, String RFC, double puntos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.RFC = RFC;
        this.puntos = puntos;
    }

    public Cliente(String nombre, String direccion, String correo, String telefono, double puntos){
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.puntos = puntos;
    }

    public Cliente(){}
}
