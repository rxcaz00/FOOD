package mx.ssmxli.food.repository.sequence;

import mx.ssmxli.food.entity.sequence.TamanoSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("tamanoSequenceRepository")
public interface TamanoSequenceRepository extends JpaRepository<TamanoSequence, Serializable> {
    public abstract TamanoSequence findByNombreIgnoreCase(String nombre);

    public abstract TamanoSequence findByValor(int valor);
}
