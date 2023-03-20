package com.example.onlineshopdemo;

public class User {
    protected String username;
    protected String password;
    protected String email;

    private String role = null;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
