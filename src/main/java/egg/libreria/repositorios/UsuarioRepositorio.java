
package egg.libreria.repositorios;

import egg.libreria.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

}
