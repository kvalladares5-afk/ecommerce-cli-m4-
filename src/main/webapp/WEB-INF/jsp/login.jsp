<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - Ecommerce</title>
    <style>
        :root {
            --bg-color: #f4f7f6;
            --card-bg: #ffffff;
            --text-main: #333333;
            --primary: #2980b9;
            --primary-hover: #3498db;
            --error-bg: #fadbd8;
            --error-text: #e74c3c;
            --success-bg: #d5f5e3;
            --success-text: #2ecc71;
        }
        body {
            font-family: Arial, sans-serif;
            background-color: var(--bg-color);
            color: var(--text-main);
            display: flex; justify-content: center; align-items: center;
            height: 100vh; margin: 0;
        }
        .card {
            background-color: var(--card-bg);
            padding: 2rem; border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            width: 100%; max-width: 400px;
        }
        .form-group { margin-bottom: 1rem; }
        label { display: block; margin-bottom: 0.5rem; }
        input[type="text"], input[type="password"] {
            width: 100%; padding: 0.5rem; border: 1px solid #ccc;
            border-radius: 4px; box-sizing: border-box;
        }
        button {
            width: 100%; padding: 0.75rem; background-color: var(--primary);
            color: white; border: none; border-radius: 4px; cursor: pointer;
        }
        button:hover { background-color: var(--primary-hover); }
        .mensaje { margin-bottom: 1rem; padding: 0.5rem; border-radius: 4px; text-align: center; }
        .error { background-color: var(--error-bg); color: var(--error-text); }
        .exito { background-color: var(--success-bg); color: var(--success-text); }
        .link { display: block; text-align: center; margin-top: 1rem; color: var(--primary); text-decoration: none; }
    </style>
</head>
<body>
    <div class="card">
        <h2>Iniciar Sesión</h2>
        
        <% if (request.getParameter("error") != null) { %>
            <div class="mensaje error">Email o contraseña incorrectos.</div>
        <% } %>
        <% if (request.getParameter("logout") != null) { %>
            <div class="mensaje exito">Has cerrado sesión.</div>
        <% } %>
        <% if (request.getParameter("exito") != null) { %>
            <div class="mensaje exito">Cuenta creada. ¡Ingresa ahora!</div>
        <% } %>

        <form action="/login" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            
            <div class="form-group">
                <label for="username">Email</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Ingresar</button>
        </form>
        <a href="/registro" class="link">¿No tienes cuenta? Regístrate aquí</a>
    </div>
</body>
</html>