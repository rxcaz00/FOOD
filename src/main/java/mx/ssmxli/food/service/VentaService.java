package mx.ssmxli.food.service;

import mx.ssmxli.food.entity.ContenidoPromocion;
import mx.ssmxli.food.entity.ContenidoRecibo;
import mx.ssmxli.food.entity.Recibo;
import mx.ssmxli.food.model.ContenidoPromocionModel;
import mx.ssmxli.food.model.ContenidoReciboModel;
import mx.ssmxli.food.model.ReciboModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ventaService")
public interface VentaService {
    public abstract ReciboModel addRecibo(ReciboModel reciboModel);

    public abstract List<ReciboModel> listAllRecibos();

    public abstract Recibo findReciboById(int id);

    public abstract ReciboModel findReciboByIdModel(int id);

    public abstract Recibo convertReciboModel2Recibo(ReciboModel reciboModel);

    public abstract ReciboModel convertRecibo2ReciboModel(Recibo recibo);

    public abstract ContenidoRecibo convertContenidoReciboModel2ContenidoRecibo(ContenidoReciboModel contenidoReciboModel);

    public abstract ContenidoReciboModel convertContenidoRecibo2ContenidoReciboModel(ContenidoRecibo contenidoRecibo);

    public abstract ReciboModel addRecibo(ReciboModel reciboModel, List<ContenidoReciboModel> contenidosRecibo, List<ContenidoPromocionModel> contenidosPromocion);

    public abstract double getTotal(char metodo);

    public abstract List<ContenidoRecibo> findContenidosReciboByRecibo(Recibo recibo);

    public abstract List<ContenidoPromocion> findContenidosPromocionByRecibo(Recibo recibo);
}