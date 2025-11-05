package org.stofli.adapter.webapi.common;

import java.net.http.HttpClient;

public abstract class Http {
    public final HttpClient _httpClient;

    public static final int HTTP_STATUS_CODE_OK = 200;
    public Http() {
        this._httpClient = HttpClient.newHttpClient();
    }
    public Http(HttpClient httpClient) {
        this._httpClient = httpClient;
    }
}
