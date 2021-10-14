package com.example.project.Domain;

public class UserDetails {
    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserDetails(String id, String fullName, String userName, String email, String role) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.role = role;
    }

    private String id;
    private String fullName;
    private String userName;
    private String email;
    private String role;
    private String token;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public UserDetails(String id, String fullName, String userName, String email, String role,String token) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.token=token;
    }
}
