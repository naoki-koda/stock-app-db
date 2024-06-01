package org.stofli.HttpClient;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class Http {
    public final HttpClient _httpClient;

    public static final int HTTP_STATUS_CODE_OK = 200;
    Http() {
        this._httpClient = HttpClient.newHttpClient();
    }
    Http(HttpClient httpClient) {
        this._httpClient = httpClient;
    }

}
