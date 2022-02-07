package egg.libreria.servicios;

import egg.libreria.entidades.Autor;
import egg.libreria.excepciones.ErrorServicio;
import egg.libreria.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional(readOnly = true)
    public Autor findById(Integer id) {
        Autor autor = autorRepositorio.selectById(id);
        return autor;
    }

    @Transactional(readOnly = true)
    public List autores() {
        return autorRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public List autoresAlfabeticamente() {
        return autorRepositorio.listarAlfabeticamente();
    }

    @Transactional(readOnly = true)
    public List autoresActivos() {
        return autorRepositorio.autoresActivos();
    }

    @Transactional
    public void guardar(Autor autor) throws ErrorServicio {
        if (autor.getNombre() != null && !autor.getNombre().isEmpty()) {
            autor.setAlta(true);
            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("El nombre no puede estar vacío. Intentelo nuevamente");
        }
    }

    @Transactional
    public void altaBaja(Integer id) throws ErrorServicio {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {

        }
    }

    @Transactional
    public void actualizar(Autor autor) {
        autorRepositorio.actualizar(autor.getNombre(), autor.getId());
    }

    @Transactional
    public void eliminar(Integer id) throws ErrorServicio {
        try {
            autorRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new ErrorServicio("El autor no existe o fue eliminado previamente");
        }
    }
}
