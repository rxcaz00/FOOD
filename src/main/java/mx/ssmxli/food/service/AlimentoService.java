package mx.ssmxli.food.service;

import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.model.AlimentoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("alimentoService")
public interface AlimentoService {
    public abstract AlimentoModel addAlimento(AlimentoModel alimentoModel);

    public abstract List<AlimentoModel> listAllAlimentos();

    public abstract Alimento findAlimentoById(int id);

    public abstract void removeAlimento(int id);

    public abstract AlimentoModel findAlimentoByIdModel(int id);

    public abstract List<AlimentoModel> listAllAlimentosHabilitados();
}

