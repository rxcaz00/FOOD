package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "contenidorecibo")
public class ContenidoRecibo {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "precio")
    private double precio;//El precio. Se guarda aparte de el alimento por si cambia el precio
                        // del alimento no afecte recibos anteriores

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Alimento alimento;//El alimento que se esta vendiendo

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn
    private Recibo recibo;//A que recibo corresponde

    @ManyToMany(mappedBy = "contenidosPedido", fetch = FetchType.EAGER)
    private Set<IngredienteExtra> ingredientesExtra;


    public ContenidoRecibo(double precio, Alimento alimento, Recibo recibo) {
        this.precio = precio;
        this.alimento = alimento;
        this.recibo = recibo;
    }

    public ContenidoRecibo() {
    }
}
