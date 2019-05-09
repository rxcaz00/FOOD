package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="gasto")
public class Gasto {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "tipo")
    private char tipo;//Si es (P)ago o (C)ompra
    @Column(name = "monto")
    private double monto; //El monto del gasto
    @Column(name = "fecha")
    private Date fecha; //Fecha del gasto
    @Column(name = "descripcion")
    private String descripcion; //Descripcion del gasto

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn
    private Usuario usuario; //Quien registro el gasto

    public Gasto(Double monto, Date fecha, String descripcion, char tipo) {
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipo=tipo;
    }

    public Gasto(){}
}
