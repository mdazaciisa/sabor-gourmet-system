# 🍽️ Sabor Gourmet — Sistema de Gestión (Spring Boot + PostgreSQL)

Aplicación web para la gestión de **Clientes**, **Mesas** y **Reservas** en un restaurante.  
El proyecto implementa el patrón **MVC**, utilizando **Spring Boot**, **Thymeleaf**, **JPA/Hibernate** y **PostgreSQL** para el manejo de datos.

---

## 🚀 Tecnologías Utilizadas

- Java 17  
- Spring Boot 3.x  
- Spring Web  
- Spring Data JPA  
- PostgreSQL  
- Thymeleaf  
- Bootstrap 5  
- Maven  

---

## 📌 Funcionalidades del Sistema

### ✔ Gestión de Clientes (CRUD)
- Crear
- Listar
- Ver detalles
- Editar
- Eliminar

### ✔ Gestión de Mesas (CRUD)
- Crear
- Listar
- Ver detalles
- Editar
- Eliminar

### ✔ Gestión de Reservas
- Crear reservas asociadas a mesas y clientes  
- Validación de disponibilidad  
- Listar y administrar reservas

### ✔ Panel de Administración
- Acceso rápido a todos los módulos  
- Vista general de clientes, mesas y reservas  

---

## 📁 Estructura del Proyecto (MVC)
```bash
src/main/java/cl/ipss/sabor_gourmet/
│
├── controllers/ # Controladores web
├── models/ # Entidades JPA
├── repositories/ # Interfaces JpaRepository
├── services/ # Lógica de negocio
└── SaborGourmetApplication.java

src/main/resources/
│
├── templates/ # Vistas Thymeleaf
└── application.properties
```

---

## ⚙️ Configuración de Base de Datos (PostgreSQL)

Archivo `application.properties` utilizado:
spring.application.name=sabor_gourmet
spring.datasource.url=jdbc:postgresql://localhost:5432/sabor_gourmet
spring.datasource.username=admin
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

---

## ▶️ Cómo Ejecutar el Proyecto

1. Clona el repositorio:

   ```bash
   git clone https://github.com/tuusuario/nombre-del-repositorio.git
2. Abre el proyecto en IntelliJ IDEA o VS Code
3. Crea la base de datos en PostgreSQL:
    CREATE DATABASE sabor_gourmet;
4. Verifica las credenciales en application.properties
5. Ejecuta la aplicación
6. Accede a:
    http://localhost:8080/admin 

---

## 🗄️ Poblar la Base de Datos
```md
Ejecutar después de que Hibernate genere las tablas: 

```sql
TRUNCATE TABLE cliente RESTART IDENTITY CASCADE;
TRUNCATE TABLE mesa RESTART IDENTITY CASCADE;

-- Datos tabla cliente
INSERT INTO cliente (nombre, apellido, telefono, email) VALUES
('Maria', 'Naranjo', '987654321', 'maria.naranjo@example.com'),
('Carolina', 'Muñoz', '956321478', 'carolina.munoz@example.com'),
('Javier', 'Soto', '945612378', 'javier.soto@example.com'),
('Andrea', 'Pérez', '912345678', 'andrea.perez@example.com'),
('Felipe', 'Rojas', '923458761', 'felipe.rojas@example.com');

-- Datos tabla mesa
INSERT INTO mesa (capacidad, ubicacion, disponible) VALUES
(2, 'Interior', true),
(4, 'Interior', true),
(6, 'Interior', true),
(2, 'Terraza', true),
(4, 'Terraza', true),
(6, 'Terraza', true),
(4, 'Patio', true),
(2, 'Patio', true),
(4, 'VIP', true),
(6, 'VIP', true);

---

## 📄 Licencia

Proyecto académico — uso educativo.