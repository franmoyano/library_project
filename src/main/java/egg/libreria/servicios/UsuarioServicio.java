package egg.libreria.servicios;

import egg.libreria.entidades.Usuario;
import egg.libreria.excepciones.ErrorServicio;
import egg.libreria.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio repo;

    @Transactional
    public void save(String username, String password1, String password2) throws ErrorServicio {
        if (username.isEmpty() || username == null) {
            throw new ErrorServicio("EL USUARIO NO PUEDE ESTAR VACÍO");
        }
        if (password1.isEmpty() || password1 == null) {
            throw new ErrorServicio("LA CONTRASEÑA NO PUEDE ESTAR VACÍA");
        }
        
        if (!password1.equals(password2)){
            throw new ErrorServicio("LAS CONTRASEÑAS NO COINCIDEN");
        }
        
        Usuario user = new Usuario();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password1));
        user.setUsername(username);
        
        repo.save(user);
    }
}
