package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.PrecioConverter;
import mx.ssmxli.food.entity.Precio;
import mx.ssmxli.food.model.PrecioModel;
import mx.ssmxli.food.repository.PrecioRepository;
import mx.ssmxli.food.service.PrecioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("precioServiceImpl")
public class PrecioServiceImpl implements PrecioService {

    @Autowired
    @Qualifier("precioRepository")
    private PrecioRepository precioRepository;

    @Autowired
    @Qualifier("precioConverter")
    private PrecioConverter precioConverter;

    @Override
    public PrecioModel addPrecio(PrecioModel precioModel) {
        //Aquí nos pide una entidad, por lo tanto tenemos que transformar el contactModel a entidad
        Precio temp=precioConverter.convertToPrecioModel2Precio(precioModel);
        Precio precio = precioRepository.save(temp);
        return precioConverter.convertPrecio2PrecioModel(precio);
    }

    @Override
    public List<PrecioModel> listAllPrecios() {
        List<Precio> precios = precioRepository.findAll();
        List<PrecioModel> preciosModel = new ArrayList();
        for(Precio precio : precios){
            preciosModel.add(precioConverter.convertPrecio2PrecioModel(precio));

        }
        return preciosModel;
    }

    @Override
    public Precio findPrecioById(int id) {
        return precioRepository.findById(id);
    }

    public PrecioModel findPrecioByIdModel(int id){
        return precioConverter.convertPrecio2PrecioModel(findPrecioById(id));
    }

    @Override
    public void removePrecio(int id) {
        Precio precio  = findPrecioById(id);
        if(precio != null){
            precioRepository.delete(findPrecioById(id));
        }
    }

}

