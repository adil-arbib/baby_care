package com.groupe6.babycare.dtos.auth;

public class TokenResponse {

    private String token;

    public TokenResponse(String tokon) {
        this.token = tokon;
    }

    public String getTokon() {
        return token;
    }

    public void setTokon(String tokon) {
        this.token = tokon;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "tokon='" + token + '\'' +
                '}';
    }
}
