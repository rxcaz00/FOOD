package mx.ssmxli.food.repository;

import mx.ssmxli.food.entity.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("reciboRepository")
public interface ReciboRepository extends JpaRepository<Recibo, Serializable> {
    public abstract Recibo findReciboById(int id);
}
