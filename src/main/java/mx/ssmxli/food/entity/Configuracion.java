package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "configuracion")
public class Configuracion {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "iva")
    private double iva;//El IVA actual
    @Column(name = "retribucion")
    private double retribucion;//El porcentaje de puntos que se regresaran por cada compra
    @Column(name = "dineroInicial")
    private double dineroInicial;//El dinero que se deja en caja al iniciar el turno/dia

    public Configuracion() {

    }
}
