package mx.ssmxli.food.service;

import mx.ssmxli.food.entity.CorteCaja;
import mx.ssmxli.food.model.CorteCajaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("corteCajaService")
public interface CorteCajaService {
    public abstract CorteCajaModel addCorteCaja(CorteCajaModel corteCajaModel)throws Exception;

    public abstract List<CorteCajaModel> listAllCorteCajas();

    public abstract CorteCaja findCorteCajaById(int id);

    public abstract CorteCajaModel findCorteCajaByIdModel(int id);
}