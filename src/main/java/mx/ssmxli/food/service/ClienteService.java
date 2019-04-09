package mx.ssmxli.food.service;


import mx.ssmxli.food.entity.Cliente;
import mx.ssmxli.food.model.ClienteModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clienteService")
public interface ClienteService {
    public abstract ClienteModel addCliente(ClienteModel clienteModel);

    public abstract List<ClienteModel> listAllClientes();

    public abstract Cliente findClienteByTel(String telefono);

    public abstract void removeCliente(String telefono);

    public abstract ClienteModel findClienteByTelModel(String telefono);
}

