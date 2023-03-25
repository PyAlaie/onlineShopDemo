package com.example.onlineshopdemo;

import java.util.ArrayList;

public class Costumer extends User{
    private String phoneNumber = null;
    private String address = null;
    private Wallet wallet = new Wallet();;
    private ArrayList<Cart> carts = new ArrayList<Cart>();

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public Cart getCart() {
        if(carts.size() < 1){
            carts.add(new Cart());
        }
        return carts.get(0);
    }

    public void clearCart(){
        this.carts.clear();
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "wallet=" + wallet +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
