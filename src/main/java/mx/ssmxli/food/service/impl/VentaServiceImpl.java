package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.VentaConverter;
import mx.ssmxli.food.entity.*;
import mx.ssmxli.food.model.*;
import mx.ssmxli.food.repository.ContenidoPromocionRepository;
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
    @Qualifier("contenidoPromocionRepository")
    private ContenidoPromocionRepository contenidoPromocionRepository;

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

    public ReciboModel addRecibo(ReciboModel reciboModel, List<ContenidoReciboModel> contenidosRecibo, List<ContenidoPromocionModel> contenidosPromocion){
        Recibo temp = ventaConverter.convertReciboModel2Recibo(reciboModel);
        ConfiguracionModel configuracion = configuracionService.findLastConfiguracion();
        log.info("Method addRecibo() -- Configuracion: " + configuracion.toString());

        //Añade diferentes cosas dependiendo de el tipo de orden
        switch(temp.getTipoOrden()){
            //A Domicilio
            case 'D':
                String direccionTemp = "";
                //Si DireccionCliente es true, entonces la direccion registrada del Cliente sera la de envio
                if(reciboModel.isDireccionCliente())
                    direccionTemp = temp.getCliente().getDireccion();
                //De otra manera, sera la Direccion de Envio ingresada
                else
                    direccionTemp = reciboModel.getDireccionDeEnvio();
                temp.setDireccionDeEnvio(direccionTemp);
                break;
            //Recoger
            case 'R':
                //Si el Cliente es quien recoge el pedido, entonces se pone a su nombre
                //Si no entonces se mantiene el que se escribio en la venta
                if(reciboModel.isRecogerCliente()) {
                    String nombreRecoger = "\nPedido a nombre de: " + temp.getCliente().getNombre() + " " + temp.getCliente().getApellidos();
                    temp.setNotas(temp.getNotas()+nombreRecoger);
                }
                break;
        }

        Recibo recibo = reciboRepository.save(temp);
        log.info("Method addRecibo() -- Saved Recibo: " + recibo.toString());
        Cliente cliente = recibo.getCliente();

        double total = 0.0;

        //Si los ContenidosRecibo estan vacios, se inicializa como lista vacia
        if(recibo.getContenidosRecibo()==null)
            recibo.setContenidosRecibo(new ArrayList<>());

        //Recorre cada uno de los ContenidoReciboModel de contenidosRecibo para asignarle el id de Recibo
        //NOTA: La asignacion de Alimento se hace desde VentaController
        for(ContenidoReciboModel contRecibo : contenidosRecibo){
            contRecibo.setId(0);//Para diferenciar los contenidoReciboModel en el controlador, se les asigno un ID temporal
                                //Es necesario regresar este valor a 0 para que JPA pueda asignarle su ID automatico
            total += contRecibo.getPrecio();//Suma el precio del contenidoRecibo al total
            contRecibo.setIdRecibo(recibo.getId());//Le asigna el id del Recibo al contenidoRecibo
            contenidoReciboRepository.save(ventaConverter.convertContenidoReciboModel2ContenidoRecibo(contRecibo));
        }

        //Si los ContenidoPromociones estan vacios, se inicializa como lista vacia
        if(recibo.getContenidoPromociones()==null)
            recibo.setContenidoPromociones(new ArrayList<>());

        //Recorre cada uno de los ContenidoPromocionModel para asignarle el id de Recibo
        for(ContenidoPromocionModel contPromocion : contenidosPromocion){
            //Se deben de pasar los ContenidoReciboModel de ContenidoPromocion a otro arreglo
            List<ContenidoReciboModel> contenidoReciboModels = contPromocion.getContenidosRecibo();
            //Despues de esto eliminamos la lista de ContenidoRecibo de contPromocion
            //ya que como los ContenidoRecibo no estan registrados, solamente haremos que salga un TransientValueException
            contPromocion.setContenidosRecibo(new ArrayList<>());

            contPromocion.setId(0);//Se retorna el ID a 0, ya que en el controlador se le asigna un ID temporal
            total += contPromocion.getPrecio();//Se suma el precio de la promocion al total
            contPromocion.setIdRecibo(recibo.getId());//Se asigna el ID del recibo
            ContenidoPromocion contenidoPromocion = contenidoPromocionRepository.save(convertContenidoPromocionModel2ContenidoPromocion(contPromocion));
            //Ya teniendo el ID de ContenidoPromocion, se guardan todos sus ContenidoRecibo
            for(ContenidoReciboModel contRecibo : contenidoReciboModels){
                contRecibo.setIdContenidoPromocion(contenidoPromocion.getId());
                contenidoReciboRepository.save(ventaConverter.convertContenidoReciboModel2ContenidoRecibo(contRecibo));
            }
        }

        //Si cliente no es null, entonces...
        if(cliente != null) {
            cliente.setPuntos(cliente.getPuntos() + (total * (configuracion.getRetribucion() / 100)));//Calcula los puntos en base a lo ingresado en configuracion
            ClienteModel clienteModel = clienteService.addCliente(clienteService.convertCliente2ClienteModel(cliente));//Añade los puntos al cliente
            log.info("Method addRecibo() -- Updated Cliente: " + clienteModel.toString());
        }

        //Calcula el subtotal
        double subtotal = total * (1-(configuracion.getIva()/100));
        recibo.setTotal(total);
        recibo.setSubtotal(subtotal);

        //Actualiza el recibo ya con todos sus datos
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

    public ContenidoPromocion convertContenidoPromocionModel2ContenidoPromocion(ContenidoPromocionModel contenidoPromocionModel){
        return ventaConverter.convertContenidoPromocionModel2ContenidoPromcion(contenidoPromocionModel);
    }

    public ContenidoPromocionModel convertContenidoPromocion2ContenidoPromocionModel(ContenidoPromocion contenidoPromocion){
        return ventaConverter.convertContenidoPromocion2ContenidoPromocionModel(contenidoPromocion);
    }
}