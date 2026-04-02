<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Catálogo - Ecommerce</title>
    <style>
        :root {
            --bg-body: #f4f7f6; --nav-bg: #2c3e50; --text-light: #ecf0f1;
            --card-bg: #fff; --text-main: #333; --primary: #2980b9;
        }
        body {
            font-family: Arial, sans-serif; background-color: var(--bg-body);
            color: var(--text-main); margin: 0; padding: 0;
        }
        .navbar {
            background-color: var(--nav-bg); padding: 1rem 2rem;
            display: flex; justify-content: space-between; align-items: center;
            color: var(--text-light);
        }
        .btn-logout {
            background-color: #e74c3c; color: white; border: none;
            padding: 0.5rem 1rem; border-radius: 4px; cursor: pointer; text-decoration: none;
        }
        .container { padding: 2rem; max-width: 1000px; margin: auto; }
        .grid {
            display: grid; grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); gap: 1.5rem;
        }
        .card {
            background: var(--card-bg); padding: 1.5rem; border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1); text-align: center;
        }
        .precio { color: var(--primary); font-size: 1.25rem; font-weight: bold; }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>Mi Tienda Pro</h2>
        <form action="/logout" method="POST" style="margin: 0;">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn-logout">Cerrar Sesión</button>
        </form>
    </div>

    <div class="container">
        <h1>Catálogo de Productos</h1>
        
        <c:if test="${empty productos}">
            <p>El catálogo está vacío. El administrador debe agregar productos.</p>
        </c:if>

        <div class="grid">
            <c:forEach var="prod" items="${productos}">
                <div class="card">
                    <h3>${prod.nombre}</h3>
                    <p class="precio">$${prod.precio}</p>
                    <button style="background: var(--primary); color: white; border: none; padding: 0.5rem; width: 100%; border-radius: 4px; cursor: pointer;">Agregar al Carrito</button>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>