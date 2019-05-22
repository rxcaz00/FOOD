package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.VentaConverter;
import mx.ssmxli.food.entity.ContenidoRecibo;
import mx.ssmxli.food.entity.Recibo;
import mx.ssmxli.food.model.ContenidoReciboModel;
import mx.ssmxli.food.model.ReciboModel;
import mx.ssmxli.food.repository.ContenidoReciboRepository;
import mx.ssmxli.food.repository.VentaRepository;
import mx.ssmxli.food.service.ConfiguracionService;
import mx.ssmxli.food.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("ventaServiceImpl")
public class VentaServiceImpl implements VentaService {

    @Autowired
    @Qualifier("ventaRepository")
    private VentaRepository ventaRepository;

    @Autowired
    @Qualifier("contenidoReciboRepository")
    private ContenidoReciboRepository contenidoReciboRepository;

    @Autowired
    @Qualifier("ventaConverter")
    private VentaConverter ventaConverter;

    @Autowired
    @Qualifier("configuracionServiceImpl")
    private ConfiguracionService configuracionService;

    public ReciboModel addRecibo(ReciboModel reciboModel) {
        Recibo temp = ventaConverter.convertReciboModel2Recibo(reciboModel);
        Recibo recibo = ventaRepository.save(temp);
        return ventaConverter.convertRecibo2ReciboModel(recibo);

    }

    @Override
    public List<ReciboModel> listAllRecibos() {
        return null;
    }

    @Override
    public Recibo findReciboById(int id) {
        return null;
    }

    @Override
    public ReciboModel findReciboByIdModel(int id) {
        return null;
    }

    public ReciboModel addRecibo(ReciboModel reciboModel, List<ContenidoReciboModel> contenidosRecibo){
        Recibo temp = ventaConverter.convertReciboModel2Recibo(reciboModel);
        Recibo recibo = ventaRepository.save(temp);
        double total = 0.0;
        double subtotal = 0.0;
        if(recibo.getContenidosRecibo()==null)
            recibo.setContenidosRecibo(new ArrayList<>());

        for(ContenidoReciboModel contRecibo : contenidosRecibo){
            total += contRecibo.getPrecio();
            contRecibo.setIdRecibo(recibo.getId());
            contenidoReciboRepository.save(ventaConverter.convertContenidoReciboModel2ContenidoRecibo(contRecibo));
        }
        subtotal = total * (1-(configuracionService.findLastConfiguracion().getIva()/100));
        recibo.setTotal(total);
        recibo.setSubtotal(subtotal);
        recibo = ventaRepository.save(recibo);

        return ventaConverter.convertRecibo2ReciboModel(recibo);
    }

    //CONVERTIDORES
    public Recibo convertReciboModel2Recibo(ReciboModel reciboModel){
        return ventaConverter.convertReciboModel2Recibo(reciboModel);
    }

    public ReciboModel convertRecibo2ReciboModel(Recibo recibo){
        return ventaConverter.convertRecibo2ReciboModel(recibo);
    }

    public ContenidoRecibo convertContenidoReciboModel2ContenidoRecibo(ContenidoReciboModel contenidoReciboModel){
        return ventaConverter.convertContenidoReciboModel2ContenidoRecibo(contenidoReciboModel);
    }

    public ContenidoReciboModel convertContenidoRecibo2ContenidoReciboModel(ContenidoRecibo contenidoRecibo){
        return ventaConverter.convertContenidoRecibo2ContenidoReciboModel(contenidoRecibo);
    }
}