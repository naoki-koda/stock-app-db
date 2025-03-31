package org.stofli.JQuants.client;

import java.io.IOException;
import java.net.HttpRetryException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.stofli.HttpClient.Http;
import org.stofli.JQuants.config.JQuantsUser;
import org.stofli.JQuants.dto.DailyQuote;
import org.stofli.JQuants.dto.IdToken;
import org.stofli.JQuants.dto.QuoteResponse;
import org.stofli.JQuants.dto.ResponseRefreshToken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ApiClient extends Http {

    private ObjectMapper _mapper = new ObjectMapper();
    private ResponseRefreshToken _resRefreshToken;
    private IdToken _idToken;

    public ApiClient(String mailAdress, String password) {
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

    public void getDailyQuates(String code, String date) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
        .header("Authorization", "Bearer " + _idToken)
        .uri(URI.create("https://api.jquants.com/v1/prices/daily_quotes?code=" + code + "&date=" + date))
        .build();

        HttpResponse<String> response =
                _httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            QuoteResponse quotesRespons = objectMapper.readValue(response.body(), QuoteResponse.class);
            List<DailyQuote> list= quotesRespons.getDailyQuotes();
            for (DailyQuote quote : list) {
                System.out.println("Code: " + quote.date() + ", Close Price: " + quote);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
