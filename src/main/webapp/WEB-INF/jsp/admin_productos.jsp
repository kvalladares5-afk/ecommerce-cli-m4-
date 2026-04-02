<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Administración de Productos</title>
    <style>
        :root {
            --bg-body: #f4f7f6; --nav-bg: #2c3e50; --card-bg: #fff;
            --primary: #2980b9; --danger: #e74c3c; --text-light: #fff;
        }
        body { font-family: Arial, sans-serif; background: var(--bg-body); margin: 0; }
        .navbar { background: var(--nav-bg); padding: 1rem 2rem; color: var(--text-light); display: flex; justify-content: space-between;}
        .container { padding: 2rem; max-width: 1000px; margin: auto; }
        table { width: 100%; border-collapse: collapse; background: var(--card-bg); box-shadow: 0 4px 6px rgba(0,0,0,0.1); margin-top: 1rem;}
        th, td { padding: 1rem; text-align: left; border-bottom: 1px solid #ddd; }
        th { background: var(--primary); color: white; }
        .btn { padding: 0.5rem 1rem; border: none; border-radius: 4px; color: white; cursor: pointer; text-decoration: none; display: inline-block; }
        .btn-primary { background: var(--primary); }
        .btn-danger { background: var(--danger); }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>Panel de Administración</h2>
        <a href="/catalogo" class="btn btn-primary" style="background: #34495e;">Ver Catálogo Público</a>
    </div>

    <div class="container">
        <h1>Gestión de Productos</h1>
        <a href="/admin/productos/nuevo" class="btn btn-primary">+ Nuevo Producto</a>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="prod" items="${productos}">
                    <tr>
                        <td>${prod.id}</td>
                        <td>${prod.nombre}</td>
                        <td>$${prod.precio}</td>
                        <td>
                            <a href="/admin/productos/editar/${prod.id}" class="btn btn-primary">Editar</a>
                            <a href="/admin/productos/eliminar/${prod.id}" class="btn btn-danger">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>