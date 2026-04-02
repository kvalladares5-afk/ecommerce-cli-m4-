# E-Commerce Full Stack Java (Portafolio Final)

## 📌 Propósito del Proyecto
Versión final y operativa del sistema E-commerce desarrollado como producto de portafolio para el Módulo 7. Integra gestión de usuarios, roles de seguridad, catálogo dinámico y un carrito de compras funcional.

* **Enlace al repositorio público en GitHub:** `https://github.com/kvalladares5-afk/ecommerce-cli-m4-`

## 🚀 Arquitectura y Mejoras UI/UX
Aunque el estándar base sugería Bootstrap, este proyecto fue optimizado construyendo una interfaz completamente responsiva mediante **CSS Puro y Variables Nativas (`:root`)**. Esto reduce drásticamente la carga del DOM, elimina dependencias externas pesadas y demuestra un control avanzado sobre el diseño frontend y la eficiencia del renderizado.

## ⚙️ Instrucciones de Ejecución Local

### Requisitos Previos:
* Java Development Kit (JDK) 21.
* Apache Maven 3.9+.
* Base de datos MySQL corriendo en el puerto 3306.

### Variables y Configuración (`application.properties`):
Asegúrate de configurar tus credenciales locales en `src/main/resources/application.properties`:
\`\`\`properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
\`\`\`

### Cómo iniciar la app:
1. Clona este repositorio.
2. Abre la terminal (VSC) en la raíz del proyecto.
3. Ejecuta el comando: `mvn clean spring-boot:run`
4. Accede en el navegador a: `http://localhost:8080/`

## 🗺️ Mapa de Rutas y Permisos

| Ruta | Acceso | Descripción |
|------|--------|-------------|
| `/login` | Público | Formulario de inicio de sesión. |
| `/registro` | Público | Creación de cuenta nueva (asigna rol CLIENT). |
| `/catalogo` | Autenticados (CLIENT/ADMIN) | Vista de productos disponibles. |
| `/carrito` | Autenticados (CLIENT/ADMIN) | Gestión de compras y totales. |
| `/admin/productos` | Solo ADMIN | Panel CRUD para gestión del inventario. |

## 🔑 Credenciales de Prueba por Defecto
El sistema cuenta con un `DataLoader` que inicializa automáticamente un administrador en la base de datos si no existe.

* **Cuenta ADMIN:**
  * **Email:** `admin@tienda.com`
  * **Password:** `admin123`
* **Cuenta CLIENT:**
  * Puede crearse dinámicamente desde la ruta pública `/registro`.

## 📸 Capturas de Pantalla
*(Agrega aquí las capturas de pantalla solicitadas de tu Home, Carrito y Vista Admin insertando las imágenes en la carpeta raíz).*