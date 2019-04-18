package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Configuracion;
import mx.ssmxli.food.model.ConfiguracionModel;
import org.springframework.stereotype.Component;

@Component("configuracionConverter")
public class ConfiguracionConverter {
    public Configuracion convertConfiguracionModel2Configuracion(ConfiguracionModel configuracionModel) {
        Configuracion configuracion = new Configuracion();
        configuracion.setId(configuracionModel.getId());
        configuracion.setIva(configuracionModel.getIva());
        configuracion.setRetribucion(configuracionModel.getRetribucion());
        return configuracion;
    }

    public ConfiguracionModel convertConfiguracion2ConfiguracionModel(Configuracion configuracion) {
        ConfiguracionModel configuracionModel = new ConfiguracionModel();
        configuracionModel.setId(configuracion.getId());
        configuracionModel.setIva(configuracion.getIva());
        configuracionModel.setRetribucion(configuracion.getRetribucion());
        return configuracionModel;
    }
}