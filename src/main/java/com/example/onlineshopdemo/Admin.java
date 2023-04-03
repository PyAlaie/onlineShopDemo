package com.example.onlineshopdemo;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin(String username, String password, String email) {
        super(username,password,email);
        this.setRole("admin");
    }
}
