package mx.ssmxli.food.entity.sequence;

import lombok.Data;
import lombok.ToString;
import mx.ssmxli.food.entity.Alimento;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

   /*Quite esa parte por problemas a la hora de mostrarlos por la categoria seleccionada
    //Categoria a la que pertenece este tamaño
    @ManyToOne
    @JoinColumn
    private CategoriaSequence categoria = new CategoriaSequence();*/

    @ToString.Exclude
    @OneToMany(mappedBy = "tamanoSequence", cascade = CascadeType.ALL)
    private List<Alimento> alimentos = new ArrayList<>();

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
