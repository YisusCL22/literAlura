package com.jesusalvarez.literAlura.client;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class BookClient {

    private static final String BASE_URL = "https://gutendex.com"; // Ajusta con la URL real de tu API

    private final HttpClient httpClient = HttpClient.newHttpClient();

    /**
     * Recupera una lista de libros disponibles en el catálogo.
     * 
     * @param queryParams Mapa de parámetros de consulta para filtrar los resultados.
     *                    Ejemplos de parámetros:
     *                    - author_year_start y author_year_end: Filtra libros cuyos autores estuvieron vivos dentro de un rango de años especificado.
     *                      Ejemplo: /books?author_year_start=1800&author_year_end=1899
     *                    - copyright: Filtra libros según su estado de derechos de autor.
     *                      Valores: true (con derechos de autor), false (dominio público en EE. UU.), null (información no disponible).
     *                      Ejemplo: /books?copyright=false
     *                    - ids: Recupera libros específicos por sus IDs de Project Gutenberg.
     *                      Ejemplo: /books?ids=11,12,13
     *                    - languages: Filtra libros por códigos de idioma de dos caracteres.
     *                      Ejemplo: /books?languages=en,fr
     *                    - mime_type: Filtra libros por tipo MIME.
     *                      Ejemplo: /books?mime_type=text/html
     *                    - search: Busca libros por palabras clave en títulos y nombres de autores.
     *                      Ejemplo: /books?search=dickens great
     *                    - sort: Ordena los libros según criterios específicos.
     *                      Valores: ascending, descending, popular (por defecto).
     *                      Ejemplo: /books?sort=ascending
     *                    - topic: Filtra libros por temas o estanterías específicas.
     *                      Ejemplo: /books?topic=children
     * @return Respuesta en formato JSON como cadena de texto.
     * @throws IOException
     * @throws InterruptedException
     */
    public String getBooks(Map<String, String> queryParams) throws IOException, InterruptedException {
        // Construye la URL base para el endpoint /books
        StringBuilder url = new StringBuilder(BASE_URL + "/books");
        
        // Si hay parámetros de consulta, los añade a la URL
        if (!queryParams.isEmpty()) {
            url.append("?");
            queryParams.forEach((key, value) -> {
                try {
                    url.append(URLEncoder.encode(key, StandardCharsets.UTF_8.toString()))
                       .append("=")
                       .append(URLEncoder.encode(value, StandardCharsets.UTF_8.toString()))
                       .append("&");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            url.deleteCharAt(url.length() - 1); // Elimina el último '&' sobrante
        }

        // Imprime la URL de la solicitud en la consola
        System.out.println("Enviando solicitud a: " + url.toString());

        // Crea una solicitud HTTP GET con la URL construida
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url.toString()))
                .GET()
                .build();

        // Envía la solicitud y obtiene la respuesta
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        // Retorna el cuerpo de la respuesta como una cadena de texto
        return response.body();
    }

    // Cambiado para que soporte el endpoint /books/<id>.
    public String getBookById(String id) throws IOException, InterruptedException {
        String url = BASE_URL + "/books/" + id;

        // Imprime la URL de la solicitud en la consola
        System.out.println("Enviando solicitud a: " + url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}