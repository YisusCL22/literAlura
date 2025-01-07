package com.jesusalvarez.literAlura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesusalvarez.literAlura.client.BookClient;
import com.jesusalvarez.literAlura.model.ApiResponse;
import com.jesusalvarez.literAlura.model.Author;
import com.jesusalvarez.literAlura.model.Book;
import com.jesusalvarez.literAlura.model.Translator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    private static final int LIBROS_POR_PAGINA = 5;
    private static final boolean DEBUG = false;

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        BookClient client = new BookClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    obtenerLibrosSinParametros(client, objectMapper, scanner);
                    break;
                case "2":
                    obtenerLibrosPorRangoAnios(client, objectMapper, scanner);
                    break;
                case "3":
                    obtenerLibrosPorCopyright(client, objectMapper, scanner);
                    break;
                case "4":
                    obtenerLibrosPorIds(client, objectMapper, scanner);
                    break;
                case "5":
                    obtenerLibrosPorIdiomas(client, objectMapper, scanner);
                    break;
                case "6":
                    obtenerLibrosPorMimeType(client, objectMapper, scanner);
                    break;
                case "7":
                    obtenerLibrosPorBusqueda(client, objectMapper, scanner);
                    break;
                case "8":
                    obtenerLibrosPorOrden(client, objectMapper, scanner);
                    break;
                case "9":
                    obtenerLibrosPorTema(client, objectMapper, scanner);
                    break;
                case "10":
                    obtenerLibroPorId(client, objectMapper, scanner);
                    break;
                case "11":
                    System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente nuevamente.");
            }

            System.out.println(); // Línea en blanco para mejor legibilidad
        }

        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("===== Menú de Opciones =====");
        System.out.println("1. Obtener libros (sin parámetros)");
        System.out.println("2. Obtener libros por rango de años de autor");
        System.out.println("3. Obtener libros por derechos de autor");
        System.out.println("4. Obtener libros por IDs");
        System.out.println("5. Obtener libros por idiomas");
        System.out.println("6. Obtener libros por tipo MIME");
        System.out.println("7. Obtener libros por búsqueda");
        System.out.println("8. Obtener libros por orden");
        System.out.println("9. Obtener libros por tema");
        System.out.println("10. Obtener libro por ID");
        System.out.println("11. Salir");
        System.out.println("============================");
    }

    private void obtenerLibrosSinParametros(BookClient client, ObjectMapper objectMapper, Scanner scanner) {
        try {
            String response = client.getBooks(Map.of());

            if (DEBUG) {
                System.out.println("Respuesta JSON (sin parámetros): " + response);
            }
            // Analizar la respuesta JSON
            ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
            List<Book> libros = apiResponse.getResults();

            mostrarLibrosConPaginacion(libros, scanner);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener libros: " + e.getMessage());
        }
    }

    private void obtenerLibrosPorRangoAnios(BookClient client, ObjectMapper objectMapper, Scanner scanner) {
        try {
            System.out.print("Ingrese el año de inicio del autor: ");
            String inicio = scanner.nextLine().trim();
            System.out.print("Ingrese el año de fin del autor: ");
            String fin = scanner.nextLine().trim();

            String response = client.getBooks(Map.of("author_year_start", inicio, "author_year_end", fin));
            
            if(DEBUG){
                System.out.println("Respuesta JSON (author_year_start=" + inicio + ", author_year_end=" + fin + "): " + response);
            }
 
            // Analizar la respuesta JSON
            ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
            List<Book> libros = apiResponse.getResults();

            mostrarLibrosConPaginacion(libros, scanner);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener libros por rango de años: " + e.getMessage());
        }
    }

    private void obtenerLibrosPorCopyright(BookClient client, ObjectMapper objectMapper, Scanner scanner) {
        try {
            System.out.print("Ingrese el estado de derechos de autor (true/false): ");
            String copyright = scanner.nextLine().trim();

            String response = client.getBooks(Map.of("copyright", copyright));
            
            if(DEBUG){
                System.out.println("Respuesta JSON (copyright=" + copyright + "): " + response);
            }

            // Analizar la respuesta JSON
            ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
            List<Book> libros = apiResponse.getResults();

            mostrarLibrosConPaginacion(libros, scanner);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener libros por derechos de autor: " + e.getMessage());
        }
    }

    private void obtenerLibrosPorIds(BookClient client, ObjectMapper objectMapper, Scanner scanner) {
        try {
            System.out.print("Ingrese los IDs de los libros separados por comas (ejemplo: 11,12,13): ");
            String ids = scanner.nextLine().trim();

            String response = client.getBooks(Map.of("ids", ids));

            if(DEBUG){
                System.out.println("Respuesta JSON (ids=" + ids + "): " + response);
            }

            // Analizar la respuesta JSON
            ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
            List<Book> libros = apiResponse.getResults();

            mostrarLibrosConPaginacion(libros, scanner);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener libros por IDs: " + e.getMessage());
        }
    }

    private void obtenerLibrosPorIdiomas(BookClient client, ObjectMapper objectMapper, Scanner scanner) {
        try {
            System.out.print("Ingrese los códigos de idioma separados por comas (ejemplo: en,fr): ");
            String languages = scanner.nextLine().trim();

            String response = client.getBooks(Map.of("languages", languages));
            
            if(DEBUG){
                System.out.println("Respuesta JSON (languages=" + languages + "): " + response);
            }

            // Analizar la respuesta JSON
            ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
            List<Book> libros = apiResponse.getResults();

            mostrarLibrosConPaginacion(libros, scanner);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener libros por idiomas: " + e.getMessage());
        }
    }

    private void obtenerLibrosPorMimeType(BookClient client, ObjectMapper objectMapper, Scanner scanner) {
        try {
            System.out.print("Ingrese el tipo MIME (ejemplo: text/html): ");
            String mimeType = scanner.nextLine().trim();

            String response = client.getBooks(Map.of("mime_type", mimeType));
            
            if(DEBUG){
                System.out.println("Respuesta JSON (mime_type=" + mimeType + "): " + response);
            }
            

            // Analizar la respuesta JSON
            ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
            List<Book> libros = apiResponse.getResults();

            mostrarLibrosConPaginacion(libros, scanner);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener libros por tipo MIME: " + e.getMessage());
        }
    }

    private void obtenerLibrosPorBusqueda(BookClient client, ObjectMapper objectMapper, Scanner scanner) {
        try {
            System.out.print("Ingrese los términos de búsqueda (ejemplo: dickens great): ");
            String search = scanner.nextLine().trim();

            String response = client.getBooks(Map.of("search", search));
            
            if(DEBUG){
                System.out.println("Respuesta JSON (search=" + search + "): " + response);
            }

            // Analizar la respuesta JSON
            ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
            List<Book> libros = apiResponse.getResults();

            mostrarLibrosConPaginacion(libros, scanner);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener libros por búsqueda: " + e.getMessage());
        }
    }

    private void obtenerLibrosPorOrden(BookClient client, ObjectMapper objectMapper, Scanner scanner) {
        try {
            System.out.print("Ingrese el criterio de orden (ascending/descending/popular): ");
            String sort = scanner.nextLine().trim();

            String response = client.getBooks(Map.of("sort", sort));
            
            if(DEBUG){
                System.out.println("Respuesta JSON (sort=" + sort + "): " + response);
            }

            // Analizar la respuesta JSON
            ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
            List<Book> libros = apiResponse.getResults();

            mostrarLibrosConPaginacion(libros, scanner);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener libros por orden: " + e.getMessage());
        }
    }

    private void obtenerLibrosPorTema(BookClient client, ObjectMapper objectMapper, Scanner scanner) {
        try {
            System.out.print("Ingrese el tema (ejemplo: children): ");
            String topic = scanner.nextLine().trim();

            String response = client.getBooks(Map.of("topic", topic));
            
            if(DEBUG){
                System.out.println("Respuesta JSON (topic=" + topic + "): " + response);
            }

            // Analizar la respuesta JSON
            ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
            List<Book> libros = apiResponse.getResults();

            mostrarLibrosConPaginacion(libros, scanner);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener libros por tema: " + e.getMessage());
        }
    }

    private void obtenerLibroPorId(BookClient client, ObjectMapper objectMapper, Scanner scanner) {
        try {
            System.out.print("Ingrese el ID del libro: ");
            String id = scanner.nextLine().trim();

            String response = client.getBookById(id);

            if(DEBUG){
                System.out.println("Respuesta JSON (id=" + id + "): " + response);
            }

            // Analizar la respuesta JSON
            Book book = objectMapper.readValue(response, Book.class);
            mostrarLibro(book);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener libro por ID: " + e.getMessage());
        }
    }

    private void mostrarLibrosConPaginacion(List<Book> libros, Scanner scanner) {
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros.");
            return;
        }

        int totalLibros = libros.size();
        int totalPaginas = (int) Math.ceil((double) totalLibros / LIBROS_POR_PAGINA);
        int paginaActual = 1;

        while (true) {
            int inicio = (paginaActual - 1) * LIBROS_POR_PAGINA;
            int fin = Math.min(inicio + LIBROS_POR_PAGINA, totalLibros);
            List<Book> pagina = libros.subList(inicio, fin);

            System.out.println("\n===== Página " + paginaActual + " de " + totalPaginas + " =====");
            for (Book book : pagina) {
                mostrarLibro(book);
            }

            System.out.println("===== Opciones de Navegación =====");
            if (paginaActual > 1) {
                System.out.println("P. Página anterior");
            }
            if (paginaActual < totalPaginas) {
                System.out.println("N. Página siguiente");
            }
            System.out.println("S. Salir de la visualización");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim().toUpperCase();

            if (opcion.equals("N") && paginaActual < totalPaginas) {
                paginaActual++;
            } else if (opcion.equals("P") && paginaActual > 1) {
                paginaActual--;
            } else if (opcion.equals("S")) {
                break;
            } else {
                System.out.println("Opción inválida o no disponible en esta página. Por favor, intente nuevamente.");
            }
        }
    }

    private void mostrarLibro(Book book) {
        System.out.println("--------------------------------------------------");
        System.out.println("ID: " + book.getId());
        System.out.println("Título: " + book.getTitle());

        System.out.println("Autores:");
        for (Author author : book.getAuthors()) {
            System.out.println("  - " + author.getName() + 
                               (author.getBirth_year() != null ? " (" + author.getBirth_year() + " - " + 
                               (author.getDeath_year() != null ? author.getDeath_year() : "Presente") + ")" : ""));
        }

        if (!book.getTranslators().isEmpty()) {
            System.out.println("Traductores:");
            for (Translator translator : book.getTranslators()) {
                System.out.println("  - " + translator.getName() + 
                                   (translator.getBirth_year() != null ? " (" + translator.getBirth_year() + " - " + 
                                   (translator.getDeath_year() != null ? translator.getDeath_year() : "Presente") + ")" : ""));
            }
        }

        System.out.println("Temas:");
        for (String subject : book.getSubjects()) {
            System.out.println("  - " + subject);
        }

        System.out.println("Estanterías:");
        for (String bookshelf : book.getBookshelves()) {
            System.out.println("  - " + bookshelf);
        }

        System.out.println("Idiomas: " + String.join(", ", book.getLanguages()));
        System.out.println("Derechos de autor: " + (book.getCopyright() ? "Sí" : "No"));
        System.out.println("Tipo de medio: " + book.getMedia_type());

        System.out.println("Formatos:");
        for (Map.Entry<String, String> entry : book.getFormats().entrySet()) {
            System.out.println("  - " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Cantidad de descargas: " + book.getDownload_count());
        System.out.println("--------------------------------------------------\n");
    }
}