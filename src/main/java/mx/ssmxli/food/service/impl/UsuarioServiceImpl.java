package mx.ssmxli.food.service.impl;

import mx.ssmxli.food.component.UsuarioConverter;
import mx.ssmxli.food.entity.Usuario;
import mx.ssmxli.food.model.UsuarioModel;
import mx.ssmxli.food.repository.UsuarioRepository;
import mx.ssmxli.food.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    @Qualifier("usuarioRepository")
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Qualifier("usuarioConverter")
    private UsuarioConverter usuarioConverter;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UsuarioModel addUsuario(UsuarioModel usuarioModel)  {
        usuarioModel.setPassword(bCryptPasswordEncoder.encode(usuarioModel.getPassword()));
        Usuario temp = usuarioConverter.convertUsuarioModel2Usuario(usuarioModel);
        Usuario usuario = usuarioRepository.save(temp);
        return usuarioConverter.convertUsuario2UsuarioModel(usuario);
    }

    public List<UsuarioModel> listAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioModel > usuariosModel = new ArrayList<>();
        for(Usuario usuario : usuarios)
            usuariosModel.add(usuarioConverter.convertUsuario2UsuarioModel(usuario));
        return usuariosModel;
    }

    public Usuario findUsuarioByUsuario(String usuario) {

        return usuarioRepository.findByUsuario(usuario);
    }

    public UsuarioModel findUsuarioByUsuarioModel(String usuario){
        return usuarioConverter.convertUsuario2UsuarioModel(findUsuarioByUsuario(usuario));
    }

    @Override
    public void removeUsuario(String  usuario) {

    }
}
