package egg.libreria.servicios;

import egg.libreria.entidades.Editorial;
import egg.libreria.excepciones.ErrorServicio;
import egg.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public List findAll() {
        return editorialRepositorio.findAll();
    }

    @Transactional
    public void save(Editorial editorial) throws ErrorServicio {
        
            if (editorial.getNombre() != null && !editorial.getNombre().isEmpty()) {
                editorial.setAlta(true);
                editorialRepositorio.save(editorial);
            } else {
                throw new ErrorServicio("El nombre no puede estar vacío. Intentelo nuevamente");
            }
        
    }

    @Transactional
    public Editorial findById(Integer id) {
        return editorialRepositorio.selectById(id);
    }

    @Transactional
    public void actualizar(Editorial editorial) {
        editorialRepositorio.actualizar(editorial.getNombre(), editorial.getId());
    }

    @Transactional
    public void eliminar(Integer id) throws ErrorServicio {
        try {
            editorialRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new ErrorServicio("No se pudo eliminar la editorial");
        }
    }
}
