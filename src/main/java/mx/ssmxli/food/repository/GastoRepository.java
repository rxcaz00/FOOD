package mx.ssmxli.food.repository;

import mx.ssmxli.food.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("gastoRepository")
public interface GastoRepository extends JpaRepository<Gasto, Serializable> {
    public abstract Gasto findById(int id);
}
