package mx.ssmxli.food.service;

import mx.ssmxli.food.entity.sequence.CategoriaSequence;
import mx.ssmxli.food.entity.sequence.NombreSequence;
import mx.ssmxli.food.entity.sequence.TamanoSequence;
import mx.ssmxli.food.model.sequence.CategoriaSequenceModel;
import mx.ssmxli.food.model.sequence.NombreSequenceModel;
import mx.ssmxli.food.model.sequence.TamanoSequenceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("idManagerSevice")
public interface IdManagerService {

    public abstract int createID(String categoria, String nombre, String tamano);

    public abstract int createID(int categoria, int nombre, int tamano);

    public abstract boolean categoriaExists();

    public abstract CategoriaSequenceModel addCategoria(CategoriaSequenceModel categoriaSequence);

    public abstract NombreSequenceModel addNombre(NombreSequenceModel nombreModel);

    public abstract TamanoSequenceModel addTamano(TamanoSequenceModel tamanoModel);

    public abstract CategoriaSequence findCategoriaByValor(int valor);

    public abstract NombreSequence findNombreByValor(int valor);

    public abstract TamanoSequence findTamanoByValor(int valor);

    public abstract CategoriaSequenceModel findCategoriaModelByValor(int valor);

    public abstract NombreSequenceModel findNombreModelByValor(int valor);

    public abstract TamanoSequenceModel findTamanoModelByValor(int valor);

    public abstract List<CategoriaSequenceModel> listAllCategorias();

    public abstract List<NombreSequenceModel> listAllNombres();

    public abstract List<TamanoSequenceModel> listAllTamanos();

    public abstract List<CategoriaSequenceModel> listAllEnabledCategorias();

    public abstract List<NombreSequenceModel> listAllEnabledNombres();

    /*Quite esa parte por problemas a la hora de mostrarlos por la categoria seleccionada
    public abstract List<NombreSequenceModel> listAllEnabledNombresByCategoria(int categoria);*/

    public abstract List<TamanoSequenceModel> listAllEnabledTamanos();

    /*Quite esa parte por problemas a la hora de mostrarlos por la categoria seleccionada
    public abstract List<TamanoSequenceModel> listAllEnabledTamanosByCategoria(int categoria);*/

    public abstract CategoriaSequenceModel convertCategoria2CategoriaModel(CategoriaSequence categoriaSequence);

    public abstract CategoriaSequence convertCategoriaModel2Categoria(CategoriaSequenceModel categoriaSequenceModel);

    public abstract NombreSequenceModel convertNombre2NombreModel(NombreSequence nombreSequence);

    public abstract NombreSequence convertNombreModel2Nombre(NombreSequenceModel nombreSequenceModel);

    public abstract TamanoSequenceModel convertTamano2TamanoModel(TamanoSequence tamanoSequence);

    public abstract TamanoSequence convertTamanoModel2Tamano(TamanoSequenceModel tamanoSequenceModel);
}

//34