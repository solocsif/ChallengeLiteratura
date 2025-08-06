# 📚 Literatura Challenge

**Literatura Challenge** es una aplicación Java desarrollada con Spring Boot que permite importar, analizar y visualizar estadísticas de libros en distintos idiomas. El proyecto está diseñado para explorar conceptos de arquitectura modular, integración de APIs externas, y visualización de datos literarios.

---

## 🚀 Descripción del Proyecto

Este proyecto nace como un reto técnico y creativo para construir una aplicación que:

- Importe libros desde una fuente externa mediante una API.
- Almacene y procese los datos en una base de datos PostgreSQL.
- Genere estadísticas por idioma, como número de libros, autores, y géneros predominantes.
- Visualice la información de forma clara y accesible a través de una interfaz web con Thymeleaf.

Además, el proyecto sirve como base para experimentar con microservicios, tolerancia a fallos, y buenas prácticas de desarrollo backend.

---

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Jackson** (para mapeo JSON)
- **IntelliJ IDEA** (entorno de desarrollo)
- **Git** (control de versiones)

---

## 📦 Módulos Principales

| Módulo                  | Descripción                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| `BookImportService`    | Se encarga de consumir la API externa y transformar los datos en entidades. |
| `EstadisticasService`  | Procesa los datos y genera estadísticas por idioma.                         |
| `MyHttpClient`         | Cliente HTTP personalizado para manejar las solicitudes externas.           |
| `LiteraturaController` | Controlador web que gestiona las vistas y endpoints.                        |

---

## 📊 Funcionalidades Clave

- ✅ Importación de libros desde una API externa.
- ✅ Persistencia de datos en PostgreSQL.
- ✅ Estadísticas por idioma (cantidad de libros, autores, etc.).
- ✅ Interfaz web con visualización clara y amigable.
- ✅ Arquitectura modular y escalable.

---

## 🧪 Cómo Ejecutar el Proyecto

1. Clona el repositorio:
   git clone https://github.com/solocsif/ChallengeLiteratura.git
2. Configura la base de datos PostgreSQL
3. Ejecuta la clase LiteraturaChallengeApplication

📄 Licencia
Este proyecto está bajo la licencia MIT. Puedes usarlo, modificarlo y compartirlo libremente.

