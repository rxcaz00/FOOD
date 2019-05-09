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
    @Column(name = "dineroInicial")
    private double dineroInicial; //El dinero inicial.
    //Aunque siempre se pondra el valor de configuracion, es una sugerencia mas que una norma.
    @Column(name="corte")
    private double corte; //Dinero al final del dia
    @Column(name = "efectivo")
    private double efectivo; //El total de lo que se vendio en efectivo
    @Column(name = "tarjeta")
    private double tarjeta; //Las ventas por tarjeta, a traves de izzetle
    @Column(name = "diferencia")
    private double diferencia; //La diferencia entre el corte y las ventas (Efectivo + Tarjeta) y gastos.
    @Column(name = "pago")
    private double pago; //Los gastos que se realizan dentro del local (Pago a proveedores, salarios)
    @Column(name = "compra")
    private double compra; //Gastos que se realizan fuera de la pizzeria, por lo que se tiene que sacar el dinero del local.

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "elaboro")
    private Usuario elaboro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviso")
    private Usuario reviso;

    public CorteCaja(){}

    public CorteCaja(Date fecha, double dineroInicial, double corte, double efectivo, double tarjeta, double diferencia, double pago, double compra, Usuario elaboro, Usuario reviso) {
        this.fecha = fecha;
        this.dineroInicial = dineroInicial;
        this.corte = corte;
        this.efectivo = efectivo;
        this.tarjeta = tarjeta;
        this.diferencia = diferencia;
        this.pago = pago;
        this.compra = compra;
        this.elaboro = elaboro;
        this.reviso = reviso;
    }
}
