package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Producto;
import service.Carrito;
import service.DescuentoPorMonto;
import exception.CantidadInvalidaException;
import java.util.List;

public class TiendaTest {

    @Test
    void testCalcularTotalBase() throws CantidadInvalidaException {
        // MODO MENTOR: Probamos que 100 * 2 sea 200.
        Carrito carrito = new Carrito();
        Producto p = new Producto("1", "Test", "Cat", 100.0);
        carrito.agregar(p, 2);
        
        assertEquals(200.0, carrito.calcularTotalBase(), "El total base debe ser 200");
    }

    @Test
    void testValidacionCantidadInvalida() {
        // MODO MENTOR: Verificamos que el sistema realmente lance la excepción si ponemos -1.
        Carrito carrito = new Carrito();
        Producto p = new Producto("1", "Test", "Cat", 100.0);
        
        assertThrows(CantidadInvalidaException.class, () -> {
            carrito.agregar(p, -1);
        }, "Debe lanzar CantidadInvalidaException para cantidades <= 0");
    }

    @Test
    void testAplicacionDescuentoMonto() {
        // MODO MENTOR: Si el total es 1000 y el descuento es 10%, debe devolver 100.
        DescuentoPorMonto regla = new DescuentoPorMonto(500.0, 10.0);
        double descuento = regla.aplicar(1000.0, List.of());
        
        assertEquals(100.0, descuento, "El descuento deberia ser el 10% de 1000");
    }
}