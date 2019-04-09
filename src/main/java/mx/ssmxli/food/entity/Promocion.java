package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="promocion")
public class Promocion {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fechaI")
    private Date fechaI;
    @Column(name = "caducidad")
    private Date caducidad;
    @Column(name = "precio")
    private double precio;
    @Column(name = "disponibilidad")
    private String disponibilidad;

    @ManyToMany(mappedBy = "promociones")
    private Set<Alimento> alimentos = new HashSet<>();


    public Promocion(String nombre, Date fechaI, Date caducidad, double precio){
        this.nombre = nombre;
        this.fechaI = fechaI;
        this.caducidad = caducidad;
        this.precio = precio;
    }



    public Promocion(){}
}
