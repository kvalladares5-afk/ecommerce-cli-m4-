<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario Producto</title>
    <style>
        :root { --bg-color: #f4f7f6; --card-bg: #fff; --primary: #2980b9; }
        body { font-family: Arial, sans-serif; background: var(--bg-color); display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .card { background: var(--card-bg); padding: 2rem; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); width: 100%; max-width: 400px; }
        .form-group { margin-bottom: 1rem; }
        label { display: block; margin-bottom: 0.5rem; }
        input[type="text"], input[type="number"] { width: 100%; padding: 0.5rem; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 0.75rem; background: var(--primary); color: white; border: none; border-radius: 4px; cursor: pointer; margin-top: 1rem;}
        .btn-cancel { display: block; text-align: center; margin-top: 1rem; color: var(--primary); text-decoration: none; }
    </style>
</head>
<body>
    <div class="card">
        <h2>Guardar Producto</h2>
        <form action="/admin/productos/guardar" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${producto.id}" />
            
            <div class="form-group">
                <label>Nombre del Producto</label>
                <input type="text" name="nombre" value="${producto.nombre}" required>
            </div>
            <div class="form-group">
                <label>Precio</label>
                <input type="number" step="0.01" name="precio" value="${producto.precio}" required>
            </div>
            <button type="submit">Guardar en Base de Datos</button>
        </form>
        <a href="/admin/productos" class="btn-cancel">Cancelar y Volver</a>
    </div>
</body>
</html>