package com.kh.istad.ite.java.mini_project.utils;

import java.io.InputStream;
import java.net.URI;
import java.net.http.*;
import java.util.Properties;

public class ApiClient {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String TOKEN;

    static {
        try (InputStream input =
                     ApiClient.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            TOKEN = prop.getProperty("TMDB_TOKEN");

        } catch (Exception e) {
            throw new RuntimeException("Cannot load config.properties", e);
        }
    }

    public static String get(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + TOKEN)
                    .header("accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {
                System.out.println("ERROR: " + response.body());
                return null;
            }

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}