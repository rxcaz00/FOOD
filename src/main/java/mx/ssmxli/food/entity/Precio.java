package mx.ssmxli.food.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="precio")
public class Precio {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "tamano")
    private String tamano;

    @Column(name = "precio")
    private double precio;

    @ManyToOne
    @JoinColumn
    private Alimento alimento;

    public Precio(){

    }
}
