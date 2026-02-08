package service;

import model.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Catalogo {
    private List<Producto> productos;

    public Catalogo() {
        this.productos = new ArrayList<>();

        productos.add(new Producto("1", "Monitor 24'", "Electronica", 150.0));
        productos.add(new Producto("2", "Teclado Mecanico", "Perifericos", 80.0));
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos); 
    }

    public List<Producto> buscar(String criterio) {
        return productos.stream()
            .filter(p -> p.getNombre().toLowerCase().contains(criterio.toLowerCase()) || 
                         p.getCategoria().toLowerCase().contains(criterio.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean eliminarProducto(String id) {
        return productos.removeIf(p -> p.getId().equals(id));
    }

    public Optional<Producto> buscarPorId(String id) {
        return productos.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
    }
}