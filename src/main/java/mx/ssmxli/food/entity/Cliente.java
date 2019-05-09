package mx.ssmxli.food.entity;


import lombok.Data;

import javax.persistence.*;
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
    private int puntos;//Se obtiene un porcentaje de la compra como puntos.

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Recibo> recibos;//Las compras que ha realizado el cliente

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
