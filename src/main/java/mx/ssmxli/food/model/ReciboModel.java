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
    private Cliente cliente;
    private char metodoPago;
    private Usuario usuario;
    private char tipoOrden;
    private List<ContenidoReciboModel> contenidosRecibo;

    public ReciboModel(){

    }
}
