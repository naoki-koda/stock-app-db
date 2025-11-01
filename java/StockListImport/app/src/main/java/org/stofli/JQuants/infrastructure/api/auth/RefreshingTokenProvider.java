package org.stofli.jquants.infrastructure.api.auth;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.stofli.httpclient.Http;
import org.stofli.jquants.infrastructure.api.dto.IdToken;
import org.stofli.jquants.infrastructure.api.dto.ResponseRefreshToken;

/**
 * Token provider that follows the refresh-token flow defined by J-Quants.
 * Implements the Template Method pattern by encapsulating the two-step authentication sequence.
 */
public class RefreshingTokenProvider extends Http implements TokenProvider {

    private static final URI AUTH_USER_URI = URI.create("https://api.jquants.com/v1/token/auth_user");
    private static final String AUTH_REFRESH_ENDPOINT = "https://api.jquants.com/v1/token/auth_refresh?refreshtoken=";

    private final CredentialProvider credentialProvider;
    private final ObjectMapper mapper;

    private ResponseRefreshToken refreshToken;
    private IdToken idToken;

    public RefreshingTokenProvider(CredentialProvider credentialProvider) {
        this(credentialProvider, new ObjectMapper());
    }

    public RefreshingTokenProvider(CredentialProvider credentialProvider, ObjectMapper mapper) {
        this.credentialProvider = credentialProvider;
        this.mapper = mapper;
    }

    @Override
    public synchronized String obtainToken() throws IOException, InterruptedException {
        if (idToken == null) {
            authenticate();
        }
        return idToken.value();
    }

    private void authenticate() throws IOException, InterruptedException {
        authenticateUser();
        requestIdToken();
    }

    private void authenticateUser() throws IOException, InterruptedException {
        JQuantsCredentials credentials = credentialProvider.loadCredentials();
        String payload = mapper.writeValueAsString(credentials);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(AUTH_USER_URI)
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        HttpResponse<String> response = _httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != HTTP_STATUS_CODE_OK) {
            throw new IllegalStateException("Failed to authenticate user: " + response.body());
        }

        refreshToken = mapper.readValue(response.body(), ResponseRefreshToken.class);
    }

    private void requestIdToken() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTH_REFRESH_ENDPOINT + refreshToken.value()))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = _httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != HTTP_STATUS_CODE_OK) {
            throw new IllegalStateException("Failed to refresh token: " + response.body());
        }

        idToken = mapper.readValue(response.body(), IdToken.class);
    }
}
