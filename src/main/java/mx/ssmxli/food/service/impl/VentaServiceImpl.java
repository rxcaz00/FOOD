package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.VentaConverter;
import mx.ssmxli.food.entity.*;
import mx.ssmxli.food.model.ClienteModel;
import mx.ssmxli.food.model.ConfiguracionModel;
import mx.ssmxli.food.model.ContenidoReciboModel;
import mx.ssmxli.food.model.ReciboModel;
import mx.ssmxli.food.repository.ContenidoReciboRepository;
import mx.ssmxli.food.repository.ReciboRepository;
import mx.ssmxli.food.service.ClienteService;
import mx.ssmxli.food.service.ConfiguracionService;
import mx.ssmxli.food.service.SecurityService;
import mx.ssmxli.food.service.VentaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ventaServiceImpl")
public class VentaServiceImpl implements VentaService {

    @Autowired
    @Qualifier("reciboRepository")
    private ReciboRepository reciboRepository;

    @Autowired
    @Qualifier("contenidoReciboRepository")
    private ContenidoReciboRepository contenidoReciboRepository;

    @Autowired
    @Qualifier("ventaConverter")
    private VentaConverter ventaConverter;

    @Autowired
    @Qualifier("configuracionServiceImpl")
    private ConfiguracionService configuracionService;

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteService clienteService;

    private static final Log log = LogFactory.getLog(VentaService.class);

    public ReciboModel addRecibo(ReciboModel reciboModel) {
        Recibo temp = ventaConverter.convertReciboModel2Recibo(reciboModel);
        Recibo recibo = reciboRepository.save(temp);
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
        Recibo recibo = reciboRepository.save(temp);
        log.info("Method addRecibo() -- Saved Recibo: " + recibo.toString());
        Cliente cliente = recibo.getCliente();
        ConfiguracionModel configuracion = configuracionService.findLastConfiguracion();
        log.info("Method addRecibo() -- Configuracion: " + configuracion.toString());

        double total = 0.0;
        double subtotal = 0.0;

        if(recibo.getContenidosRecibo()==null)
            recibo.setContenidosRecibo(new ArrayList<>());

        for(ContenidoReciboModel contRecibo : contenidosRecibo){
            contRecibo.setId(0);//Para diferenciar los contenidoReciboModel en el controlador, se les asigno un ID temporal
                                //Es necesario regresar este valor a 0 para que JPA pueda asignarle su ID automatico
            total += contRecibo.getPrecio();
            contRecibo.setIdRecibo(recibo.getId());
            contenidoReciboRepository.save(ventaConverter.convertContenidoReciboModel2ContenidoRecibo(contRecibo));
        }

        if(cliente != null) {
            cliente.setPuntos(cliente.getPuntos() + (total * (configuracion.getRetribucion() / 100)));//Calcula los puntos en base a lo ingresado en configuracion
            ClienteModel clienteModel = clienteService.addCliente(clienteService.convertCliente2ClienteModel(cliente));//Añade los puntos al cliente
            log.info("Method addRecibo() -- Updated Cliente: " + clienteModel.toString());
        }

        //Calcula el subtotal
        subtotal = total * (1-(configuracion.getIva()/100));
        recibo.setTotal(total);
        recibo.setSubtotal(subtotal);

        recibo = reciboRepository.save(recibo);
        log.info("Method addRecibo() -- Updated Recibo: " + recibo.toString());

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