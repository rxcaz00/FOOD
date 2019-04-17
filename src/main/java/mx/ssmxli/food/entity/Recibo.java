package mx.ssmxli.food.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Usuario usuario;

    @OneToMany(mappedBy = "recibo", cascade = CascadeType.ALL)
    private Set<ContenidoPedido> contenidosRecibo;

    @OneToOne(mappedBy = "recibo")
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
