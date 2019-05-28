package mx.ssmxli.food.model;

import lombok.Data;
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
    private List<ContenidoReciboModel> contenidosRecibo;
    private int numeroMesa;
    private double dineroRecibido;
    private String direccionDeEnvio;
    private boolean direccionCliente;
    private boolean recogerCliente;

    public ReciboModel(){

    }
}
