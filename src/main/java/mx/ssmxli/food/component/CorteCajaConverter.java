package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.CorteCaja;
import mx.ssmxli.food.entity.Usuario;
import mx.ssmxli.food.model.CorteCajaModel;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("corteCajaConverter")
public class CorteCajaConverter {
    /**
     *
     * @param corteCajaModel
     *
     * Convierte un objeto de tipo "CorteCajaModel" a un objeto de tipo "CorteCaja"
     *
     * @return corteCaja
     * @throws Exception
     * @author Diana
     */
    public CorteCaja convertCorteCajaModel2CorteCaja(CorteCajaModel corteCajaModel)throws Exception {
        CorteCaja corteCaja = new CorteCaja();
        corteCaja.setId(corteCajaModel.getId());
        corteCaja.setFecha(new Date());
        corteCaja.setDineroInicial(corteCajaModel.getDineroInicial());
        corteCaja.setCorte(corteCajaModel.getCorte());
        corteCaja.setEfectivo(corteCajaModel.getEfectivo());
        corteCaja.setTarjeta(corteCajaModel.getTarjeta());
        corteCaja.setDiferencia(corteCajaModel.getDiferencia());
        corteCaja.setPago(corteCajaModel.getPago());
        corteCaja.setCompra(corteCajaModel.getCompra());
        //Borrar ya que se tengan las capas de usuario
        Usuario testUser = new Usuario();
        testUser.setNivel("gerente");
        testUser.setPassword("1111");
        testUser.setUsuario("prueba");
        testUser.setNombre("John");
        testUser.setApellidos("Doe");
        corteCaja.setElaboro(testUser);
        corteCaja.setReviso(testUser);
        /*Descomentar ya que se tengan las capas de usuario
        corteCaja.setElaboro(usuarioService.findByUsername(corteCajaModel.getElaboro()));
        corteCaja.setReviso(usuarioService.findByUsername(corteCajaModel.getReviso()));*/
        return corteCaja;
    }

    /**
     *
     * @param corteCaja
     *
     * Convierte un objeto de tipo "CorteCaja" a un objeto de tipo "CorteCajaModel"
     *
     * @return corteCajaModel
     * @author Diana
     */
    public CorteCajaModel convertCorteCaja2CorteCajaModel(CorteCaja corteCaja){
        CorteCajaModel corteCajaModel = new CorteCajaModel();
        corteCajaModel.setId(corteCaja.getId());
        corteCajaModel.setFecha(
                new SimpleDateFormat("yyyy-MM-dd")
                        .format(corteCaja.getFecha()));
        corteCajaModel.setDineroInicial(corteCaja.getDineroInicial());
        corteCajaModel.setCorte(corteCaja.getCorte());
        corteCajaModel.setEfectivo(corteCaja.getEfectivo());
        corteCajaModel.setTarjeta(corteCaja.getTarjeta());
        corteCajaModel.setDiferencia(corteCaja.getDiferencia());
        corteCajaModel.setPago(corteCaja.getPago());
        corteCajaModel.setCompra(corteCaja.getCompra());
        corteCajaModel.setElaboro(corteCaja.getElaboro().getUsuario());
        corteCajaModel.setReviso(corteCaja.getReviso().getUsuario());
        return corteCajaModel;
    }


}
