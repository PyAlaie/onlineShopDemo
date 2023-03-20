package com.example.onlineshopdemo;

public class Admin extends User {
    public Admin(String username, String password, String email) {
        super(username,password,email);
        this.setRole("admin");
    }
}
