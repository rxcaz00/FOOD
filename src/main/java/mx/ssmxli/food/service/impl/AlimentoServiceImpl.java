package mx.ssmxli.food.service.impl;


import mx.ssmxli.food.component.AlimentoConverter;
import mx.ssmxli.food.entity.Alimento;
import mx.ssmxli.food.model.AlimentoModel;
import mx.ssmxli.food.repository.AlimentoRepository;
import mx.ssmxli.food.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("alimentoServiceImpl")
public class AlimentoServiceImpl implements AlimentoService {

    @Autowired
    @Qualifier("alimentoRepository")
    private AlimentoRepository alimentoRepository;

    @Autowired
    @Qualifier("alimentoConverter")
    private AlimentoConverter alimentoConverter;

    @Override
    public AlimentoModel addAlimento(AlimentoModel alimentoModel) {
        //Aquí nos pide una entidad, por lo tanto tenemos que transformar el contactModel a entidad
        Alimento temp=alimentoConverter.convertToAlimentoModel2Alimento(alimentoModel);
        Alimento alimento = alimentoRepository.save(temp);
        return alimentoConverter.convertAlimento2AlimentoModel(alimento);
    }

    @Override
    public List<AlimentoModel> listAllAlimentos() {
        List<Alimento> alimentos = alimentoRepository.findAll();
        List<AlimentoModel> alimentosModel = new ArrayList();
        for(Alimento alimento : alimentos){
            alimentosModel.add(alimentoConverter.convertAlimento2AlimentoModel(alimento));

        }
        return alimentosModel;
    }

    @Override
    public Alimento findAlimentoById(int id) {
        return alimentoRepository.findById(id);
    }

    public AlimentoModel findAlimentoByIdModel(int id){
        return alimentoConverter.convertAlimento2AlimentoModel(findAlimentoById(id));
    }

    @Override
    public void removeAlimento(int id) {
        Alimento alimento  = findAlimentoById(id);
        if(alimento != null){
            alimentoRepository.delete(findAlimentoById(id));
        }
    }


}

