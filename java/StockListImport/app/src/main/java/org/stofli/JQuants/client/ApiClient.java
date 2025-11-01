package org.stofli.jquants.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.stofli.domain.model.DailyQuote;
import org.stofli.httpclient.Http;
import org.stofli.jquants.config.JQuantsUser;
import org.stofli.jquants.dto.IdToken;
import org.stofli.jquants.dto.QuoteResponseDto;
import org.stofli.jquants.dto.ResponseRefreshToken;
import org.stofli.jquants.mapper.QuoteMapper;

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

    public Optional<DailyQuote> getDailyQuates(String code, String date) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
        .header("Authorization", "Bearer " + _idToken)
        .uri(URI.create("https://api.jquants.com/v1/prices/daily_quotes?code=" + code + "&date=" + date))
        .build();

        HttpResponse<String> response =
                _httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            QuoteResponseDto dto = objectMapper.readValue(response.body(), QuoteResponseDto.class);
            List<DailyQuote> quotes = QuoteMapper.toDomain(dto.getDailyQuotes());
            // return Optional.of(quotes);
            // return Optional.of(quotes.get(1));
            return QuoteMapper.toDomain(dto.getDailyQuotes()).stream().findFirst();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
