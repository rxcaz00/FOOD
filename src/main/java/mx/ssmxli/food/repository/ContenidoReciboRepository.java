package mx.ssmxli.food.repository;

import mx.ssmxli.food.entity.ContenidoRecibo;
import mx.ssmxli.food.entity.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("contenidoReciboRepository")
public interface ContenidoReciboRepository extends JpaRepository<ContenidoRecibo, Serializable> {
    public abstract ContenidoRecibo findById(int id);

    public abstract ContenidoRecibo findByRecibo(Recibo recibo);
}
