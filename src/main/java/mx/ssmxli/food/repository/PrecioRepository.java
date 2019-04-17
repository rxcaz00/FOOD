package mx.ssmxli.food.repository;

import mx.ssmxli.food.entity.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("precioRepository")
public interface PrecioRepository extends JpaRepository<Precio, Serializable> {
    public abstract Precio findById(int id);
}
