package mx.ssmxli.food.service;

import mx.ssmxli.food.entity.Recibo;
import mx.ssmxli.food.model.ReciboModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ventaService")
public interface VentaService {
    public abstract ReciboModel addRecibo(ReciboModel reciboModel);
/*
    public abstract List<ReciboModel> listAllRecibos();

    public abstract Recibo findReciboById(int id);

    public abstract ReciboModel findReciboByIdModel(int id);

 */
}