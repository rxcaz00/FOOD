package mx.ssmxli.food.repository;

import mx.ssmxli.food.entity.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("configuracionRepository")
public interface ConfiguracionRepository extends JpaRepository<Configuracion, Serializable> {
    public abstract Configuracion findById(int id);
}
