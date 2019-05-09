package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "recibo")
public class Recibo {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "fecha")
    private Date fecha;//La fecha de venta
    @Column(name = "total")
    private double total;//El total con IVA
    @Column(name = "subtotal")
    private double subtotal;//El total sin el IVA
    @Column(name = "notas", nullable = true)
    private String notas;//Alguna indicacion especial en la entrega
    @Column(name = "metodoPago")
    private char metodoPago;//Si se pago con (T)arjeta o (E)fectivo
    @Column(name = "tipoOrden")
    private char tipoOrden;//Si es (L)ocal o a (D)omicilio

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Usuario usuario;

    @OneToMany(mappedBy = "recibo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ContenidoRecibo> contenidosRecibo;

    @OneToOne(mappedBy = "recibo", fetch = FetchType.EAGER)
    private Comanda comanda;

    public Recibo(Date fecha, double total, double subtotal, String notas, Cliente cliente) {
        this.fecha = fecha;
        this.total = total;
        this.subtotal = subtotal;
        this.notas = notas;
        this.cliente = cliente;
    }

    public Recibo(){}
}
