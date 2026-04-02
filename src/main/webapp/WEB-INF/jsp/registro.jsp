<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro - Ecommerce</title>
    <style>
        :root {
            --bg-color: #f4f7f6; --card-bg: #ffffff;
            --text-main: #333333; --primary: #2980b9; --primary-hover: #3498db;
        }
        body {
            font-family: Arial, sans-serif; background-color: var(--bg-color);
            color: var(--text-main); display: flex; justify-content: center;
            align-items: center; height: 100vh; margin: 0;
        }
        .card {
            background-color: var(--card-bg); padding: 2rem; border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1); width: 100%; max-width: 400px;
        }
        .form-group { margin-bottom: 1rem; }
        label { display: block; margin-bottom: 0.5rem; }
        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%; padding: 0.5rem; border: 1px solid #ccc;
            border-radius: 4px; box-sizing: border-box;
        }
        button {
            width: 100%; padding: 0.75rem; background-color: var(--primary);
            color: white; border: none; border-radius: 4px; cursor: pointer;
        }
        button:hover { background-color: var(--primary-hover); }
        .link { display: block; text-align: center; margin-top: 1rem; color: var(--primary); text-decoration: none; }
    </style>
</head>
<body>
    <div class="card">
        <h2>Crear Cuenta</h2>
        <form action="/registro" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            
            <div class="form-group">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" required>
            </div>
            <div class="form-group">
                <label for="apellido">Apellido</label>
                <input type="text" id="apellido" name="apellido" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Registrarse</button>
        </form>
        <a href="/login" class="link">¿Ya tienes cuenta? Inicia sesión</a>
    </div>
</body>
</html>