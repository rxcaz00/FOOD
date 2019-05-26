package mx.ssmxli.food.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "contenidopromocion")
public class ContenidoPromocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "precio")
    private double precio;//El precio de la promocion al momento de la venta

    @ToString.Exclude
    @OneToMany(mappedBy = "contenidoPromocion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ContenidoRecibo> contenidosRecibo;//Que alimentos se seleccionaron para la promocion
                                                   //Se utiliza contenidoRecibo para poder poner ingredientes extra

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn
    private Recibo recibo;//A que recibo corresponde

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Promocion promocion;//La promocion que se esta vendiendo
}
