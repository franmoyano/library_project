package egg.libreria.controladores;

import egg.libreria.entidades.Editorial;
import egg.libreria.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libreria/editorial")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("")
    public String editorial(Model model) {
        model.addAttribute("titulo", "EDITORIAL");
        return ("editorial.html");
    }

    @GetMapping("/lista-editoriales")
    public String editoriales(Model model) {
        model.addAttribute("titulo", "EDITORIALES");
        model.addAttribute("editoriales", editorialServicio.findAll());
        return ("lista-editoriales.html");
    }

    @GetMapping("/cargar")
    public String cargar(Editorial editorial) {
        return ("cargar-editorial.html");
    }

    @PostMapping("/guardar")
    public String guardar(Model model, Editorial editorial) {
        try {
            editorialServicio.save(editorial);
            return ("redirect:/libreria/editorial/lista-editoriales");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "cargar-editorial.html";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(Model model, @PathVariable(name = "id") Integer id) {
        try {
            editorialServicio.eliminar(id);
            model.addAttribute("success", "Editorial eliminada con éxito");
            model.addAttribute("titulo", "EDITORIALES");
            model.addAttribute("editoriales", editorialServicio.findAll());
            return "lista-editoriales.html";
        } catch (Exception e) {
            model.addAttribute(e.getMessage());
            model.addAttribute("titulo", "EDITORIALES");
            model.addAttribute("editoriales", editorialServicio.findAll());
            return "lista-editoriales.html";
        }
    }

}