package mx.ssmxli.food.entity.sequence;

import lombok.Data;
import lombok.ToString;
import mx.ssmxli.food.entity.Alimento;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad estilo sequence
 * Utilizada para llevar control de los identificadores de nombres de Alimento
 * */
@Data
@Entity
@Table(name = "nombre_sequence")
public class NombreSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "valor")
    private int valor;
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column(name = "habilitado")
    private boolean habilitado;

   /* Quite esa parte por problemas a la hora de mostrarlos por la categoria seleccionada
   //Categoria a la que pertenece este Nombre
    @ManyToOne
    @JoinColumn
    private CategoriaSequence categoria = new CategoriaSequence();*/

    @ToString.Exclude
    @OneToMany(mappedBy = "nombreSequence", cascade = CascadeType.ALL)
    private List<Alimento> alimentos = new ArrayList<>();

    public NombreSequence(String nombre){
        this.nombre = nombre;
    }

    public NombreSequence(String nombre, boolean habilitado) {
        this.habilitado = habilitado;
        this.nombre = nombre;
    }

    public NombreSequence() {
    }
}
