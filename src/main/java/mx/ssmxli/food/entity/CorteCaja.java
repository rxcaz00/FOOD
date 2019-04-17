package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="cortecaja")
public class CorteCaja {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "caja")
    private double caja;

    @Column(name="corte")
    private double corte;

    @Column(name = "venta")
    private double venta;

    @Column(name = "tarjeta")
    private double tarjeta;

    @Column(name = "pago")
    private double pago;

    @Column(name = "compra")
    private double compra;

    @Column(name = "diferencia")
    private double diferencia;

    @Column(name = "elaboro")
    private String elaboro;

    @Column(name = "reviso")
    private String reviso;

    public CorteCaja(){

    }
}
