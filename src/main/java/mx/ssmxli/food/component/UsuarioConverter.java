package mx.ssmxli.food.component;

import mx.ssmxli.food.entity.Usuario;
import mx.ssmxli.food.model.UsuarioModel;
import org.springframework.stereotype.Component;

@Component("usuarioConverter")
public class UsuarioConverter {
    /**
     *
     * @param usuarioModel
     *
     * Convierte un objeto de tipo "UsuarioModel" a un objeto de tipo "Usuario"
     *
     * @return usuario

     * @author Danya
     */
    public Usuario convertUsuarioModel2Usuario(UsuarioModel usuarioModel)  {

        Usuario usuario = new Usuario();
        usuario.setUsuario(usuarioModel.getUsuario());
        usuario.setNombre(usuarioModel.getNombre());
        usuario.setApellidos(usuarioModel.getApellidos());
        usuario.setNivel(usuarioModel.getNivel());
        usuario.setPassword(usuarioModel.getPassword());
        return usuario;
    }

    /**
     *
     * @param usuario
     *
     * Convierte un objeto de tipo "usuario" a un objeto de tipo "UsuarioModel"
     *
     * @return usuarioModel
     * @author Danya
     */
    public UsuarioModel convertUsuario2UsuarioModel(Usuario usuario){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUsuario(usuario.getUsuario());
        usuarioModel.setNombre(usuario.getNombre());
        usuarioModel.setApellidos(usuario.getApellidos());
        usuarioModel.setNivel(usuario.getNivel());
        usuarioModel.setPassword(usuario.getPassword());

        return usuarioModel;
    }
}
