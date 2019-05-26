package mx.ssmxli.food.entity;
import lombok.Data;
import mx.ssmxli.food.entity.sequence.CategoriaSequence;
import mx.ssmxli.food.entity.sequence.NombreSequence;
import mx.ssmxli.food.entity.sequence.TamanoSequence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="alimento")
public class Alimento {

    @Id
    @Column(name = "id")
    private int id;
    /*@Column(name = "nombre")
    private String nombre;//nombre del alimento*/
    @Column(name = "descripcion")
    private String descripcion;//breve descripcion del alimento
    /*@Column(name = "categoria")
    private String categoria;//En que categoria del menu se encuentra el alimento
    @Column(name = "tamano")
    private String tamano;//tamaño del alimento*/
    @Column(name = "precio")
    private double precio;//precio del alimento
    @Column(name = "habilitado")
    private boolean habilitado; //Variable para borrado logico

    @ManyToOne
    @JoinColumn
    private CategoriaSequence categoriaSequence; //La categoria a la que pertenece el alimento

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private NombreSequence nombreSequence;//El nombre que tiene el alimento

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private TamanoSequence tamanoSequence;//El tamaño que tiene el alimento

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "alimento_promocion",
            joinColumns = @JoinColumn(name = "alimento_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "promocion_id", referencedColumnName = "id"))
    private Set<Promocion> promociones = new HashSet<>();//Las promociones en las que aplica el alimento

    @OneToMany(mappedBy = "alimento", cascade = CascadeType.ALL)
    private List<ContenidoRecibo> contenidosRecibo = new ArrayList<>();//Las veces que se ha vendido el alimento

    /*public Alimento(int id, String nombre, String descripcion, String categoria, String tamano, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.tamano = tamano;
        this.precio = precio;
    }*/

    public Alimento(int id, String descripcion, double precio, boolean habilitado, CategoriaSequence categoriaSequence, NombreSequence nombreSequence, TamanoSequence tamanoSequence, Set<Promocion> promociones, List<ContenidoRecibo> contenidosRecibo) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.habilitado = habilitado;
        this.categoriaSequence = categoriaSequence;
        this.nombreSequence = nombreSequence;
        this.tamanoSequence = tamanoSequence;
        this.promociones = promociones;
        this.contenidosRecibo = contenidosRecibo;
    }

    public Alimento(){}
}


