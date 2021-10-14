package com.example.project.Domain;

public class RegisterationResponse {
    public RegisterationResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private String response;
}
