package mx.ssmxli.food.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
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
    @Column(name = "descripcion")
    private String descripcion;//breve descripcion del alimento
    @Column(name = "categoria")
    private String categoria;//En que categoria del menu se encuentra el alimento
    @Column(name = "tamano", nullable = true)
    private String tamano;//tamaño del alimento
    @Column(name = "precio")
    private double precio;//precio del alimento
    @Column(name = "habilitado")
    private boolean habilitado; //Variable para borrado logico

    @ManyToMany(mappedBy = "alimentos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Promocion> promociones;//Las promociones en las que aplica el alimento

    @OneToMany(mappedBy = "alimento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContenidoRecibo> contenidosRecibo;//Las veces que se ha vendido el alimento

    public Alimento(int id, String nombre, String descripcion, String categoria, String tamano, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.tamano = tamano;
        this.precio = precio;
    }

    public Alimento(){}
}


