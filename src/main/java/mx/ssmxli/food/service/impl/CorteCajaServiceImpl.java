package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.CorteCajaConverter;
import mx.ssmxli.food.entity.CorteCaja;
import mx.ssmxli.food.model.CorteCajaModel;
import mx.ssmxli.food.repository.CorteCajaRepository;
import mx.ssmxli.food.service.CorteCajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("corteCajaServiceImpl")
public class CorteCajaServiceImpl implements CorteCajaService {

    @Autowired
    @Qualifier("corteCajaRepository")
    private CorteCajaRepository corteCajaRepository;

    @Autowired
    @Qualifier("corteCajaConverter")
    private CorteCajaConverter corteCajaConverter;

    public CorteCajaModel addCorteCaja(CorteCajaModel corteCajaModel) throws Exception {
        //Aquí nos pide una entidad, por lo tanto tenemos que transformar el modelo a entidad
        CorteCaja temp = corteCajaConverter.convertCorteCajaModel2CorteCaja(corteCajaModel);
        CorteCaja corteCaja = corteCajaRepository.save(temp);
        return corteCajaConverter.convertCorteCaja2CorteCajaModel(corteCaja);
    }

    public List<CorteCajaModel> listAllCorteCajas() {
        List<CorteCaja> corteCajas = corteCajaRepository.findAll();
        List<CorteCajaModel> corteCajasModel = new ArrayList();
        for(CorteCaja corteCaja : corteCajas)
            corteCajasModel.add(corteCajaConverter.convertCorteCaja2CorteCajaModel(corteCaja));
        return corteCajasModel;
    }

    public CorteCaja findCorteCajaById(int id) {
        return corteCajaRepository.findById(id);
    }

    public CorteCajaModel findCorteCajaByIdModel(int id) {
        return corteCajaConverter.convertCorteCaja2CorteCajaModel(findCorteCajaById(id));
    }
}