package mx.ssmxli.food.service;


import mx.ssmxli.food.entity.Precio;
import mx.ssmxli.food.model.PrecioModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("precioService")
public interface PrecioService {
    public abstract PrecioModel addPrecio(PrecioModel precioModel);

    public abstract List<PrecioModel> listAllPrecios();

    public abstract Precio findPrecioById(int id);

    public abstract PrecioModel findPrecioByIdModel(int id);
}

