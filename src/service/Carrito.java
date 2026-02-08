package service;

import model.ItemCarrito;
import model.Producto;
import exception.CantidadInvalidaException;
import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public void agregar(Producto p, int cantidad) throws CantidadInvalidaException {
        if (cantidad <= 0) {
            throw new CantidadInvalidaException("La cantidad debe ser mayor a cero.");
        }
        items.add(new ItemCarrito(p, cantidad));
    }

    public void quitar(String productoId) {
        items.removeIf(item -> item.getProducto().getId().equals(productoId));
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void vaciar() {
        items.clear();
    }

    public double calcularTotalBase() {
        return items.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
    }
}