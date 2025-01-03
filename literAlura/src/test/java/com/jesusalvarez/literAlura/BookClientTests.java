package com.jesusalvarez.literAlura;

import com.jesusalvarez.literAlura.client.BookClient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.Map;

class BookClientTests {

    @Test
    void testGetBooksByAuthorYearRange() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("author_year_start", "1800", "author_year_end", "1899"));
        assertNotNull(response, "La respuesta no debe ser nula");
    }

    @Test
    void testGetBooksByCopyright() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("copyright", "false"));
        assertNotNull(response, "La respuesta no debe ser nula");
    }

    @Test
    void testGetBooksByIds() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("ids", "11,12,13"));
        assertNotNull(response, "La respuesta no debe ser nula");
    }

    @Test
    void testGetBooksByLanguages() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("languages", "en,fr"));
        assertNotNull(response, "La respuesta no debe ser nula");
    }

    @Test
    void testGetBooksByMimeType() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("mime_type", "text/html"));
        assertNotNull(response, "La respuesta no debe ser nula");
    }

    @Test
    void testGetBooksBySearch() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("search", "dickens great"));
        assertNotNull(response, "La respuesta no debe ser nula");
    }

    @Test
    void testGetBooksBySort() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("sort", "ascending"));
        assertNotNull(response, "La respuesta no debe ser nula");
    }

    @Test
    void testGetBooksByTopic() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBooks(Map.of("topic", "children"));
        assertNotNull(response, "La respuesta no debe ser nula");
    }

    @Test
    void testGetBookById() throws IOException, InterruptedException {
        BookClient client = new BookClient();
        String response = client.getBookById("12345");
        assertNotNull(response, "La respuesta no debe ser nula");
    }
}