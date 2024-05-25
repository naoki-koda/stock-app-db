package org.stofli.HttpClient;

public class RequestRefreshToken {
    public String refreshtoken;

    public RequestRefreshToken(String refreshToken) {
        this.refreshtoken = refreshToken;
    }

    @Override
    public String toString() {
        return refreshtoken;
    }
}