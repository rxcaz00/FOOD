package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.IdManagerConverter;
import mx.ssmxli.food.entity.sequence.CategoriaSequence;
import mx.ssmxli.food.entity.sequence.NombreSequence;
import mx.ssmxli.food.entity.sequence.TamanoSequence;
import mx.ssmxli.food.model.sequence.CategoriaSequenceModel;
import mx.ssmxli.food.model.sequence.NombreSequenceModel;
import mx.ssmxli.food.model.sequence.TamanoSequenceModel;
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

    @Autowired
    @Qualifier("idManagerConverter")
    private IdManagerConverter idManagerConverter;

    /**
     *
     * Recibe tres String correspondientes a la categoria, el nombre y el tamaño del Alimento.
     * En base en el ID de cada uno en su respectiva BD, creara el ID del Alimento.
     *
     * Regresa un id entero con el formato CCNNNTT. C=Categoria, N=Nombre y T=Tamaño.
     * @return int id
     * @param categoria
     * @param nombre
     * @param tamano
     * */
    @Override
    public int createID(String categoria, String nombre, String tamano) {
        int id = 0;
        CategoriaSequence categoriaSequence = categoriaSequenceRepository.findByNombreIgnoreCase(categoria); //Busca la categoria
        NombreSequence nombreSequence = nombreSequenceRepository.findByNombreIgnoreCase(nombre); //Busca el nombre
        TamanoSequence tamanoSequence = tamanoSequenceRepository.findByNombreIgnoreCase(tamano); //Busca el tamaño

        /*Vestigios de cuando funcionaba con Input Text en vez de SELECT. Ahora categoria, nombre y tamaño tienen sus propios mantenimientos
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
        */

        id += (categoriaSequence.getValor() * 100000);//"Guarda" la categoria en los ultimos digitos a partir del 6to
        id += (nombreSequence.getValor() * 100);//"Guarda" el nombre entre el 5to y el 3er digito
        id += tamanoSequence.getValor();//"Guarda" el tamaño en los primeros 2 digitos

        return id;
    }

    /**
     * Verifica si existen o no categorias registradas.
     * True = Si hay categorias
     * False = No hay categorias
     *
     * @return boolean
     * @author Andrés
     * */
    @Override
    public boolean existsCategoria() {
        boolean exists = true;

        if(listAllCategorias().isEmpty())
            exists = false;

        return exists;
    }


    //CONSULTA GENERAL
    /**
     * Regresa una lista con todos los CategoriaSequence, convertidos a CategoriaSequenceModel
     *
     * @return List<CategoriaSequenceModel>
     * @author Andrés
     * */
    @Override
    public List<CategoriaSequenceModel> listAllCategorias() {
        List<CategoriaSequence> categoriaTemp = categoriaSequenceRepository.findAll();
        List<CategoriaSequenceModel> categoriaSequences = new ArrayList<>();

        for (CategoriaSequence categoria: categoriaTemp) {
            categoriaSequences.add(idManagerConverter.convertCategoria2CategoriaModel(categoria));
        }

        return categoriaSequences;
    }

    /**
     * Regresa una lista con todos los NombreSequence, convertidos a NombreSequenceModel
     *
     * @return List<NombreSequence>
     * @author Andrés
     * */
    @Override
    public List<NombreSequenceModel> listAllNombres() {
        List<NombreSequence> nombreTemp = nombreSequenceRepository.findAll();
        List<NombreSequenceModel> nombreSequences = new ArrayList<>();

        for (NombreSequence nombre : nombreTemp){
            nombreSequences.add(idManagerConverter.convertNombre2NombreModel(nombre));
        }

        return nombreSequences;
    }

    /**
     * Regresa una lista con todos los TamanoSequence, convertidos a TamanoSequenceModel
     *
     * @return List<TamanoSequenceModel>
     * @author Andrés
     * */
    @Override
    public List<TamanoSequenceModel> listAllTamanos() {
        List<TamanoSequence> tamanoTemp = tamanoSequenceRepository.findAll();
        List<TamanoSequenceModel> tamanoSequences = new ArrayList<>();

        for(TamanoSequence tamano: tamanoTemp){
            tamanoSequences.add(idManagerConverter.convertTamano2TamanoModel(tamano));
        }

        return tamanoSequences;
    }


    //CONSULTA HABILITADOS
    /**
     * Regresa una lista con todos los CategoriaSequence habilitados
     *
     * @return List<CategoriaSequence>
     * @author Andrés
     * */
    @Override
    public List<CategoriaSequenceModel> listAllEnabledCategorias() {
        List<CategoriaSequenceModel> categoriaList = new ArrayList<>();
        List<CategoriaSequenceModel> temp = listAllCategorias();

        for (CategoriaSequenceModel categoria: temp) {
            if(categoria.isHabilitado())
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
    public List<NombreSequenceModel> listAllEnabledNombres() {
        List<NombreSequenceModel> nombreList = new ArrayList<>();
        List<NombreSequenceModel> temp = listAllNombres();

        for(NombreSequenceModel nombre : temp){
            if(nombre.isHabilitado())
                nombreList.add(nombre);
        }

        return nombreList;
    }

    /**
     * Regresa una lista con todos los TamanoSequence habilitados
     *
     * @return List<TamanoSequenceModel>
     * @author Andrés
     * */
    @Override
    public List<TamanoSequenceModel> listAllEnabledTamanos() {
        List<TamanoSequenceModel> tamanoList = new ArrayList<>();
        List<TamanoSequenceModel> temp = listAllTamanos();

        for(TamanoSequenceModel tamano : temp){
            if(tamano.isHabilitado())
                tamanoList.add(tamano);
        }

        return tamanoList;
    }


    //CONVERTIDORES
    /**
     * Convertidor de CategoriaSequence a CategoriaSequenceModel
     *
     * @param categoriaSequence
     * @return categoriaSequenceModel
     * @author Andrés
     * */
    @Override
    public CategoriaSequenceModel convertCategoria2CategoriaModel(CategoriaSequence categoriaSequence) {
        return idManagerConverter.convertCategoria2CategoriaModel(categoriaSequence);
    }

    /**
     * Convertidor de CategoriaSequenceModel a CategoriaSequence
     *
     * @param categoriaSequenceModel
     * @return categoriaSequence
     * @author Andrés
     * */
    @Override
    public CategoriaSequence convertCategoriaModel2Categoria(CategoriaSequenceModel categoriaSequenceModel) {
        return idManagerConverter.convertCategoriaModel2Categoria(categoriaSequenceModel);
    }

    /**
     * Convertidor de NombreSequence a NombreSequenceModel
     *
     * @param nombreSequence
     * @return nombreSequenceModel
     * @author Andrés
     * */
    @Override
    public NombreSequenceModel convertNombre2NombreModel(NombreSequence nombreSequence) {
        return idManagerConverter.convertNombre2NombreModel(nombreSequence);
    }

    /**
     * Convertidor de NombreSequenceModel a NombreSequence
     *
     * @param nombreSequenceModel
     * @return nombreSequence
     * @author Andrés
     * */
    @Override
    public NombreSequence convertNombreModel2Nombre(NombreSequenceModel nombreSequenceModel) {
        return idManagerConverter.convertNombreModel2Nombre(nombreSequenceModel);
    }

    /**
     * Convertidor de TamanoSequence a TamanoSequenceModel
     *
     * @param tamanoSequence
     * @return tamanoSequenceModel
     * @author Andrés
     * */
    @Override
    public TamanoSequenceModel convertTamano2TamanoModel(TamanoSequence tamanoSequence) {
        return idManagerConverter.convertTamano2TamanoModel(tamanoSequence);
    }

    /**
     * Convertidor de TamanoSequenceModel a TamanoSequence
     *
     * @param tamanoSequenceModel
     * @return tamanoSequence
     * @author Andrés
     * */
    @Override
    public TamanoSequence convertTamanoModel2Tamano(TamanoSequenceModel tamanoSequenceModel) {
        return idManagerConverter.convertTamanoModel2Tamano(tamanoSequenceModel);
    }

}
