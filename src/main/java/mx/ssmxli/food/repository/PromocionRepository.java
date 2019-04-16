package mx.ssmxli.food.repository;


import mx.ssmxli.food.entity.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("promocionRepository")
public interface PromocionRepository extends JpaRepository<Promocion, Serializable> {
    public abstract Promocion findById(int id);

}