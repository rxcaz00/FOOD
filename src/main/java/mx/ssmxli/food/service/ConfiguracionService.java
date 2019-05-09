package mx.ssmxli.food.service;

import mx.ssmxli.food.entity.Configuracion;
import mx.ssmxli.food.model.ConfiguracionModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("configuracionService")
public interface ConfiguracionService {
    public abstract ConfiguracionModel addConfiguracion(ConfiguracionModel configuracionModel);

    public abstract List<ConfiguracionModel> listAllConfiguraciones();

    public abstract Configuracion findConfiguracionById(int id);

    public abstract ConfiguracionModel findConfiguracionByIdModel(int id);

    public abstract ConfiguracionModel findLastConfiguracion();
}
