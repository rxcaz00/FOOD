package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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
    private String nombre;//Nombre de la promocion
    @Column(name = "fechaI")
    private Date fechaI;//Desde que fecha es valida la promocion
    @Column(name = "fechaF")
    private Date fechaF; //Hasta que fecha es valida la promocion
    @Column(name = "precio")
    private double precio; //El precio de la promocion
    @Column(name = "disponibilidad")
    private String disponibilidad; //Un string con los dias separados con ;

    @ManyToMany(mappedBy = "promociones")
    private Set<Alimento> alimentos = new HashSet<>(); // Los productos que aplican en la promocion


    public Promocion(String nombre, Date fechaI, Date fechaF, double precio, String disponibilidad){
        this.nombre = nombre;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public Promocion(){}
}
