package mx.ssmxli.food.service;

import mx.ssmxli.food.entity.Gasto;
import mx.ssmxli.food.model.GastoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("gastoService")
public interface GastoService {
    public abstract GastoModel addGasto(GastoModel gastoModel) throws Exception;

    public abstract List<GastoModel> listAllGastos();

    public abstract Gasto findGastoById(int id);

    public abstract GastoModel findGastoByIdModel(int id);

    public abstract void removeGasto(int id);
}
