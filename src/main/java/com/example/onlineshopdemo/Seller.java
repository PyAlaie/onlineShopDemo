package com.example.onlineshopdemo;

import java.io.Serializable;
import java.util.ArrayList;

public class Seller extends User implements Serializable {
    private boolean isAuth = false;
    private boolean isChecked = false;
    private Wallet wallet;
    private ArrayList<Product> products = new ArrayList<>();

    private ArrayList<Transaction> transactions = new ArrayList<>();

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Seller(String username, String password, String email) {
        super(username, password, email);
        this.wallet = new Wallet();
        this.setRole("seller");

        Notification welcome = new Notification("Welcome to Online Shop!");
        welcome.setText("Hope you have a great time selling shit to people :)");
        this.addNotification(welcome);
    }

    public boolean isAuth() {
        return isAuth;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void authorize(){
        this.isAuth = true;
        this.isChecked = true;
    }

    public void unauthorize(){
        this.isChecked = true;
        this.isAuth = false;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "isAuth=" + isAuth +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void addTransaction(Transaction transaction){
        if(!transactions.contains(transaction)){
            transactions.add(transaction);
        }
    }

    public void addProduct(Product product){
        if(!products.contains(product)){
            products.add(product);
        }
    }
}
