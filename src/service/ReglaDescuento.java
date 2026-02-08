package service;

import model.ItemCarrito;
import java.util.List;

public interface ReglaDescuento {
    double aplicar(double totalBase, List<ItemCarrito> items);
    String getDescripcion();
}