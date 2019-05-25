package mx.ssmxli.food.entity.sequence;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Entidad estilo sequence
 * Utilizada para llevar control de los identificadores de categorias de Alimento
 * */
@Data
@Entity
@Table(name = "categoria_sequence")
public class CategoriaSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "valor")
    private int valor;
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column(name = "habilitado")
    private boolean habilitado;

    //Nombres que pertenecen a esta categoria
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<NombreSequence> nombreSequences;

    //Tamaños que pertenecen a esta categoria
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<TamanoSequence> tamanoSequences;

    public CategoriaSequence(String nombre){
        this.nombre = nombre;
    }

    public CategoriaSequence(String nombre, boolean habilitado) {
        this.habilitado = habilitado;
        this.nombre = nombre;
    }

    public CategoriaSequence() {
    }
}
