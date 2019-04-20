package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Date fecha; //Fecha del corte
    @Column(name = "caja")
    private double caja; //El dinero inicial. Normalmente es $500 pesos
    @Column(name="corte")
    private double corte; //Dinero al final del dia
    @Column(name = "venta")
    private double venta; //El total de lo que se vendio ese dia
    @Column(name = "tarjeta")
    private double tarjeta; //Las ventas por tarjeta, a traves de izzetle
    @Column(name = "diferencia")
    private double diferencia; //La diferencia entre el corte y las ventas y gastos.
    @Column(name = "pago")
    private double pago; //
    @Column(name = "compra")
    private double compra; //

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elaboro")
    private Usuario elaboro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviso")
    private Usuario reviso;

    public CorteCaja(){}

    public CorteCaja(Date fecha, double caja, double corte, double venta, double tarjeta, double diferencia, double pago, double compra) {
        this.fecha = fecha;
        this.caja = caja;
        this.corte = corte;
        this.venta = venta;
        this.tarjeta = tarjeta;
        this.diferencia = diferencia;
        this.pago = pago;
        this.compra = compra;
    }


}
