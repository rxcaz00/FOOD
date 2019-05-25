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

    public abstract boolean existsCategoria();

    public abstract List<CategoriaSequenceModel> listAllCategorias();

    public abstract List<NombreSequenceModel> listAllNombres();

    public abstract List<TamanoSequenceModel> listAllTamanos();

    public abstract List<CategoriaSequenceModel> listAllEnabledCategorias();

    public abstract List<NombreSequenceModel> listAllEnabledNombres();

    public abstract List<TamanoSequenceModel> listAllEnabledTamanos();

    public abstract CategoriaSequenceModel convertCategoria2CategoriaModel(CategoriaSequence categoriaSequence);

    public abstract CategoriaSequence convertCategoriaModel2Categoria(CategoriaSequenceModel categoriaSequenceModel);

    public abstract NombreSequenceModel convertNombre2NombreModel(NombreSequence nombreSequence);

    public abstract NombreSequence convertNombreModel2Nombre(NombreSequenceModel nombreSequenceModel);

    public abstract TamanoSequenceModel convertTamano2TamanoModel(TamanoSequence tamanoSequence);

    public abstract TamanoSequence convertTamanoModel2Tamano(TamanoSequenceModel tamanoSequenceModel);
}
