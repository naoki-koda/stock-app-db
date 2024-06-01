package org.stofli.HttpClient;

import java.net.http.HttpClient;

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
