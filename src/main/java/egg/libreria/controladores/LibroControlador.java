package egg.libreria.controladores;

import egg.libreria.entidades.Autor;
import egg.libreria.entidades.Editorial;
import egg.libreria.entidades.Libro;
import egg.libreria.servicios.AutorServicio;
import egg.libreria.servicios.EditorialServicio;
import egg.libreria.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("libreria/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

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
    public String guardarPantalla(Model model, Libro libro) {
        model.addAttribute("autores", autorServicio.autores());
        model.addAttribute("editoriales", editorialServicio.findAll());
        return "cargar-libro.html";
    }

    @PostMapping("/guardar")
    public String guardar(Model model, Libro libro) {
        try {
            libroServicio.save(libro);
            return "redirect:/libreria/libro/lista-libros";
        } catch (Exception e) {
            model.addAttribute("autores", autorServicio.autores());
            model.addAttribute("editoriales", editorialServicio.findAll());
            model.addAttribute("error", e.getMessage());
            return "cargar-libro.html";
        }
    }
}

