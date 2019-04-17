package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Cliente;
import mx.ssmxli.food.model.ClienteModel;
import org.springframework.stereotype.Component;


    @Component("clienteConverter")
    public class ClienteConverter {
        public Cliente convertClienteModel2Cliente(ClienteModel clienteModel){
            Cliente cliente = new Cliente();

            cliente.setNombre(clienteModel.getNombre());
            cliente.setDireccion(clienteModel.getDireccion());
            cliente.setCorreo(clienteModel.getCorreo());
            cliente.setRFC(clienteModel.getRFC());
            return cliente;
        }

        public ClienteModel convertCliente2ClienteModel(Cliente cliente ){
            ClienteModel clienteModel = new ClienteModel();
            clienteModel.setNombre(cliente.getNombre());
            clienteModel.setDireccion(cliente.getDireccion());
            clienteModel.setCorreo(cliente.getCorreo());
            clienteModel.setRFC(cliente.getRFC());
            return clienteModel;
        }
}
