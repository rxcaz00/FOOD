package mx.ssmxli.food.repository;

import mx.ssmxli.food.entity.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("ventaRepository")
public interface VentaRepository extends JpaRepository<Recibo, Serializable> {
    public abstract Recibo findReciboById(int id);
}
