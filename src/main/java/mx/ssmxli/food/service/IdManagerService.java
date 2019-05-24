package mx.ssmxli.food.service;

import mx.ssmxli.food.entity.sequence.CategoriaSequence;
import mx.ssmxli.food.entity.sequence.NombreSequence;
import mx.ssmxli.food.entity.sequence.TamanoSequence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("idManagerSevice")
public interface IdManagerService {

    public abstract int createID(String categoria, String nombre, String tamano);

    public abstract List<CategoriaSequence> listAllCategoria();

    public abstract List<NombreSequence> listAllNombre();

    public abstract List<TamanoSequence> listAllTamano();

    public abstract List<CategoriaSequence> listAllEnabledCategoria();

    public abstract List<NombreSequence> listAllEnabledNombre();

    public abstract List<TamanoSequence> listAllEnabledTamano();
}
