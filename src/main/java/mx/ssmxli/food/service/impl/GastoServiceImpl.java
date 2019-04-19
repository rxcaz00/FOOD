package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.GastoConverter;
import mx.ssmxli.food.entity.Gasto;
import mx.ssmxli.food.model.GastoModel;
import mx.ssmxli.food.repository.GastoRepository;
import mx.ssmxli.food.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("gastoServiceImpl")
public class GastoServiceImpl implements GastoService {

    @Autowired
    @Qualifier("gastoRepository")
    private GastoRepository gastoRepository;

    @Autowired
    @Qualifier("gastoConverter")
    private GastoConverter gastoConverter;

    public GastoModel addGasto(GastoModel gastoModel){
        Gasto temp = gastoConverter.convertGastoModel2Gasto(gastoModel);
        Gasto gasto = gastoRepository.save(temp);
        return gastoConverter.convertGasto2GastoModel(gasto);
    }

    public List<GastoModel> listAllGastos() {
        List<Gasto> gastos = gastoRepository.findAll();
        List<GastoModel> gastosModel = new ArrayList<>();
        for(Gasto gasto : gastos)
            gastosModel.add(gastoConverter.convertGasto2GastoModel(gasto));
        return gastosModel;
    }

    public Gasto findGastoById(int id) {
        return gastoRepository.findById(id);
    }

    public GastoModel findGastoByIdModel(int id){
        return gastoConverter.convertGasto2GastoModel(findGastoById(id));
    }
}