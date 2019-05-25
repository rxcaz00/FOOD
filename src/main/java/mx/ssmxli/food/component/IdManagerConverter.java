package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.sequence.CategoriaSequence;
import mx.ssmxli.food.entity.sequence.NombreSequence;
import mx.ssmxli.food.entity.sequence.TamanoSequence;
import mx.ssmxli.food.model.sequence.CategoriaSequenceModel;
import mx.ssmxli.food.model.sequence.NombreSequenceModel;
import mx.ssmxli.food.model.sequence.TamanoSequenceModel;
import mx.ssmxli.food.repository.sequence.CategoriaSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("idManagerConverter")
public class IdManagerConverter {
    @Autowired
    @Qualifier("categoriaSequenceRepository")
    private CategoriaSequenceRepository categoriaSequenceRepository;

    //Convertidores Categoria
        //Entidad a Modelo
    public CategoriaSequenceModel convertCategoria2CategoriaModel(CategoriaSequence categoriaSequence){
        CategoriaSequenceModel categoriaSequenceModel = new CategoriaSequenceModel();
        List<NombreSequenceModel> nombreTemp = new ArrayList<>();
        List<TamanoSequenceModel> tamanoTemp = new ArrayList<>();

        categoriaSequenceModel.setValor(categoriaSequence.getValor());
        categoriaSequenceModel.setNombre(categoriaSequence.getNombre());
        categoriaSequenceModel.setHabilitado(categoriaSequence.isHabilitado());

        for (NombreSequence nombre : categoriaSequence.getNombreSequences()){
            nombreTemp.add(convertNombre2NombreModel(nombre));
        }
        categoriaSequenceModel.setNombreSequenceModels(nombreTemp);

        for (TamanoSequence tamano : categoriaSequence.getTamanoSequences()){
            tamanoTemp.add(convertTamano2TamanoModel(tamano));
        }
        categoriaSequenceModel.setTamanoSequenceModels(tamanoTemp);

        return categoriaSequenceModel;
    }
        //Modelo a Entidad
    public CategoriaSequence convertCategoriaModel2Categoria(CategoriaSequenceModel categoriaSequenceModel){
        CategoriaSequence categoriaSequence = new CategoriaSequence();
        List<NombreSequence> nombreTemp = new ArrayList<>();
        List<TamanoSequence> tamanoTemp = new ArrayList<>();

        categoriaSequence.setValor(categoriaSequenceModel.getValor());
        categoriaSequence.setNombre(categoriaSequenceModel.getNombre());
        categoriaSequence.setHabilitado(categoriaSequenceModel.isHabilitado());

        for(NombreSequenceModel nombreModel : categoriaSequenceModel.getNombreSequenceModels()){
            nombreTemp.add(convertNombreModel2Nombre(nombreModel));
        }
        categoriaSequence.setNombreSequences(nombreTemp);

        for(TamanoSequenceModel tamanoModel : categoriaSequenceModel.getTamanoSequenceModels()){
            tamanoTemp.add(convertTamanoModel2Tamano(tamanoModel));
        }
        categoriaSequence.setTamanoSequences(tamanoTemp);

        return categoriaSequence;
    }


    //Convertidores NombreSequence
        //Entidad a Modelo
    public NombreSequenceModel convertNombre2NombreModel(NombreSequence nombreSequence){
        NombreSequenceModel nombreSequenceModel = new NombreSequenceModel();

        nombreSequenceModel.setValor(nombreSequence.getValor());
        nombreSequenceModel.setNombre(nombreSequence.getNombre());
        nombreSequenceModel.setHabilitado(nombreSequence.isHabilitado());
        /*nombreSequenceModel.setCategoria(convertCategoria2CategoriaModel(nombreSequence.getCategoria()));*/
        nombreSequenceModel.setValor_Categoria(nombreSequence.getCategoria().getValor());
        nombreSequenceModel.setNombre_Categoria(nombreSequence.getCategoria().getNombre());

        return nombreSequenceModel;
    }
        //Modelo a Entidad
    public NombreSequence convertNombreModel2Nombre(NombreSequenceModel nombreSequenceModel){
        NombreSequence nombreSequence = new NombreSequence();

        nombreSequence.setValor(nombreSequenceModel.getValor());
        nombreSequence.setNombre(nombreSequenceModel.getNombre());
        nombreSequence.setHabilitado(nombreSequenceModel.isHabilitado());
        /*nombreSequence.setCategoria(categoriaSequenceRepository.findByValor(nombreSequenceModel.getCategoria().getValor()));*/
        nombreSequence.setCategoria(categoriaSequenceRepository.findByValor(nombreSequenceModel.getValor_Categoria()));

        return nombreSequence;
    }

    //Convertidores TamanoSequence
        //Entidad a Modelo
    public TamanoSequenceModel convertTamano2TamanoModel(TamanoSequence tamanoSequence){
        TamanoSequenceModel tamanoSequenceModel = new TamanoSequenceModel();

        tamanoSequenceModel.setValor(tamanoSequence.getValor());
        tamanoSequenceModel.setNombre(tamanoSequence.getNombre());
        tamanoSequenceModel.setHabilitado(tamanoSequence.isHabilitado());
        /*tamanoSequenceModel.setCategoria(convertCategoria2CategoriaModel(tamanoSequence.getCategoria()));*/
        tamanoSequenceModel.setValor_Categoria(tamanoSequence.getCategoria().getValor());
        tamanoSequenceModel.setNombre_Categoria(tamanoSequence.getCategoria().getNombre());

        return tamanoSequenceModel;
    }
        //Modelo a Entidad
    public TamanoSequence convertTamanoModel2Tamano(TamanoSequenceModel tamanoSequenceModel){
        TamanoSequence tamanoSequence = new TamanoSequence();

        tamanoSequence.setValor(tamanoSequenceModel.getValor());
        tamanoSequence.setNombre(tamanoSequenceModel.getNombre());
        tamanoSequence.setHabilitado(tamanoSequenceModel.isHabilitado());
        /*tamanoSequence.setCategoria(categoriaSequenceRepository.findByValor(tamanoSequenceModel.getCategoria().getValor()));*/
        tamanoSequence.setCategoria(categoriaSequenceRepository.findByValor(tamanoSequenceModel.getValor_Categoria()));

        return tamanoSequence;
    }
}
