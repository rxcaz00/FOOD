package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Cliente;
import mx.ssmxli.food.model.ClienteModel;
import org.springframework.stereotype.Component;


    @Component("clienteConverter")
    public class ClienteConverter {

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
            cliente.setCorreo(clienteModel.getCorreo());
            cliente.setDireccion(clienteModel.getDireccion());
            cliente.setNombre(clienteModel.getNombre());
            cliente.setRFC(clienteModel.getRFC());
            cliente.setPuntos(clienteModel.getPuntos());
            cliente.setRecibos(clienteModel.getRecibos());
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
            clienteModel.setCorreo(cliente.getCorreo());
            clienteModel.setDireccion(cliente.getDireccion());
            clienteModel.setNombre(cliente.getNombre());
            clienteModel.setRFC(cliente.getRFC());
            clienteModel.setPuntos(cliente.getPuntos());
            clienteModel.setRecibos(cliente.getRecibos());
            clienteModel.setTelefono(cliente.getTelefono());
            return clienteModel;
        }
}
