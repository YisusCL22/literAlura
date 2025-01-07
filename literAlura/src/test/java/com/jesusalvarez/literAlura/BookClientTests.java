package com.jesusalvarez.literAlura;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesusalvarez.literAlura.client.BookClient;
import com.jesusalvarez.literAlura.model.ApiResponse;
import com.jesusalvarez.literAlura.model.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.Map;

class BookClientTests {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetBooks() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of());
        System.out.println("Respuesta JSON (sin parámetros): " + response);
        assertNotNull(response, "La respuesta no debe ser nula");

        // Analizar la respuesta JSON
        ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
        for (Book book : apiResponse.getResults()) {
            System.out.println("Libro: " + book.getTitle());
        }
    }

    @Test
    void testGetBooksByAuthorYearRange() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("author_year_start", "1990", "author_year_end", "2000"));
        System.out.println("Respuesta JSON (author_year_start=1990, author_year_end=2000): " + response);
        assertNotNull(response, "La respuesta no debe ser nula");

        // Analizar la respuesta JSON
        ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
        for (Book book : apiResponse.getResults()) {
            System.out.println("Libro: " + book.getTitle());
        }
    }

    @Test
    void testGetBooksByCopyright() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("copyright", "false"));
        System.out.println("Respuesta JSON (copyright=false): " + response);
        assertNotNull(response, "La respuesta no debe ser nula");

        // Analizar la respuesta JSON
        ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
        for (Book book : apiResponse.getResults()) {
            System.out.println("Libro: " + book.getTitle());
        }
    }

    @Test
    void testGetBooksByIds() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("ids", "11,12,13"));
        System.out.println("Respuesta JSON (ids=11,12,13): " + response);
        assertNotNull(response, "La respuesta no debe ser nula");

        // Analizar la respuesta JSON
        ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
        for (Book book : apiResponse.getResults()) {
            System.out.println("Libro: " + book.getTitle());
        }
    }

    @Test
    void testGetBooksByLanguages() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("languages", "en,fr"));
        System.out.println("Respuesta JSON (languages=en,fr): " + response);
        assertNotNull(response, "La respuesta no debe ser nula");

        // Analizar la respuesta JSON
        ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
        for (Book book : apiResponse.getResults()) {
            System.out.println("Libro: " + book.getTitle());
        }
    }

    @Test
    void testGetBooksByMimeType() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("mime_type", "text/html"));
        System.out.println("Respuesta JSON (mime_type=text/html): " + response);
        assertNotNull(response, "La respuesta no debe ser nula");

        // Analizar la respuesta JSON
        ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
        for (Book book : apiResponse.getResults()) {
            System.out.println("Libro: " + book.getTitle());
        }
    }

    @Test
    void testGetBooksBySearch() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("search", "dickens great"));
        System.out.println("Respuesta JSON (search=dickens great): " + response);
        assertNotNull(response, "La respuesta no debe ser nula");

        // Analizar la respuesta JSON
        ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
        for (Book book : apiResponse.getResults()) {
            System.out.println("Libro: " + book.getTitle());
        }
    }

    @Test
    void testGetBooksBySort() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("sort", "ascending"));
        System.out.println("Respuesta JSON (sort=ascending): " + response);
        assertNotNull(response, "La respuesta no debe ser nula");

        // Analizar la respuesta JSON
        ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
        for (Book book : apiResponse.getResults()) {
            System.out.println("Libro: " + book.getTitle());
        }
    }

    @Test
    void testGetBooksByTopic() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("topic", "children"));
        System.out.println("Respuesta JSON (topic=children): " + response);
        assertNotNull(response, "La respuesta no debe ser nula");

        // Analizar la respuesta JSON
        ApiResponse apiResponse = objectMapper.readValue(response, ApiResponse.class);
        for (Book book : apiResponse.getResults()) {
            System.out.println("Libro: " + book.getTitle());
        }
    }

    @Test
    void testGetBookById() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBookById("12345");
        System.out.println("Respuesta JSON (id=12345): " + response);
        assertNotNull(response, "La respuesta no debe ser nula");

        // Analizar la respuesta JSON
        Book book = objectMapper.readValue(response, Book.class);
        System.out.println("Libro: " + book.getTitle());
    }
}