package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.ConfiguracionConverter;
import mx.ssmxli.food.entity.Configuracion;
import mx.ssmxli.food.model.ConfiguracionModel;
import mx.ssmxli.food.repository.ConfiguracionRepository;
import mx.ssmxli.food.service.ConfiguracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("configuracionServiceImpl")
public class ConfiguracionServiceImpl implements ConfiguracionService {

    @Autowired
    @Qualifier("configuracionRepository")
    private ConfiguracionRepository configuracionRepository;

    @Autowired
    @Qualifier("configuracionConverter")
    private ConfiguracionConverter configuracionConverter;

    public ConfiguracionModel addConfiguracion(ConfiguracionModel configuracionModel) {
        Configuracion temp = configuracionConverter.convertConfiguracionModel2Configuracion(configuracionModel);
        Configuracion configuracion = configuracionRepository.save(temp);
        return configuracionConverter.convertConfiguracion2ConfiguracionModel(configuracion);
    }

    public List<ConfiguracionModel> listAllConfiguraciones() {
        List<Configuracion> configuraciones = configuracionRepository.findAll();
        List<ConfiguracionModel> configuracionesModel = new ArrayList();
        for(Configuracion configuracion : configuraciones)
            configuracionesModel.add(configuracionConverter.convertConfiguracion2ConfiguracionModel(configuracion));
        return configuracionesModel;
    }

    public Configuracion findConfiguracionById(int id) {
        return configuracionRepository.findById(id);
    }

    public ConfiguracionModel findConfiguracionByIdModel(int id) {
        return configuracionConverter.convertConfiguracion2ConfiguracionModel(findConfiguracionById(id));
    }

    public ConfiguracionModel findLastConfiguracion() {
        List<ConfiguracionModel> configuracionesModel = listAllConfiguraciones();
        return configuracionesModel.get(configuracionesModel.size() - 1);
    }
}
