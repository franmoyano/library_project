
package egg.libreria.repositorios;

import egg.libreria.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Integer> {
    
    @Query("SELECT a FROM Autor a ORDER BY a.nombre")
    public List<Autor> listarAlfabeticamente();

    @Query("SELECT a FROM Autor a WHERE a.alta=true")
    public List<Autor> autoresActivos();

    @Query("SELECT a FROM Autor a WHERE a.id = :id")
    public Autor selectById(@Param("id") Integer id);

    @Modifying
    @Query("UPDATE Autor a SET a.nombre = :nombre WHERE a.id = :id")
    public void actualizar(@Param("nombre") String nombre, @Param("id") Integer id);
    
}
