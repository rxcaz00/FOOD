package mx.ssmxli.food.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="alimento")
public class Alimento {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ingredientes")
    private String ingredientes;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "tamano", nullable = true)
    private String tamano;
    @Column(name = "precio")
    private double precio;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "alimento_promocion",
            joinColumns = @JoinColumn(name = "alimento_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "promocion_id", referencedColumnName = "id"))
    private Set<Promocion> promociones;

    public Alimento(String nombre, String ingredientes, String categoria, String tamano, double precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.categoria = categoria;
        this.tamano = tamano;
        this.precio = precio;
    }

        public Alimento(){

        }
}


