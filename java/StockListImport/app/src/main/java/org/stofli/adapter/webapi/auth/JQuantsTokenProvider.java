package org.stofli.adapter.webapi.auth;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;

import org.stofli.adapter.webapi.auth.core.CredentialProvider;
import org.stofli.adapter.webapi.auth.core.IdToken;
import org.stofli.adapter.webapi.auth.core.TokenProvider;
import org.stofli.adapter.webapi.common.Http;
import org.stofli.adapter.webapi.jquants.dto.ResponseRefreshToken;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JQuantsTokenProvider extends Http implements TokenProvider {
    private final CredentialProvider<JQuantsCredentials> credentialProvider;
    private final ObjectMapper mapper;
    private static final String AUTH_URL_BASE = "https://api.jquants.com/v1/token/";
    private static final String AUTH_USER_PATH = "auth_user";
    private static final Duration ID_TOKEN_VALIDITY = Duration.ofHours(24);
    private static final Duration REFRESH_TOKEN_VALIDITY = Duration.ofDays(7);
    private TokenCache cache;

    public JQuantsTokenProvider(CredentialProvider<JQuantsCredentials> credentialProvider) {
        this(credentialProvider, new ObjectMapper());
    }

    public JQuantsTokenProvider(CredentialProvider<JQuantsCredentials> credentialProvider, ObjectMapper mapper) {
        this.credentialProvider = credentialProvider;
        this.mapper = mapper;
    }
    @Override
    public IdToken obtainToken() throws IOException, InterruptedException {
        if (cache != null && cache.isIdTokenValid()) {
            return cache.idToken;
        }
        if (cache != null && cache.isRefreshTokenValid()) {
            return cache.idToken;
        }
        System.out.println("Authenticating new user session...");
        authenticate();
        return cache.idToken;
    }
    // private boolean isExpired() {
    //     return expiresAt != null && Instant.now().isAfter(expiresAt.minusSeconds(30));
    // }
    private void authenticate() throws IOException, InterruptedException {
        authenticateUser();
        requestIdToken();
        return;
    }

    private void authenticateUser() throws IOException, InterruptedException {
        JQuantsCredentials credentials = credentialProvider.loadCredentials();
        String payload = mapper.writeValueAsString(credentials);

        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .uri(URI.create(AUTH_URL_BASE + AUTH_USER_PATH))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        HttpResponse<String> response = _httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == HTTP_STATUS_CODE_OK) {
            String resBody =response.body();
            mapper.readValue(resBody, ResponseRefreshToken.class);
            ResponseRefreshToken refresh = mapper.readValue(resBody, ResponseRefreshToken.class);
            cache = new TokenCache();
            cache.refreshToken = refresh.value();
            cache.refreshTokenIssuedAt = LocalDateTime.now();
        } else {
            throw new IllegalStateException("Failed to authenticate user: " + response.body());
        }
    }
    private void requestIdToken() throws IOException, InterruptedException {
        StringBuilder idTokenUrl = new StringBuilder(AUTH_URL_BASE).append("auth_refresh?refreshtoken=").append(cache.refreshToken);
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .uri(URI.create(idTokenUrl.toString()))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = _httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == HTTP_STATUS_CODE_OK) {
            IdToken newIdToken = mapper.readValue(response.body(), IdToken.class);
            cache.idToken = newIdToken;
            cache.idTokenIssuedAt = LocalDateTime.now();
        } else {
            throw new IllegalStateException("Failed to obtain ID token: " + response.body());
        }
    }
    public static class TokenCache {
        public IdToken idToken;
        public String refreshToken;
        public LocalDateTime idTokenIssuedAt;
        public LocalDateTime refreshTokenIssuedAt;

        public boolean isIdTokenValid() {
            return idToken != null &&
                   idTokenIssuedAt != null &&
                   idTokenIssuedAt.plus(ID_TOKEN_VALIDITY).isAfter(LocalDateTime.now());
        }

        public boolean isRefreshTokenValid() {
            return refreshToken != null &&
                   refreshTokenIssuedAt != null &&
                   refreshTokenIssuedAt.plus(REFRESH_TOKEN_VALIDITY).isAfter(LocalDateTime.now());
        }
    }
}
