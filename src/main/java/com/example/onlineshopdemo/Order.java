package com.example.onlineshopdemo;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private Cart cart;
    private Date date;
    private int totalPrice;
    private Costumer costumer;
    private ArrayList<Seller> sellers;
    private boolean isChecked = false;
    private boolean isConfirmed = false;

    public Order(Date date, int totalPrice, Costumer costumer, ArrayList<Seller> seller, Cart cart) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.costumer = costumer;
        this.sellers = seller;
        this.cart = cart;
    }

    public Date getDate() {
        return date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public ArrayList<Seller> getSeller() {
        return sellers;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void confirm(){
        this.isChecked = true;
        this.isConfirmed = true;
        costumer.getWallet().withdrawl(totalPrice);
        // TODO: add 90 percent of money to sellers and 10 percent to shop
    }

    public void deny(){
        this.isChecked = true;
        this.isConfirmed = false;
    }
}
