package mx.ssmxli.food.repository;


import mx.ssmxli.food.entity.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("alimentoRepository")
public interface AlimentoRepository extends JpaRepository<Alimento, Serializable> {
    public abstract Alimento findById(int id);
}