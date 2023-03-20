package com.example.onlineshopdemo;

import java.util.ArrayList;

public class Costumer extends User{
    private String phoneNumber = null;
    private String address = null;
    private Wallet wallet = new Wallet();;
    private ArrayList<Cart> carts = new ArrayList<Cart>();

    public Costumer(String username, String password, String email) {
        super(username, password, email);
        this.setRole("costumer");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void addCart(){
        carts.add(new Cart());
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }
}
