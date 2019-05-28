package mx.ssmxli.food.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(name = "dineroRecibido")
    private double dineroRecibido;
    @Column(name = "notas", nullable = true)
    private String notas;//Alguna indicacion especial en la entrega
    @Column(name = "metodoPago")
    private char metodoPago;//Si se pago con (T)arjeta o (E)fectivo
    @Column(name = "tipoOrden")
    private char tipoOrden;//Si es (L)ocal, a (D)omicilio o para (R)ecoger
    @Column(name = "numeroMesa", nullable = true)
    private int numeroMesa;
    @Column(name = "direccionDeEnvio", nullable = true)
    private String direccionDeEnvio;


    @ManyToOne(optional = true)
    @JoinColumn
    private Cliente cliente;

    @ManyToOne
    @JoinColumn
    private Usuario usuario;

    @ToString.Exclude
    @OneToMany(mappedBy = "recibo", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)//Se utiliza esta linea para arreglar un bug
                                        //el cual se da al tener mas de una relacion @...ToMany con FetchType.EAGER
    private List<ContenidoRecibo> contenidosRecibo = new ArrayList<>();//Los alimentos que se estan vendiendo

    @ToString.Exclude
    @OneToMany(mappedBy = "recibo", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ContenidoPromocion> contenidoPromociones = new ArrayList<>();//Las promociones que se estan vendiendo

    @OneToOne(mappedBy = "recibo", fetch = FetchType.EAGER)
    private Comanda comanda;

    public Recibo(Date fecha, double total, double subtotal, String notas, Cliente cliente, int numeroMesa, double dineroRecibido, String direccionDeEnvio) {
        this.fecha = fecha;
        this.total = total;
        this.subtotal = subtotal;
        this.notas = notas;
        this.cliente = cliente;
        this.numeroMesa = numeroMesa;
        this.dineroRecibido = dineroRecibido;
        this.direccionDeEnvio = direccionDeEnvio;
    }

    public Recibo(){}
}
