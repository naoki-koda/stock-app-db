package org.stofli.adopter.webapi.jquants;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

import org.stofli.adopter.webapi.auth.core.IdToken;
import org.stofli.adopter.webapi.auth.core.TokenProvider;
import org.stofli.adopter.webapi.common.Http;
import org.stofli.adopter.webapi.jquants.dto.QuoteResponseDto;
import org.stofli.adopter.webapi.jquants.mapper.QuoteMapper;
import org.stofli.domain.model.DailyQuote;
import org.stofli.domain.port.MarketDataClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JQuantsClient extends Http implements MarketDataClient {

  private TokenProvider jquantsTokenProvider;
  private final ObjectMapper mapper;

    public JQuantsClient(TokenProvider tokenProvider) {
        this(tokenProvider, new ObjectMapper());
    }

    public JQuantsClient(TokenProvider tokenProvider, ObjectMapper mapper) {
        this.jquantsTokenProvider = tokenProvider;
        this.mapper = mapper;
    }

  @Override
  public List<DailyQuote> fetchDailyQuotes(String code, LocalDate date) throws IOException, InterruptedException {
    IdToken idToken = jquantsTokenProvider.obtainToken();
    HttpRequest request = HttpRequest.newBuilder()
                                      .header("Authorization", "Bearer " + idToken.toString())
                                      .uri(URI.create("https://api.jquants.com/v1/prices/daily_quotes?code=" + code + "&date=" + date))
                                      .build();

    HttpResponse<String> response = _httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    if(response.statusCode() == HTTP_STATUS_CODE_OK ) {
      try {
        QuoteResponseDto dto = mapper.readValue(response.body(), QuoteResponseDto.class);
        return QuoteMapper.toDomain(dto.getDailyQuotes());
      } catch(JsonProcessingException e) {
        throw new RuntimeException("JSONのパースに失敗しました: " + e.getMessage(), e);
      }
    } else {
      throw new IOException("HTTPエラー: status=" + response.statusCode() + ", body=" + response.body());
    }
  }
}
