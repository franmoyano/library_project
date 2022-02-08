
package egg.libreria.servicios;

import egg.libreria.entidades.Libro;
import egg.libreria.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroServicio {
    @Autowired
    private LibroRepositorio libroRepositorio;

    @Transactional(readOnly = true)
    public List<Libro> findAll() {
        return libroRepositorio.findAll();
    }
}
