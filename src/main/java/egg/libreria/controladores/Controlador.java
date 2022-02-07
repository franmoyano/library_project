
package egg.libreria.controladores;

import egg.libreria.servicios.AutorServicio;
import egg.libreria.servicios.EditorialServicio;
import egg.libreria.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Controlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private LibroServicio libroServicio;
    
    @Autowired
    private EditorialServicio editServicio;
    
    @GetMapping("/")
    public String index(Model model){
        return ("redirect:/libreria");
    }
    
    @GetMapping("/libreria")
    public String libreria(Model model, @RequestParam(required = false) String mensaje){
        model.addAttribute("titulo", "LIBRERIA");
        model.addAttribute("inicio", "");
        model.addAttribute("mensaje", mensaje);
        return ("index.html");
    }
    
}
