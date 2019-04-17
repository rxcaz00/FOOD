package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "recibo")
public class Recibo {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "total")
    private double total;

    @Column(name = "subtotal")
    private double subtotal;

    @Column(name = "notas")
    private String notas;

    @ManyToOne
    @JoinColumn
    private Cliente cliente;

    public Recibo(){

    }
}
