package mx.ssmxli.food.repository.sequence;

import mx.ssmxli.food.entity.sequence.CategoriaSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("categoriaSequenceRepository")
public interface CategoriaSequenceRepository extends JpaRepository<CategoriaSequence, Serializable> {
    public abstract CategoriaSequence findByNombreIgnoreCase(String nombre);
}
