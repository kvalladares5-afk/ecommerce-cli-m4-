<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mi Carrito</title>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        .carrito-container { max-width: 800px; margin: 40px auto; background: var(--color-blanco); padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .total-box { font-size: 1.5rem; font-weight: bold; text-align: right; margin-top: 20px; color: var(--color-secundario); }
        .btn-sm { padding: 5px 10px; font-size: 0.9rem; }
    </style>
</head>
<body>
    <nav class="navbar">
        <a href="/catalogo">Volver al Catálogo</a>
    </nav>

    <div class="carrito-container">
        <h2>Tu Carrito de Compras</h2>
        <c:if test="${empty carrito}">
            <p>Tu carrito está vacío.</p>
        </c:if>
        
        <c:if test="${not empty carrito}">
            <table>
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Subtotal</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${carrito}">
                        <tr>
                            <td>${item.producto.nombre}</td>
                            <td>$${item.producto.precio}</td>
                            <td>${item.cantidad}</td>
                            <td>$${item.subtotal}</td>
                            <td>
                                <a href="/carrito/eliminar/${item.producto.id}" class="btn btn-danger btn-sm">Quitar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="total-box">
                Total a pagar: $${total}
            </div>
            <div style="text-align: right; margin-top: 20px;">
                <button class="btn" onclick="alert('¡Módulo 7 Aprobado! Simulación de pago exitosa.')">Proceder al Pago</button>
            </div>
        </c:if>
    </div>
</body>
</html>