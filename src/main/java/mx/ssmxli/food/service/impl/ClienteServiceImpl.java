package mx.ssmxli.food.service.impl;


import mx.ssmxli.food.component.ClienteConverter;
import mx.ssmxli.food.entity.Cliente;
import mx.ssmxli.food.model.ClienteModel;
import mx.ssmxli.food.repository.ClienteRepository;
import mx.ssmxli.food.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("clienteServiceImpl")
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    @Qualifier("clienteRepository")
    private ClienteRepository clienteRepository;

    @Autowired
    @Qualifier("clienteConverter")
    private ClienteConverter clienteConverter;

    @Override
    public ClienteModel addCliente(ClienteModel clienteModel) {
        //Aquí nos pide una entidad, por lo tanto tenemos que transformar el contactModel a entidad
        Cliente temp=clienteConverter.convertToClienteModel2Cliente(clienteModel);
        Cliente cliente = clienteRepository.save(temp);
        return clienteConverter.convertCliente2ClienteModel(cliente);
    }

    @Override
    public List<ClienteModel> listAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteModel> clientesModel = new ArrayList();
        for(Cliente cliente : clientes){
            clientesModel.add(clienteConverter.convertCliente2ClienteModel(cliente));

        }
        return clientesModel;
    }

    @Override
    public Cliente findClienteByTel(String telefono) {
        return clienteRepository.findByTel(telefono);
    }

    public ClienteModel findClienteByTelModel(String telefono){
        return clienteConverter.convertCliente2ClienteModel(findClienteByTel(telefono));
    }

    @Override
    public void removeCliente(String telfono) {
        Cliente cliente  = findClienteByTel(telfono);
        if(cliente != null){
            clienteRepository.delete(findClienteByTel(telfono));
        }
    }


}



