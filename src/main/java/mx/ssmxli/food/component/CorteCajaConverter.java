package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.CorteCaja;
import mx.ssmxli.food.entity.Usuario;
import mx.ssmxli.food.model.CorteCajaModel;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("corteCajaConverter")
public class CorteCajaConverter {
    //Conversion de Modelo a Entidad
    public CorteCaja convertCorteCajaModel2CorteCaja(CorteCajaModel corteCajaModel)throws Exception {

        CorteCaja corteCaja = new CorteCaja();

        corteCaja.setId(corteCajaModel.getId());
        corteCaja.setFecha(new Date());//Fecha del dia de hoy
        corteCaja.setCaja(corteCajaModel.getCaja());
        corteCaja.setCorte(corteCajaModel.getCorte());
        corteCaja.setVenta(corteCajaModel.getVenta());
        corteCaja.setTarjeta(corteCajaModel.getTarjeta());
        corteCaja.setDiferencia(corteCajaModel.getDiferencia());
        corteCaja.setPago(corteCajaModel.getPago());
        corteCaja.setCompra(corteCajaModel.getCompra());
        //Borrar ya que se tengan las capas de usuario
        Usuario testUser = new Usuario();
        testUser.setId(1);
        testUser.setNivel("gerente");
        testUser.setPassword("1111");
        testUser.setUsuario("prueba");
        corteCaja.setElaboro(testUser);
        corteCaja.setReviso(testUser);
        /*Descomentar ya que se tengan las capas de usuario
        corteCaja.setElaboro(usuarioService.findByUsername(corteCajaModel.getElaboro()));
        corteCaja.setReviso(usuarioService.findByUsername(corteCajaModel.getReviso()));*/
        return corteCaja;
    }
    //Conversion de Entidad a modelo
    public CorteCajaModel convertCorteCaja2CorteCajaModel(CorteCaja corteCaja){
        CorteCajaModel corteCajaModel = new CorteCajaModel();
        corteCajaModel.setId(corteCaja.getId());
        corteCajaModel.setFecha(corteCaja.getFecha().toString());
        corteCajaModel.setCaja(corteCaja.getCaja());
        corteCajaModel.setCorte(corteCaja.getCorte());
        corteCajaModel.setVenta(corteCaja.getVenta());
        corteCajaModel.setTarjeta(corteCaja.getTarjeta());
        corteCajaModel.setDiferencia(corteCaja.getDiferencia());
        corteCajaModel.setPago(corteCaja.getPago());
        corteCajaModel.setCompra(corteCaja.getCompra());
        corteCajaModel.setElaboro(corteCaja.getElaboro().getUsuario());
        corteCajaModel.setReviso(corteCaja.getReviso().getUsuario());
        return corteCajaModel;
    }


}
