package com.example.project.Domain;

public class LoginResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponse(String token) {
        this.token = token;
    }
}
