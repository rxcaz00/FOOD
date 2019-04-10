package mx.ssmxli.food.repository;


import mx.ssmxli.food.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("clienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, Serializable> {
    public abstract Cliente findByTelefono(String telefono);
}
