package mx.ssmxli.food.Entity;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "precio", cascade = CascadeType.ALL)
    private Set<Alimento> alimentos;

    public Precio(String tamano, double precio) {
        this.tamano = tamano;
        this.precio = precio;
    }

    public Precio(){

    }
}
