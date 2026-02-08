package service;

import model.ItemCarrito;
import java.util.List;

public class DescuentoPorCategoria implements ReglaDescuento {
    private String categoria;
    private double porcentaje;

    public DescuentoPorCategoria(String categoria, double porcentaje) {
        this.categoria = categoria;
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicar(double totalBase, List<ItemCarrito> items) {
        boolean tieneCategoria = items.stream()
            .anyMatch(item -> item.getProducto().getCategoria().equalsIgnoreCase(categoria));
        
        return tieneCategoria ? totalBase * (porcentaje / 100) : 0;
    }

    @Override
    public String getDescripcion() {
        return "Descuento por incluir productos de " + categoria + " (" + porcentaje + "%)";
    }
}