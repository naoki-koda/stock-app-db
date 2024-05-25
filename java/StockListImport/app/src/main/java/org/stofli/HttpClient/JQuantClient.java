package org.stofli.HttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JQuantClient {

    private final HttpClient httpClient;
    private ResponseRefreshToken resRefreshToken;

    public JQuantClient() {
        this.httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .build();
    }
    public JQuantClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void getRefreshToken(String mailAdress, String password) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();

        JQuantsUser jQuantUser = new JQuantsUser(mailAdress, password);
        String jQuantUserjson = mapper.writeValueAsString(jQuantUser);

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.jquants.com/v1/token/auth_user"))
        .POST(HttpRequest.BodyPublishers.ofString(jQuantUserjson))
        .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        String resBody = response.body();
        resRefreshToken = mapper.readValue(resBody, ResponseRefreshToken.class);
        getIdToken(resRefreshToken);
    }

    public void getIdToken(ResponseRefreshToken refreshToken) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();

        RequestRefreshToken reqRfreshToken = new RequestRefreshToken(refreshToken.toString());
        String refreshTokenJson = mapper.writeValueAsString(reqRfreshToken);
        System.out.println(refreshTokenJson);

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.jquants.com/v1/token/auth_refresh"))
        .POST(HttpRequest.BodyPublishers.ofString(refreshTokenJson))
        .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        String resBody = response.body();
        System.out.println(resBody);
        // IdToken idToken = mapper.readValue(resBody, IdToken.class);
        // System.out.println(idToken);
    }
}
