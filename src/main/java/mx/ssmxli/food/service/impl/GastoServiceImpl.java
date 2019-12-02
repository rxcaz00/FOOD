package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.GastoConverter;
import mx.ssmxli.food.entity.Gasto;
import mx.ssmxli.food.model.GastoModel;
import mx.ssmxli.food.repository.GastoRepository;
import mx.ssmxli.food.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("gastoServiceImpl")
public class GastoServiceImpl implements GastoService {

    @Autowired
    @Qualifier("gastoRepository")
    private GastoRepository gastoRepository;

    @Autowired
    @Qualifier("gastoConverter")
    private GastoConverter gastoConverter;

    public GastoModel addGasto(GastoModel gastoModel) throws Exception {
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

    @Override
    public void removeGasto(int id) {

    }

    public double getTotal(char tipo) {
        List<Gasto> gastos = gastoRepository.findAll();
        double total = 0;
        for(Gasto gasto : gastos) {
            try {
                if (new SimpleDateFormat("dd-MM-yyyy").format(new Date())
                        .equals(new SimpleDateFormat("dd-MM-yyyy").format(gasto.getFecha()))
                && gasto.getTipo() == tipo)
                    total += gasto.getMonto();
            } catch (Exception e) {

            }
        }
        return total;
    }
}