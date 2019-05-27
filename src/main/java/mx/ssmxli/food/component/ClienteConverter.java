package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Cliente;
import mx.ssmxli.food.entity.Recibo;
import mx.ssmxli.food.model.ClienteModel;
import mx.ssmxli.food.model.ReciboModel;
import mx.ssmxli.food.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component("clienteConverter")
public class ClienteConverter {

    @Autowired
    @Qualifier("ventaServiceImpl")
    private VentaService ventaService;

    /**
     *
     * @param clienteModel
     *
     * Convierte un objeto de tipo "ClienteModel" a un objeto de tipo "Cliente"
     *
     * @return cliente
     * @author Roberto
     */
    public Cliente convertClienteModel2Cliente(ClienteModel clienteModel){
        Cliente cliente = new Cliente();
        List<Recibo> recibos = new ArrayList<>();

        cliente.setCorreo(clienteModel.getCorreo());
        cliente.setDireccion(clienteModel.getDireccion());
        cliente.setNombre(clienteModel.getNombre());
        cliente.setApellidos(clienteModel.getApellidos());
        /*
        cliente.setApellidoPaterno(clienteModel.getApellidoPaterno());
        cliente.setApellidoMaterno(clienteModel.getApellidoMaterno());
         */
        cliente.setRFC(clienteModel.getRFC());
        cliente.setPuntos(clienteModel.getPuntos());

        for(ReciboModel reciboModel : clienteModel.getRecibos()){
            recibos.add(ventaService.convertReciboModel2Recibo(reciboModel));
        }
        cliente.setRecibos(recibos);

        cliente.setTelefono(clienteModel.getTelefono());
        return cliente;
    }

    /**
     *
     * @param cliente
     *
     * Convierte un objeto de tipo "Cliente" a un objeto de tipo "ClienteModel"
     *
     * @return clienteModel
     * @author Roberto
     */
    public ClienteModel convertCliente2ClienteModel(Cliente cliente ){
        ClienteModel clienteModel = new ClienteModel();
        List<ReciboModel> reciboModels = new ArrayList<>();

        clienteModel.setCorreo(cliente.getCorreo());
        clienteModel.setDireccion(cliente.getDireccion());
        clienteModel.setNombre(cliente.getNombre());
        clienteModel.setApellidos(cliente.getApellidos());
        /*
        clienteModel.setApellidoPaterno(cliente.getApellidoPaterno());
        clienteModel.setApellidoMaterno(cliente.getApellidoMaterno());
         */
        clienteModel.setRFC(cliente.getRFC());
        clienteModel.setPuntos(cliente.getPuntos());
        for(Recibo recibo : cliente.getRecibos()){
            reciboModels.add(ventaService.convertRecibo2ReciboModel(recibo));
        }
        clienteModel.setRecibos(reciboModels);
        clienteModel.setTelefono(cliente.getTelefono());
        return clienteModel;
    }

}
