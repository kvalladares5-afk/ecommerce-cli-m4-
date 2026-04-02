package com.ejemplo.controlador;

import com.ejemplo.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "catalogo"; 
    }
}