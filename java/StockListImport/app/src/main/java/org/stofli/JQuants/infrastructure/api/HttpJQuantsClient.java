package org.stofli.jquants.infrastructure.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.stofli.domain.model.DailyQuote;
import org.stofli.httpclient.Http;
import org.stofli.jquants.domain.port.MarketDataClient;
import org.stofli.jquants.dto.QuoteResponseDto;
import org.stofli.jquants.infrastructure.api.auth.TokenProvider;
import org.stofli.jquants.mapper.QuoteMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * HTTP adapter for the J-Quants market data API.
 */
public class HttpJQuantsClient extends Http implements MarketDataClient {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String DAILY_QUOTES_ENDPOINT = "https://api.jquants.com/v1/prices/daily_quotes?code=%s&date=%s";

    private final TokenProvider tokenProvider;
    private final ObjectMapper mapper;

    public HttpJQuantsClient(TokenProvider tokenProvider) {
        this(tokenProvider, new ObjectMapper());
    }

    public HttpJQuantsClient(TokenProvider tokenProvider, ObjectMapper mapper) {
        this.tokenProvider = tokenProvider;
        this.mapper = mapper;
    }

    @Override
    public List<DailyQuote> fetchDailyQuotes(String code, LocalDate date) throws IOException, InterruptedException {
        String formattedDate = DATE_FORMATTER.format(date);
        String token = tokenProvider.obtainToken();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DAILY_QUOTES_ENDPOINT.formatted(code, formattedDate)))
                .header("Authorization", "Bearer " + token)
                .build();

        HttpResponse<String> response = _httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != HTTP_STATUS_CODE_OK) {
            throw new IllegalStateException("Failed to fetch quotes for code=%s date=%s: %s"
                    .formatted(code, formattedDate, response.body()));
        }

        QuoteResponseDto dto = mapper.readValue(response.body(), QuoteResponseDto.class);
        List<DailyQuote> quotes = QuoteMapper.toDomain(dto.getDailyQuotes());
        return quotes;
    }
}
