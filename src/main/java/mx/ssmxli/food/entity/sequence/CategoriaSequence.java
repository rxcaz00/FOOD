package mx.ssmxli.food.entity.sequence;

import lombok.Data;
import lombok.ToString;
import mx.ssmxli.food.entity.Alimento;

import javax.persistence.*;
import java.util.ArrayList;
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

   /* Quite esa parte por problemas a la hora de mostrarlos por la categoria seleccionada
   //Nombres que pertenecen a esta categoria
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<NombreSequence> nombreSequences = new ArrayList<>();

    //Tamaños que pertenecen a esta categoria
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<TamanoSequence> tamanoSequences = new ArrayList<>();*/

    @ToString.Exclude
    @OneToMany(mappedBy = "categoriaSequence", cascade = CascadeType.ALL)
    private List<Alimento> alimentos = new ArrayList<>();

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
