package org.stofli.adopter.webapi.auth;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.stofli.adopter.webapi.auth.core.CredentialProvider;
import org.stofli.adopter.webapi.auth.core.IdToken;
import org.stofli.adopter.webapi.auth.core.TokenProvider;
import org.stofli.adopter.webapi.common.Http;
import org.stofli.adopter.webapi.jquants.dto.ResponseRefreshToken;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JQuantsTokenProvider extends Http implements TokenProvider {
    private final CredentialProvider<JQuantsCredentials> credentialProvider;
    private final ObjectMapper mapper;
    private static final String AUTH_URL_BASE = "https://api.jquants.com/v1/token/";
    private static final String AUTH_USER_PATH = "auth_user";
    public JQuantsTokenProvider(CredentialProvider<JQuantsCredentials> credentialProvider) {
        this(credentialProvider, new ObjectMapper());
    }

    public JQuantsTokenProvider(CredentialProvider<JQuantsCredentials> credentialProvider, ObjectMapper mapper) {
        this.credentialProvider = credentialProvider;
        this.mapper = mapper;
    }
    @Override
    public IdToken obtainToken() throws IOException, InterruptedException {
        return authenticate();
    }
    private IdToken authenticate() throws IOException, InterruptedException {
        return requestIdToken(authenticateUser());
    }

    private ResponseRefreshToken authenticateUser() throws IOException, InterruptedException {
        JQuantsCredentials credentials = credentialProvider.loadCredentials();
        String payload = mapper.writeValueAsString(credentials);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTH_URL_BASE + AUTH_USER_PATH))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        HttpResponse<String> response = _httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == HTTP_STATUS_CODE_OK) {
            String resBody =response.body();
            return mapper.readValue(resBody, ResponseRefreshToken.class);
        } else {
            throw new IllegalStateException("Failed to authenticate user: " + response.body());
        }
    }
    private IdToken requestIdToken(ResponseRefreshToken refreshToken) throws IOException, InterruptedException {
        StringBuilder idTokenUrl = new StringBuilder(AUTH_URL_BASE).append("auth_refresh?refreshtoken=").append(refreshToken.toString());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(idTokenUrl.toString()))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = _httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == HTTP_STATUS_CODE_OK) {
            return mapper.readValue(response.body(), IdToken.class);
        } else {
            throw new IllegalStateException("Failed to obtain ID token: " + response.body());
        }
    }
}
