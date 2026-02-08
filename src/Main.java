import java.util.List;
import java.util.Scanner;
import exception.CantidadInvalidaException;
import service.Carrito;
import service.Catalogo;
import service.DescuentoPorCategoria;
import service.DescuentoPorMonto;
import service.ReglaDescuento;

public class Main {
    private static Catalogo catalogo = new Catalogo();
    private static Scanner scanner = new Scanner(System.in);
    private static Carrito carrito = new Carrito();

    private static List<ReglaDescuento> reglas = List.of(
        new DescuentoPorMonto(500.0, 10.0),
        new DescuentoPorCategoria("Electronica", 5.0)
    );

    public static void main(String[] args) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- E-COMMERCE CLI ---");
            System.out.println("1) ADMIN (Gestion de productos)");
            System.out.println("2) USUARIO (Carrito y Compra)");
            System.out.println("0) Salir");
            System.out.print("Seleccione: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1: menuAdmin(); break;
                    case 2: menuUsuario(); break;
                    case 0: System.out.println("Saliendo..."); break;
                    default: System.out.println("Opcion no valida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
            }
        }
    }

    private static void menuAdmin() {
        int opcionAdmin = -1;
        while (opcionAdmin != 0) {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1) Listar productos");
            System.out.println("2) Crear producto");
            System.out.println("3) Eliminar producto");
            System.out.println("0) Volver");
            System.out.print("Seleccione: ");

            try {
                opcionAdmin = Integer.parseInt(scanner.nextLine());
                switch (opcionAdmin) {
                    case 1: listarProductosAdmin(); break;
                    case 2: crearProductoAdmin(); break;
                    case 3: eliminarProductoAdmin(); break;
                    case 0: System.out.println("Regresando al menu principal..."); break;
                    default: System.out.println("Opcion no valida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
            }
        }
    }

    private static void listarProductosAdmin() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        if (catalogo.getProductos().isEmpty()) {
            System.out.println("No hay productos en el catalogo.");
        } else {
            catalogo.getProductos().forEach(System.out::println);
        }
    }

    private static void crearProductoAdmin() {
        System.out.print("Ingrese ID unico: ");
        String id = scanner.nextLine();
        
        if (catalogo.buscarPorId(id).isPresent()) {
            System.out.println("Error: Ya existe un producto con ese ID.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        
        System.out.print("Precio: ");
        try {
            double precio = Double.parseDouble(scanner.nextLine());
            if (precio <= 0) {
                System.out.println("Error: El precio debe ser mayor a 0.");
                return;
            }
            catalogo.agregarProducto(new model.Producto(id, nombre, categoria, precio));
            System.out.println("Producto creado con exito.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un precio numerico valido.");
        }
    }

    private static void eliminarProductoAdmin() {
        System.out.print("Ingrese ID del producto a eliminar: ");
        String id = scanner.nextLine();
        
        System.out.print("¿Esta seguro de eliminar el producto " + id + "? (S/N): ");
        String confirmar = scanner.nextLine();
        
        if (confirmar.equalsIgnoreCase("S")) {
            if (catalogo.eliminarProducto(id)) {
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("No se encontro el producto.");
            }
        }
    }

    private static void menuUsuario() {
        int opcionUser = -1;
        while (opcionUser != 0) {
            System.out.println("\n--- MENU USUARIO ---");
            System.out.println("1) Listar / Buscar productos");
            System.out.println("2) Agregar al carrito");
            System.out.println("3) Ver carrito");
            System.out.println("4) Ver descuentos activos");
            System.out.println("5) Confirmar compra");
            System.out.println("0) Volver");
            System.out.print("Seleccione: ");

            try {
                opcionUser = Integer.parseInt(scanner.nextLine());
                switch (opcionUser) {
                    case 1: listarProductosAdmin(); break;
                    case 2: agregarAlCarrito(); break;
                    case 3: verCarrito(); break;
                    case 4: verDescuentosActivos(); break;
                    case 5: confirmarCompra(); break;
                    case 0: break;
                    default: System.out.println("Opcion no valida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void agregarAlCarrito() throws CantidadInvalidaException {
        System.out.print("ID del producto: ");
        String id = scanner.nextLine();
        var productoOpt = catalogo.buscarPorId(id);
        
        if (productoOpt.isPresent()) {
            System.out.print("Cantidad: ");
            try {
                int cant = Integer.parseInt(scanner.nextLine());
                carrito.agregar(productoOpt.get(), cant);
                System.out.println("Agregado con exito.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese una cantidad entera valida.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void verCarrito() {
        System.out.println("\n--- TU CARRITO ---");
        if (carrito.getItems().isEmpty()) {
            System.out.println("El carrito esta vacio.");
        } else {
            carrito.getItems().forEach(System.out::println);
            System.out.printf("TOTAL BASE: $%.2f\n", carrito.calcularTotalBase());
        }
    }

    private static void confirmarCompra() {
        if (carrito.getItems().isEmpty()) {
            System.out.println("Error: El carrito esta vacio. No puedes confirmar la compra.");
            return;
        }

        System.out.println("\n--- RESUMEN DE COMPRA ---");
        carrito.getItems().forEach(System.out::println);

        double totalBase = carrito.calcularTotalBase();
        double descuentoTotal = calcularDescuentos(totalBase);
        double totalFinal = totalBase - descuentoTotal;

        System.out.println("---------------------------");
        System.out.printf("TOTAL BASE:      $%.2f\n", totalBase);
        System.out.printf("DESCUENTOS:     -$%.2f\n", descuentoTotal);
        System.out.printf("TOTAL A PAGAR:   $%.2f\n", totalFinal);
        System.out.println("---------------------------");

        System.out.print("¿Desea finalizar el pago? (S/N): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            System.out.println("¡Compra realizada con exito! Generando orden...");
            carrito.vaciar();
        } else {
            System.out.println("Compra cancelada. Los productos siguen en tu carrito.");
        }
    }

    private static double calcularDescuentos(double totalBase) {
        double totalDescuento = 0;
        System.out.println("\n--- DESCUENTOS APLICADOS ---");
        
        for (ReglaDescuento regla : reglas) {
            double desc = regla.aplicar(totalBase, carrito.getItems());
            if (desc > 0) {
                System.out.printf("- %s: -$%.2f\n", regla.getDescripcion(), desc);
                totalDescuento += desc;
            }
        }
        
        if (totalDescuento == 0) System.out.println("Ningun descuento aplicado.");
        return totalDescuento;
    }

    private static void verDescuentosActivos() {
        System.out.println("\n--- REGLAS DE DESCUENTO VIGENTES ---");
        reglas.forEach(r -> System.out.println("* " + r.getDescripcion()));
    }
}
