package mx.ssmxli.food.service;

import mx.ssmxli.food.entity.sequence.CategoriaSequence;
import mx.ssmxli.food.entity.sequence.NombreSequence;
import mx.ssmxli.food.entity.sequence.TamanoSequence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("idManagerSevice")
public interface IdManagerService {
    /**
     * @param categoria
     * @param nombre
     * @param tamano
     *
     * Recibe tres String correspondientes a la categoria, el nombre y el tamaño del Alimento.
     * En base en el ID de cada uno en su respectiva BD, creara el ID del Alimento.
     *
     * Regresa un id entero con el formato CCNNNTT. C=Categoria, N=Nombre y T=Tamaño.
     * @return int id
     * */
    public abstract int createID(String categoria, String nombre, String tamano);

    public abstract List<CategoriaSequence> listAllCategoria();

    public abstract List<NombreSequence> listAllNombre();

    public abstract List<TamanoSequence> listAllTamano();
}
