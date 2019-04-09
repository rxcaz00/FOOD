package mx.ssmxli.food.Entity;

import lombok.Data;

import javax.persistence.*;
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
    private String nombre;
    @Column(name = "fechaI")
    private String fechaI;
    @Column(name = "caducidad")
    private String caducidad;
    @Column(name = "precio")
    private String precio;

    @ManyToMany(mappedBy = "promocions")
    private Set<Alimento> alimentos = new HashSet<>();


    public Promocion(String nombre, String fechaI, String caducidad, String precio) {
        this.nombre = nombre;
        this.fechaI = fechaI;
        this.caducidad = caducidad;
        this.precio = precio;
    }



    public Promocion(){}
}
