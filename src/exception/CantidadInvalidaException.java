package exception;

// Esta excepción se lanzará cuando el usuario ingrese cantidad <= 0 [cite: 21, 30]
public class CantidadInvalidaException extends Exception {
    public CantidadInvalidaException(String mensaje) {
        super(mensaje);
    }
}