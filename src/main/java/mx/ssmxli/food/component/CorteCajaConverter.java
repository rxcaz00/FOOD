package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.CorteCaja;
import mx.ssmxli.food.entity.Usuario;
import mx.ssmxli.food.model.CorteCajaModel;
import mx.ssmxli.food.service.SecurityService;
import mx.ssmxli.food.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("corteCajaConverter")
public class CorteCajaConverter {

    @Autowired
    @Qualifier("securityServiceImpl")
    SecurityService securityService;

    @Autowired
    @Qualifier("usuarioServiceImpl")
    UsuarioService usuarioService;

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
        corteCaja.setDiferencia(corteCajaModel.getEfectivo() - corteCajaModel.getCorte());
        corteCaja.setPago(corteCajaModel.getPago());
        corteCaja.setCompra(corteCajaModel.getCompra());

        String currentUser;
        currentUser = securityService.findLoggedInUsername();
        corteCaja.setElaboro(usuarioService.findUsuarioByUsuario(currentUser));

        currentUser = securityService.findLoggedGerenteInUsername();
        if(!currentUser.equals("no se encontro"))
            corteCaja.setReviso(usuarioService.findUsuarioByUsuario(currentUser));
        else
            corteCaja.setReviso(null);
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

        String nombre;
        nombre = corteCaja.getElaboro().getNombre() + " " + corteCaja.getElaboro().getApellidos();
        corteCajaModel.setElaboro(nombre);

        if(corteCaja.getReviso() == null)
            nombre = "Revisar";
        else
            nombre = corteCaja.getReviso().getNombre() + " " + corteCaja.getReviso().getApellidos();
        corteCajaModel.setReviso(nombre);
        return corteCajaModel;
    }


}
