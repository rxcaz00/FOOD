package mx.ssmxli.food.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "ingredienteextra")
public class IngredienteExtra {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio")
    private double precio;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ingredienteextra_contenidorecibo",
                joinColumns = @JoinColumn(name = "ingredienteextra_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "contenidorecibo_id", referencedColumnName = "id"))
    private Set<ContenidoRecibo> contenidosPedido;


    public IngredienteExtra(String nombre, double precio, Set<ContenidoRecibo> contenidosPedido) {
        this.nombre = nombre;
        this.precio = precio;
        this.contenidosPedido = contenidosPedido;
    }

    public IngredienteExtra() {
    }
}
