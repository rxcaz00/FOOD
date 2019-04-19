package mx.ssmxli.food.repository;

import mx.ssmxli.food.entity.CorteCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("corteCajaRepository")
public interface CorteCajaRepository extends JpaRepository<CorteCaja, Serializable> {
    public abstract CorteCaja findById(int id);
}

