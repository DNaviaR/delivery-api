# 🍕 Delivery API

Una API RESTful completa para la gestión de pedidos de comida, construida con **Java Spring Boot** y **PostgreSQL**. El proyecto implementa seguridad, roles de usuario, lógica de negocio para pedidos y está totalmente contenerizado con **Docker**.

## 🚀 Tecnologías Utilizadas

* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3
* **Seguridad:** Spring Security (Basic Auth + BCrypt)
* **Base de Datos:** PostgreSQL 15 (Dockerizado)
* **ORM:** JPA / Hibernate
* **Contenerización:** Docker & Docker Compose
* **Herramientas:** Maven, Lombok, Postman

## ⚙️ Características

* **Autenticación y Autorización:**
    * Registro de usuarios con contraseñas encriptada.
    * Roles diferenciados: `ADMIN` (Gestiona menú) y `USER` (Realiza pedidos).
* **Gestión de Datos:**
    * CRUD de Productos.
    * Creación de Pedidos con cálculo automático de totales.
    * Persistencia de datos en contenedor PostgreSQL.
* **Frontend Básico:**
    * Cliente HTML/JS simple para visualizar el menú (consumo de API con Fetch y CORS habilitado).

## 🛠️ Instalación y Ejecución

### Requisitos previos
* Docker Desktop instalado y corriendo.
* Java 17 (Opcional si usas Docker, necesario para compilar).

### Opción A: Ejecución Rápida con Docker (Recomendada)

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/DNaviaR/delivery-api](https://github.com/DNaviaR/delivery-api)
    cd delivery-api
    ```

2.  **Generar el ejecutable .jar:**
    ```bash
    ./mvnw clean package -DskipTests
    ```
    *(En Windows puede requerir `mvn clean package -DskipTests` si no tienes el wrapper configurado).*

3.  **Levantar la infraestructura:**
    ```bash
    docker-compose up --build
    ```

4.  **Acceso:**
    * La API estará disponible en: `http://localhost:8080`
    * La Base de Datos PostgreSQL en el puerto: `5432`

---

## 🔌 Endpoints de la API

| Método | Endpoint | Rol Requerido | Descripción |
| :--- | :--- | :--- | :--- |
| **POST** | `/auth/register` | Público | Registrar un nuevo usuario. |
| **GET** | `/products` | Autenticado | Ver el listado de productos. |
| **POST** | `/products` | ADMIN | Crear un nuevo producto (Pizza, Bebida...). |
| **POST** | `/orders` | USER | Crear un pedido con una lista de IDs. |
| **GET** | `/orders` | USER | Ver historial de pedidos propios. |

### 🧪 Usuarios de Prueba (Setup Inicial)

Al levantar Docker, la base de datos estará vacía. Usa **Postman** para crear los usuarios iniciales:

**1. Crear Administrador (Chef)**
* **POST** `/auth/register`
* **Body:**
    ```json
    {
        "username": "chef_ramsay",
        "password": "123",
        "role": "ADMIN"
    }
    ```

**2. Crear Cliente**
* **POST** `/auth/register`
* **Body:**
    ```json
    {
        "username": "pepe_cliente",
        "password": "123",
        "role": "USER"
    }
    ```

---

## 🌍 Frontend (Demo)

El proyecto incluye un archivo `index.html` en la raíz para probar la conexión CORS.
1.  Asegúrate de haber creado al usuario `chef_ramsay` (paso anterior).
2.  Abre el archivo `index.html` en tu navegador.
3.  Haz clic en "Ver Menú" para cargar los productos desde el backend.

## 📂 Estructura del Proyecto

src/main/java/com/delivery/api 
├── config/ # Configuraciones (Security, CORS) 
├── controller/ # Endpoints REST (Auth, Order, Product) 
├── dto/ # Objetos de transferencia de datos (OrderRequest) 
├── model/ # Entidades JPA (User, Product, Order) 
├── repository/ # Interfaces de acceso a datos (JPA Repositories) 
└── DeliveryApiApplication.java
