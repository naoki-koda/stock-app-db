package org.stofli.HttpClient;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Http {
    private final HttpClient httpClient;
    Http() {
        this.httpClient = HttpClient.newHttpClient();
    }
    Http(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

        /**
     * Post‚ÌƒTƒ“ƒvƒ‹
     */
    public void postJson() throws IOException, InterruptedException {
        // HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("http://localhost:8080/sample/post"))
        //         .header("Content-Type", "application/json")
        //         .POST(HttpRequest.BodyPublishers.ofString(
        //                 new ObjectMapper().writeValueAsString(Map.of("key", "value"))))
        //         .build();

        // HttpResponse<String> httpResponse = this.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        // System.out.println(httpResponse.body());
    }
}
