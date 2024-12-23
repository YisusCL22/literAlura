# 📚 LiterAlura: Catálogo Interactivo de Libros

Bienvenido a **LiterAlura**, un emocionante proyecto de programación diseñado para desafiar tus habilidades en Java. Aprende a realizar solicitudes a una API de libros, manipular datos JSON, almacenarlos en una base de datos y proporcionar una experiencia interactiva para el usuario a través de la consola.

---

## 🎯 Objetivo del Proyecto

El objetivo principal es desarrollar un **Catálogo de Libros** que permita:
- Buscar libros a través de una API específica.
- Manipular y analizar datos en formato JSON.
- Guardar y consultar información en una base de datos.
- Ofrecer al usuario al menos **5 opciones de interacción textual** en consola.

---

## 🛠️ Tecnologías Utilizadas

El proyecto se desarrolla utilizando las siguientes herramientas y tecnologías:

- **Lenguaje de Programación**: Java
- **Gestión de Datos**: JSON
- **Base de Datos**: SQLite (o H2 para una opción embebida)
- **Herramientas de Control de Versiones**: Git y GitHub
- **Metodología Ágil**: Trello para la gestión de tareas

---

## 🚀 Funcionalidades Principales

1. **Búsqueda de Libros**:
   - Realiza solicitudes a una API para buscar libros por título o autor.
2. **Análisis de Datos**:
   - Manipula y analiza respuestas en formato JSON.
3. **Gestión de Base de Datos**:
   - Inserta, actualiza y consulta libros y autores desde una base de datos local.
4. **Interacción Textual**:
   - Ofrece un menú interactivo con opciones como:
     - Mostrar todos los libros almacenados.
     - Filtrar libros por palabra clave.
     - Mostrar detalles de un libro específico.
     - Agregar libros favoritos a una lista personalizada.
5. **Documentación y Demostración**:
   - Incluye un archivo `README.md` detallado con capturas de pantalla y un video del sistema en funcionamiento.

---

## 🗂️ Estructura del Proyecto

```plaintext
literAlura/
├── src/
│   ├── api/               # Clases para consumir la API
│   ├── models/            # Clases para representar libros y autores
│   ├── database/          # Gestión de base de datos
│   ├── ui/                # Interacción con el usuario
├── README.md              # Documentación del proyecto
├── .gitignore             # Archivos a excluir del control de versiones
└── Trello Board           # Enlace al tablero para la gestión de tareas
