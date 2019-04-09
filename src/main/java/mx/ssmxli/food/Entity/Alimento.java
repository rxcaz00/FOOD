package mx.ssmxli.food.Entity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
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


    @ManyToOne
    @JoinColumn
    private Precio precio;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "alimento_promocion",
            joinColumns = @JoinColumn(name = "alimento_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "promocion_id", referencedColumnName = "id"))
    private Set<Promocion> promocions;

    public Alimento(String nombre, String ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

        public Alimento(){

        }
    }


