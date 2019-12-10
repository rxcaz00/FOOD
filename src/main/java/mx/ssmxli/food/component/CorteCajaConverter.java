package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.CorteCaja;
import mx.ssmxli.food.entity.Usuario;
import mx.ssmxli.food.model.CorteCajaModel;
import mx.ssmxli.food.service.CorteCajaService;
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

    @Autowired
    @Qualifier("corteCajaServiceImpl")
    CorteCajaService corteCajaService;

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
        corteCaja.setDineroInicial(corteCajaModel.getDineroInicial());
        corteCaja.setCorte(corteCajaModel.getCorte());
        corteCaja.setEfectivo(corteCajaModel.getEfectivo());
        corteCaja.setTarjeta(corteCajaModel.getTarjeta());
        corteCaja.setDiferencia(corteCajaModel.getCorte() -
                (corteCajaModel.getEfectivo() -
                        (corteCajaModel.getPago() + corteCajaModel.getCompra())));
        corteCaja.setPago(corteCajaModel.getPago());
        corteCaja.setCompra(corteCajaModel.getCompra());

        String currentUser;

        if(corteCajaModel.getElaboro() == null) {
            currentUser = securityService.findLoggedInUsername();
            corteCaja.setElaboro(usuarioService.findUsuarioByUsuario(currentUser));

            corteCaja.setFecha(new Date());
        } else {
            corteCaja.setElaboro(
                    corteCajaService.findCorteCajaById(
                            corteCaja.getId()).getElaboro());

            corteCaja.setFecha(
                    new SimpleDateFormat("dd/MM/yyyy")
                            .parse(corteCajaModel.getFecha()));
        }

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
                new SimpleDateFormat("dd/MM/yyyy")
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
