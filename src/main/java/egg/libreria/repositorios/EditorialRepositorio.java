
package egg.libreria.repositorios;

import egg.libreria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, Integer> {

    @Query("SELECT e FROM Editorial e WHERE e.id = :id")
    public Editorial selectById(@Param("id") Integer id);

    @Modifying
    @Query("UPDATE Editorial e SET e.nombre = :nombre WHERE e.id = :id")
    public void actualizar(@Param("nombre") String nombre, @Param("id") Integer id);
}
