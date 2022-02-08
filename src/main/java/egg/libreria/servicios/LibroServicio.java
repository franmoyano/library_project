
package egg.libreria.servicios;

import egg.libreria.entidades.Libro;
import egg.libreria.excepciones.ErrorServicio;
import egg.libreria.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroServicio {
    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorServicio autorServicio;

    @Transactional(readOnly = true)
    public List<Libro> findAll() {
        return libroRepositorio.findAll();
    }

    @Transactional
    public void save(Libro libro) throws ErrorServicio {

        if (libro.getTitulo().isEmpty() || libro.getTitulo() == null) {
            throw new ErrorServicio("El título no puede estar vacío");
        }
        if (libro.getIsbn() == null || libro.getIsbn() < 1) {
            throw new ErrorServicio("El número ISBN no puede estar vacío");
        }
        if (libro.getAnio() > 2022 || libro.getAnio() == null) {
            throw new ErrorServicio("El año debe ser menor o igual a 2022");
        }
        if (libro.getEjemplares() == null || libro.getEjemplares() <= 1) {
            throw new ErrorServicio("La cantidad de ejemplares debe ser mayor a 0");
        }
        if (libro.getEjemplaresPrestados() == null || libro.getEjemplaresPrestados() > libro.getEjemplares()) {
            throw new ErrorServicio("La cantidad de ejemplares prestados debe ser menor o igual al total de ejemplares");
        }
        if (libro.getAutor() == null) {
            throw new ErrorServicio("Debe crear un autor para el libro");
        }
        if (libro.getEditorial() == null) {
            throw new ErrorServicio("Debe crear una editorial para el libro");
        }
        libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
        libro.setAlta(true);
        libroRepositorio.save(libro);
    }
}
