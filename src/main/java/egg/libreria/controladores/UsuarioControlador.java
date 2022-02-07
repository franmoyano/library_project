
package egg.libreria.controladores;

import egg.libreria.excepciones.ErrorServicio;
import egg.libreria.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libreria/user")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/sign-up")
    public String registro(Model model) {
        return "registro.html";
    }
    
    @PostMapping("/sign-up")
    public String registroSave(Model model, @RequestParam String username, @RequestParam String password1, @RequestParam String password2) {
        try {
            usuarioServicio.save(username, password1, password2);
            String mensaje = "Usuario creado satisfactoriamente";
            return "redirect:/libreria?mensaje=" + mensaje;
        } catch (ErrorServicio error) {
            //System.out.println(error.getMessage());
            model.addAttribute("error", error.getMessage());
            return "registro.html";
        }
    }
}
