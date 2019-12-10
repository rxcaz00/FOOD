package mx.ssmxli.food.repository;

import mx.ssmxli.food.entity.ContenidoPromocion;
import mx.ssmxli.food.entity.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("contenidoPromocionRepository")
public interface ContenidoPromocionRepository extends JpaRepository<ContenidoPromocion, Serializable> {
    public abstract ContenidoPromocion findById(int id);

    public abstract ContenidoPromocion findByRecibo(Recibo recibo);
}
