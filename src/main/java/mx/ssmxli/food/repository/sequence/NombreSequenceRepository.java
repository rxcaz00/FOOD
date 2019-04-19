package mx.ssmxli.food.repository.sequence;

import mx.ssmxli.food.entity.sequence.NombreSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("nombreSequenceRepository")
public interface NombreSequenceRepository extends JpaRepository<NombreSequence, Serializable> {
    public abstract NombreSequence findByNombreIgnoreCase(String nombre);
}
