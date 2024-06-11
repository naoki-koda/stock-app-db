package org.stofli.HttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JQuantClient extends Http {

    private ObjectMapper _mapper = new ObjectMapper();
    private ResponseRefreshToken _resRefreshToken;
    private IdToken _idToken;

    public JQuantClient(String mailAdress, String password) {
        try {
            getRefreshToken(mailAdress, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRefreshToken(String mailAdress, String password) throws IOException, InterruptedException, Exception {

        JQuantsUser jQuantUser = new JQuantsUser(mailAdress, password);
        String jQuantUserjson = _mapper.writeValueAsString(jQuantUser);

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.jquants.com/v1/token/auth_user"))
        .POST(HttpRequest.BodyPublishers.ofString(jQuantUserjson))
        .build();

        HttpResponse<String> response =
                _httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == HTTP_STATUS_CODE_OK) {
            String resBody = response.body();
            _resRefreshToken = _mapper.readValue(resBody, ResponseRefreshToken.class);
            getIdToken(_resRefreshToken);
        } else {
            throw new Exception(response.body());
        }

    }

    public void getIdToken(ResponseRefreshToken refreshToken) throws IOException, InterruptedException, Exception {

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.jquants.com/v1/token/auth_refresh?refreshtoken=" + refreshToken))
        .POST(HttpRequest.BodyPublishers.ofString(""))
        .build();

        HttpResponse<String> response =
                _httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if(response.statusCode() == HTTP_STATUS_CODE_OK) {
            _idToken = _mapper.readValue(response.body(), IdToken.class);
        } else {
            throw new Exception(response.body());
        }
    }

    public DailyQuates getDailyQuates(String code, String date) throws IOException, InterruptedException, Exception {

        DailyQuates dailyQuates;
        HttpRequest request = HttpRequest.newBuilder()
        .header("Authorization", "Bearer " + _idToken)
        .uri(URI.create("https://api.jquants.com/v1/prices/daily_quotes?code=" + code + "&date=" + date))
        .build();

        HttpResponse<String> response =
                _httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == HTTP_STATUS_CODE_OK) {
            // System.out.println(response.body());
            dailyQuates = _mapper.readValue(response.body(), DailyQuates.class);
        } else {
            throw new Exception(response.body());
        }
        return dailyQuates;
    }

    public DailyQuates getDailyQuates(String code, String from, String to) throws IOException, InterruptedException, Exception {

        DailyQuates dailyQuates;
        HttpRequest request = HttpRequest.newBuilder()
        .header("Authorization", "Bearer " + _idToken)
        .uri(URI.create("https://api.jquants.com/v1/prices/daily_quotes?code=" + code + "&from=" + from + "&to=" + to))
        .build();

        HttpResponse<String> response =
                _httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == HTTP_STATUS_CODE_OK) {
            // System.out.println(response.body());
            dailyQuates = _mapper.readValue(response.body(), DailyQuates.class);
            System.out.println(response.body());
        } else {
            throw new Exception(response.body());
        }
        return dailyQuates;
    }
}
