package mx.ssmxli.food.repository;

import mx.ssmxli.food.entity.Comanda;
import mx.ssmxli.food.entity.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ComandaRepository extends JpaRepository<Comanda, Serializable> {
    public abstract Comanda findByRecibo(Recibo recibo);
}
