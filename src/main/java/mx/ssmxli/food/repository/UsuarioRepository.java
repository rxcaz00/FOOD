package mx.ssmxli.food.repository;

import mx.ssmxli.food.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable> {
    public abstract Usuario findUsuarioById(int id);

    public abstract Usuario findUsuarioByUsuario(String usuario);
}
