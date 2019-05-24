package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.entity.sequence.CategoriaSequence;
import mx.ssmxli.food.entity.sequence.NombreSequence;
import mx.ssmxli.food.entity.sequence.TamanoSequence;
import mx.ssmxli.food.repository.sequence.CategoriaSequenceRepository;
import mx.ssmxli.food.repository.sequence.NombreSequenceRepository;
import mx.ssmxli.food.repository.sequence.TamanoSequenceRepository;
import mx.ssmxli.food.service.IdManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("idManagerServiceImpl")
public class IdManagerServiceImpl implements IdManagerService {
    @Autowired
    @Qualifier("categoriaSequenceRepository")
    private CategoriaSequenceRepository categoriaSequenceRepository;

    @Autowired
    @Qualifier("nombreSequenceRepository")
    private NombreSequenceRepository nombreSequenceRepository;

    @Autowired
    @Qualifier("tamanoSequenceRepository")
    private TamanoSequenceRepository tamanoSequenceRepository;

    @Override
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
    public int createID(String categoria, String nombre, String tamano) {
        int id = 0;
        CategoriaSequence categoriaSequence = categoriaSequenceRepository.findByNombreIgnoreCase(categoria); //Busca la categoria
        NombreSequence nombreSequence = nombreSequenceRepository.findByNombreIgnoreCase(nombre); //Busca el nombre
        TamanoSequence tamanoSequence = tamanoSequenceRepository.findByNombreIgnoreCase(tamano); //Busca el tamaño

        //Si no encontro la categoria entonces...
        if(categoriaSequence == null)
            //Registrar la nueva categoria
            categoriaSequence = categoriaSequenceRepository.save(new CategoriaSequence(categoria));

        //Si no encontro el nombre entonces...
        if(nombreSequence == null)
            //Registrar el nuevo nombre
            nombreSequence = nombreSequenceRepository.save(new NombreSequence(nombre)); //Registrar el nuevo nombre

        //Si no encontro el tamaño entonces...
        if(tamanoSequence == null)
            //Registrar el nuevo tamaño
            tamanoSequence = tamanoSequenceRepository.save(new TamanoSequence((tamano))); //Registrar el nuevo tamaño

        id += (categoriaSequence.getValor() * 100000);//"Guarda" la categoria en los ultimos digitos a partir del 6to
        id += (nombreSequence.getValor() * 100);//"Guarda" el nombre entre el 5to y el 3er digito
        id += tamanoSequence.getValor();//"Guarda" el tamaño en los primeros 2 digitos

        return id;
    }

    /**
     * Regresa una lista con todos los TamanoSequence
     *
     * @return List<TamanoSequence>
     * @author Andrés
     * */
    @Override
    public List<CategoriaSequence> listAllCategoria() {
        return categoriaSequenceRepository.findAll();
    }

    /**
     * Regresa una lista con todos los NombreSequence
     *
     * @return List<NombreSequence>
     * @author Andrés
     * */
    @Override
    public List<NombreSequence> listAllNombre() {
        return nombreSequenceRepository.findAll();
    }

    /**
     * Regresa una lista con todos los TamanoSequence
     *
     * @return List<TamanoSequence>
     * @author Andrés
     * */
    @Override
    public List<TamanoSequence> listAllTamano() {
        return tamanoSequenceRepository.findAll();
    }


    /**
     * Regresa una lista con todos los CategoriaSequence habilitados
     *
     * @return List<CategoriaSequence>
     * @author Andrés
     * */
    @Override
    public List<CategoriaSequence> listAllEnabledCategoria() {
        List<CategoriaSequence> categoriaList = new ArrayList<>();
        List<CategoriaSequence> temp = listAllCategoria();

        for (CategoriaSequence categoria: temp) {
            if(categoria.isEnabled())
                categoriaList.add(categoria);
        }

        return categoriaList;
    }


    /**
     * Regresa una lista con todos los NombreSequence habilitados
     *
     * @return List<NombreSequence>
     * @author Andrés
     * */
    @Override
    public List<NombreSequence> listAllEnabledNombre() {
        List<NombreSequence> nombreList = new ArrayList<>();
        List<NombreSequence> temp = listAllNombre();

        for(NombreSequence nombre : temp){
            if(nombre.isEnabled())
                nombreList.add(nombre);
        }

        return nombreList;
    }

    /**
     * Regresa una lista con todos los TamanoSequence habilitados
     *
     * @return List<TamanoSequence>
     * @author Andrés
     * */
    @Override
    public List<TamanoSequence> listAllEnabledTamano() {
        List<TamanoSequence> tamanoList = new ArrayList<>();
        List<TamanoSequence> temp = listAllTamano();

        for(TamanoSequence tamano : temp){
            if(tamano.isEnabled())
                tamanoList.add(tamano);
        }

        return tamanoList;
    }

}
