package mx.ssmxli.food.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="alimento")
public class Alimento {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;//nombre del alimento
    @Column(name = "ingredientes")
    private String ingredientes;//ingredientes del alimento
    @Column(name = "categoria")
    private String categoria;//En que categoria del menu se encuentra el alimento
    @Column(name = "tamano", nullable = true)
    private String tamano;//tamaño del alimento
    @Column(name = "precio")
    private double precio;//precio del alimento
    @Column(name = "habilitado")
    private boolean habilitado; //Variable para borrado logico

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "alimento_promocion",
            joinColumns = @JoinColumn(name = "alimento_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "promocion_id", referencedColumnName = "id"))
    private Set<Promocion> promociones;

    public Alimento(int id,String nombre, String ingredientes, String categoria, String tamano, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.categoria = categoria;
        this.tamano = tamano;
        this.precio = precio;
    }

        public Alimento(){

        }
}


