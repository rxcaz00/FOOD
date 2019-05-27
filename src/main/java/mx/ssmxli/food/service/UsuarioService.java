package mx.ssmxli.food.service;

import mx.ssmxli.food.entity.Usuario;
import mx.ssmxli.food.model.UsuarioModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("usuarioService")
public interface UsuarioService {
    public abstract UsuarioModel addUsuario(UsuarioModel usuarioModel) ;

    public abstract List<UsuarioModel> listAllUsuarios();

    public abstract Usuario findUsuarioByUsuario(String usuario);

    public abstract UsuarioModel findUsuarioByUsuarioModel(String usuario);

    public abstract void removeUsuario(String usuario);

    public abstract UsuarioModel convertUsuario2UsuarioModel(Usuario usuario);

    public abstract Usuario convertUsuarioModel2Usuario(UsuarioModel usuarioModel);
}
