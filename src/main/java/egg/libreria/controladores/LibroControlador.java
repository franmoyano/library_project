package egg.libreria.controladores;

import egg.libreria.entidades.Libro;
import egg.libreria.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("libreria/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("titulo", "LIBRO");
        return "libro.html";
    }

    @GetMapping("/lista-libros")
    public String libros(Model model) {
        model.addAttribute("titulo", "LIBROS");
        model.addAttribute("libros", libroServicio.findAll());
        return "lista-libros.html";
    }

    @GetMapping("/guardar")
    public String guardar(Model model, Libro libro) {
        return "cargar-libro.html";
    }
}
