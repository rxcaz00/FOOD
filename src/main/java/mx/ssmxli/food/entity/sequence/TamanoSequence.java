package mx.ssmxli.food.entity.sequence;

import lombok.Data;

import javax.persistence.*;


/**
 * Entidad estilo sequence.
 * Utilizada para llevar control de los identificadores de tamaños de Alimento.
 * */
@Data
@Entity
@Table(name = "tamano_sequence")
public class TamanoSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "valor")
    private int valor;
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column(name = "habilitado")
    private boolean habilitado;

    //Categoria a la que pertenece este tamaño
    @ManyToOne
    @JoinColumn
    private CategoriaSequence categoria;

    public TamanoSequence(String nombre){
        this.nombre = nombre;
    }

    public TamanoSequence(String nombre, boolean habilitado) {
        this.nombre = nombre;
        this.habilitado = habilitado;
    }

    public TamanoSequence() {
    }
}
