package com.challenge.literalura.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {

    public String getJson(String searchInput) {
        String URL_BASE = "https://gutendex.com/books/?search=";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE + searchInput))
                .build();
        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

    public String getJsonFromFile() {
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("books.json")) {
            if (inputStream == null) throw new FileNotFoundException("Archivo no encontrado");
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("No se encontró el archivo.");
        }
    }
}
