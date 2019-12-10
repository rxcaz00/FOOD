package mx.ssmxli.food.model;

import lombok.Data;
import lombok.ToString;
import mx.ssmxli.food.entity.Cliente;
import mx.ssmxli.food.entity.ContenidoRecibo;
import mx.ssmxli.food.entity.Usuario;

import java.util.List;

@Data
public class ReciboModel {
    private int id;
    private String fecha;
    private String notas;
    private double subtotal;
    private double total;
    private String cliente;
    private char metodoPago;
    private String usuario;
    private String nombreUsuario;
    private char tipoOrden;
    @ToString.Exclude
    private List<ContenidoReciboModel> contenidosRecibo;
    @ToString.Exclude
    private List<ContenidoPromocionModel> contenidosPromocion;
    private int numeroMesa;
    private double dineroRecibido;
    private String direccionDeEnvio;
    private boolean direccionCliente;
    private boolean recogerCliente;
    private double puntos;

    public ReciboModel(){

    }
}
