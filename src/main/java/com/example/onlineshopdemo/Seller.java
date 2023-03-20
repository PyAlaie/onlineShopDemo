package com.example.onlineshopdemo;

public class Seller extends User{
    private boolean isAuth = false;
    private boolean isChecked = false;
    private Wallet wallet;

    public Seller(String username, String password, String email) {
        super(username, password, email);
        this.wallet = new Wallet();
        this.setRole("seller");
    }

    public void authorize(){
        this.isAuth = true;
        this.isChecked = true;
    }

    public void unauthorize(){
        this.isChecked = true;
        this.isAuth = false;
    }
}
