
# 📚 Literalura

Literalura es una aplicación de consola desarrollada en Java con Spring Boot que permite buscar libros utilizando la API [Gutendex](https://gutendex.com/), guardar los resultados en una base de datos con PostgreSQL y consultar listas de libros, autores e idiomas.

## ✨ ONE G8

Este proyecto fue creado para completar el challenge ONE G8 Backend - Challenge Literalura - Spring Boot.


## 🚀 Características

- Búsqueda de libros por título usando la API de Gutendex.
- Almacenamiento del libro seleccionado (la primera coincidencia) en una base de datos local.
- Consulta de:
  - Todos los libros registrados.
  - Autores disponibles.
  - Autores vivos en un determinado año.
  - Libros filtrados por idioma.

## 🛠️ Requisitos

- Java 17 o superior.
- Spring Boot 3.x
- Maven como gestor de dependencias.
- Acceso a internet para consultar la API de Gutendex.

## ⚙️ Ejecución

1. Clona el repositorio:
```
   git clone https://github.com/YoshuaPariona/challenge-literalura.git
   cd literalura
```

2. Asegúrate de tener Java 17+ y un IDE como IntelliJ.

3. Ejecuta la clase `LiteraluraApplication.java` desde el IDE o mediante tu herramienta de compilación.

4. Usa el menú de consola para explorar las opciones disponibles.

## 🧱 Estructura del proyecto
```
src/
├── main/
│   ├── java/
│   │   └── com/tuusuario/literalura/
│   │       ├── models/                       # Entidades y records: Book, Author, Language
│   │       ├── repositories/                 # Interfaces de JpaRepository
│   │       ├── services/                     # Lógica de negocio (servicios)
│   │       ├── main/                         # Menú
│   │       └── LiteraluraApplication.java    # Punto de entrada
│   └── resources/
│       └── application.properties            # Propiedades
├── pom.xml                                   # Dependencias
└── README.md
```
## 📚 Sobre Gutendex

Gutendex es una API pública gratuita que proporciona acceso a los metadatos de los libros disponibles en el [Proyecto Gutenberg](https://www.gutenberg.org/), una biblioteca digital de dominio público.

## 📄 Licencia

Este proyecto está licenciado bajo la **Licencia MIT**. Consulta el archivo [LICENSE](LICENSE) para más detalles.
