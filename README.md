# 🎵 Backend Playlist - Spring Boot

Backend REST API para la gestión de playlists y canciones.  
Incluye autenticación con JWT, validación, manejo de excepciones y documentación Swagger.

✅ **Framework:** Spring Boot 3.5.4  
✅ **Seguridad:** Spring Security + JWT  
✅ **Persistencia:** JPA + Hibernate (H2 en memoria)
✅ **Documentación:** Swagger / OpenAPI

---

## ✅ Prerrequisitos

Antes de compilar y ejecutar este proyecto necesitas tener instalado:

| Herramienta        | Versión recomendada                         | Descripción                          |
|-------------------|----------------------------------------------|--------------------------------------|
| Java              | 17 (compatible con Spring Boot 3.5.4)       | Para compilar y ejecutar el backend |
| Maven             | 3.8+                                        | Para gestionar dependencias y build |
| IDE (opcional)    | IntelliJ IDEA           | Para editar y ejecutar más cómodo   |
| Git (opcional)    | latest                                      | Para clonar el repositorio          |

## 🚀 Funcionalidad principal

- **Autenticación**
    - Registro de usuario (`/auth/register`)
    - Login (`/auth/login`) → devuelve token JWT
- **Playlists**
    - Crear playlist con canciones
    - Listar todas las playlists
    - Ver detalles de una playlist por nombre
    - Eliminar playlist
- **Canciones**
    - Crear canción dentro de una playlist
- **Manejo global de errores**
    - Validación
    - Usuario inválido
    - Email existente
    - Playlist no encontrada

---

## ⚙️ Tecnologías usadas

- Java 21
- Spring Boot 3.5.4
- Spring Security
- Spring Data JPA
- H2 Database (o MySQL)
- Lombok
- Swagger (springdoc-openapi)

---

## Como usarse

### 1. Clonar el repositorio
```bash
git clone https://github.com/ANCB03/quipux-backend-playlist.git

cd quipux-backend-playlist 
```
### 2. Editar el application.yml
Puedes cambiar el secret para el jwt y la duración del token.

### 3. Ejecuta la aplicación

- **Nota**: Si usas IntelliJ IDEA dale en el boton de play y asegurate que este apuntando a "**MainApplication**"


### Link de la colección en postman
https://www.postman.com/codersforce/workspace/public/collection/24144423-0b34b00c-94f4-4e01-9da5-b5332c6b071f?action=share&creator=24144423

## Nota
Se crean dos usuarios al inciarse la aplicación con las siguientes credenciales:

- **Usuario 1**
    - email: admin@gmail.com
    - password: 1234
- **Usuario 2**
  - email: user@gmail.com
  - password: 1234