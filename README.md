# ForoHub API

![Estado](https://img.shields.io/badge/Estado-En_desarrollo-blue)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring](https://img.shields.io/badge/Spring%20Boot-3.5.4-green)

## 📖 Descripción del Proyecto

API RESTful para un foro de discusión, desarrollada como parte del Challenge de Alura Latam a través del programa Oracle Next Education. La API permite a los usuarios autenticarse y gestionar tópicos (crear, listar, detallar, actualizar y eliminar), siguiendo una arquitectura en capas y aplicando buenas prácticas de seguridad.

---

## ✨ Funcionalidades

- **Autenticación de Usuarios:** Sistema de login seguro basado en **JSON Web Tokens (JWT)** para proteger los endpoints.
- **Gestión Completa de Tópicos (CRUD):**
  - **Crear** un nuevo tópico asociado a un usuario y un curso.
  - **Listar** todos los tópicos, con opción de **paginación** y ordenamiento.
  - **Detallar** un tópico específico por su ID.
  - **Actualizar** el título y mensaje de un tópico existente.
  - **Eliminar** un tópico de la base de datos.
- **Validaciones de Negocio:** Se aplican reglas para no permitir tópicos duplicados y para asegurar que los datos de entrada sean correctos y completos.
- **Documentación de la API:** Documentación interactiva generada con **Swagger/Springdoc**.

---

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Security:** Para la autenticación y autorización.
- **Spring Data JPA / Hibernate:** Para la persistencia de datos.
- **MySQL:** Como base de datos relacional.
- **Flyway:** Para la gestión de migraciones de la base de datos de forma versionada.
- **Auth0 JWT:** Para la generación y validación de JSON Web Tokens.
- **Lombok:** Para reducir el código boilerplate en las entidades y DTOs.
- **Maven:** Como gestor de dependencias del proyecto.

---

## 🚀 Cómo Empezar

Sigue estos pasos para ejecutar el proyecto en tu entorno local.

**1. Clonar el Repositorio**
```bash
git clone https://github.com/agustinncba/forohub.git
cd forohub
```

**2. Configurar la Base de Datos**
- Asegúrate de tener MySQL instalado.
- Crea una base de datos con el nombre `forohub_db`.
```sql
CREATE DATABASE forohub_db;
```

**3. Configurar las Variables de Entorno**

- Ve al archivo src/main/resources/application.properties.
- Modifica las siguientes propiedades con tus credenciales de MySQL y un secreto para JWT:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forohub_db
spring.datasource.username=tu_usuario_mysql
spring.datasource.password=tu_contraseña_mysql
api.security.secret=tu_secreto_super_secreto_para_jwt
```

**4. Ejecutar la Aplicación**
```bash
./mvnw spring-boot:run
```

- La API estará disponible en http://localhost:8080.
- La documentación de Swagger estará disponible en http://localhost:8080/swagger-ui.html.