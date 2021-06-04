package ru.omsu.imit.novikova.rest.response;

public class TokenResponse {
    private int id;
    private String accessToken;

    public TokenResponse() {
    }

    public TokenResponse(int id, String accessToken) {
        this.id = id;
        this.accessToken = accessToken;
    }

    public int getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
