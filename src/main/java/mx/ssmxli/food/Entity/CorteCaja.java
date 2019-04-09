package mx.ssmxli.food.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="cortecaja")
public class CorteCaja {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "caja")
    private double caja;
    @Column(name="corte")
    private double corte;
    @Column(name = "venta")
    private double venta;
    @Column(name = "tarjeta")
    private double tarjeta;
    @Column(name = "pago")
    private  double pago;
    @Column(name = "compra")
    private double compra;
    @Column(name = "diferencia")
    private double diferencia;
    @Column(name = "elaboro")
    private String elaboro;
    @Column(name = "reviso")
    private String reviso;

    public CorteCaja(){}

    public CorteCaja(String fecha, double caja, double corte, double venta, double tarjeta, double pago, double compra, double diferencia, String elaboro, String reviso) {
        this.fecha = fecha;
        this.caja = caja;
        this.corte = corte;
        this.venta = venta;
        this.tarjeta = tarjeta;
        this.pago = pago;
        this.compra = compra;
        this.diferencia = diferencia;
        this.elaboro = elaboro;
        this.reviso = reviso;
    }


}
