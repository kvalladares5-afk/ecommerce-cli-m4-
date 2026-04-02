package com.ejemplo.controlador;

import com.ejemplo.modelo.ItemCarrito;
import com.ejemplo.modelo.Producto;
import com.ejemplo.repositorio.ProductoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public String verCarrito(HttpSession session, Model model) {
        List<ItemCarrito> carrito = obtenerCarrito(session);
        double total = carrito.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
        model.addAttribute("carrito", carrito);
        model.addAttribute("total", total);
        return "carrito"; 
    }

    @PostMapping("/agregar")
    public String agregarAlCarrito(@RequestParam Long productoId, @RequestParam int cantidad, HttpSession session) {
        Producto producto = productoRepository.findById(productoId).orElseThrow();
        List<ItemCarrito> carrito = obtenerCarrito(session);
        
        boolean existe = false;
        for (ItemCarrito item : carrito) {
            if (item.getProducto().getId().equals(productoId)) {
                item.setCantidad(item.getCantidad() + cantidad);
                existe = true;
                break;
            }
        }
        if (!existe) carrito.add(new ItemCarrito(producto, cantidad));
        
        session.setAttribute("carrito", carrito);
        return "redirect:/catalogo";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDelCarrito(@PathVariable Long id, HttpSession session) {
        List<ItemCarrito> carrito = obtenerCarrito(session);
        carrito.removeIf(item -> item.getProducto().getId().equals(id));
        session.setAttribute("carrito", carrito);
        return "redirect:/carrito";
    }

    @SuppressWarnings("unchecked")
    private List<ItemCarrito> obtenerCarrito(HttpSession session) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }
}