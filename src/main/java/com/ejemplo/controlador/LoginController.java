package com.ejemplo.controlador;

import com.ejemplo.modelo.Usuario;
import com.ejemplo.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Renderiza login.jsp
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro"; // Renderiza registro.jsp
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        // Si todo sale bien, lo enviamos al login con un mensaje de éxito
        return "redirect:/login?exito";
    }
    
    @GetMapping("/")
    public String inicio() {
        // Redirección temporal al catálogo que haremos más adelante
        return "redirect:/catalogo"; 
    }
}