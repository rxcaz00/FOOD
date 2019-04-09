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
    @Column(name = "monto")
    private double monto;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "descripcion")
    private String descripcion;

    public Gasto(Double monto, Date fecha, String descripcion) {
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Gasto(){}
}
