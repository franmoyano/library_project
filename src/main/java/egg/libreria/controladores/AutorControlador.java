package egg.libreria.controladores;

import egg.libreria.entidades.Autor;
import egg.libreria.excepciones.ErrorServicio;
import egg.libreria.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("libreria/autor")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("")
    public String autorIndex(Model model) {
        model.addAttribute("titulo", "AUTOR");
        return ("autor.html");
    }

    @GetMapping("/lista-autores")
    public String autores(Model model) {
        model.addAttribute("titulo", "AUTORES");
        model.addAttribute("elementos", autorServicio.autores());
        return ("lista-autores.html");
    }

    @GetMapping("/lista-autores-activos")
    public String autoresActivos(Model model) {
        model.addAttribute("titulo", "AUTORES ACTIVOS");
        model.addAttribute("elementos", autorServicio.autoresActivos());
        return ("lista-autores.html");
    }

    @GetMapping("/cargar")
    public String cargar(Autor autor, Model model) {
        model.addAttribute("titulo", "CARGAR AUTOR");
        model.addAttribute("pholder","Introduce el nombre aquí");
        return ("cargar-autor.html");
    }

    @PostMapping("/guardar")
    public String guardar(Model model, Autor autor) {
        try {
            if(autorServicio.findById(autor.getId()) != null) {
                autorServicio.actualizar(autor);
            } else {
                autorServicio.guardar(autor);
                model.addAttribute("titulo", "AUTORES");
                model.addAttribute("elementos", autorServicio.autores());
            }
            return ("lista-autores.html");
        } catch (Exception e) {
            model.addAttribute("titulo", "ERROR AL GUARDAR");
            model.addAttribute("error", e.getMessage());
            return "cargar-autor.html";
        }
    }

    @GetMapping("/eliminar")
    public String eliminar(Model model, @RequestParam Integer id) {
        try {
            autorServicio.eliminar(id);
            model.addAttribute("success", "Autor eliminado con éxito");
            model.addAttribute("titulo", "AUTORES");
            model.addAttribute("elementos", autorServicio.autores());
            return "lista-autores.html";
        } catch (Exception e) {
            e.getMessage();
            return "redirect:/libreria/autor/listar-autores";
        }
    }

    @GetMapping("/editar")
    public String editarPantalla(Model model, Autor autor, @RequestParam Integer id) {
        model.addAttribute("esAutor", true);
        model.addAttribute("autor", autorServicio.findById(id));
        model.addAttribute("titulo", "EDITAR AUTOR");
        return "editar.html";
    }

    @PostMapping("/editar")
    public String editar(Model model, Autor autor) {
        autorServicio.actualizar(autor);
        return "redirect:/libreria/autor/lista-autores";
    }
}


