package service;

import model.ItemCarrito;
import java.util.List;

public class DescuentoPorMonto implements ReglaDescuento {
    private double min;
    private double porcentaje;

    public DescuentoPorMonto(double min, double porcentaje) {
        this.min = min;
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicar(double totalBase, List<ItemCarrito> items) {
        return (totalBase >= min) ? totalBase * (porcentaje / 100) : 0;
    }

    @Override
    public String getDescripcion() {
        return "Descuento por compra superior a $" + min + " (" + porcentaje + "%)";
    }
}