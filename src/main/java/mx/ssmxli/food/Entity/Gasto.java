package mx.ssmxli.food.Entity;

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
    private String monto;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "descripcion")
    private String descripcion;

    public Gasto(String monto, Date fecha, String descripcion) {
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Gasto(){

    }
}
