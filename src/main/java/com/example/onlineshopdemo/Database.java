package com.example.onlineshopdemo;

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {
    private String name = null;
    private String webAddress = null;
    private String supportPhoneNumber = null;
    private int totalProfit;
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    private ArrayList<Seller> sellers = new ArrayList<Seller>();
    private ArrayList<Costumer> costumers = new ArrayList<Costumer>();
    private ArrayList<FundRequest> fundRequests = new ArrayList<FundRequest>();
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Cart> carts = new ArrayList<Cart>();
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<SubCategory> subCategories = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public void setSupportPhoneNumber(String supportPhoneNumber) {
        this.supportPhoneNumber = supportPhoneNumber;
    }

    public void setTotalProfit(int totalProfit) {
        this.totalProfit = totalProfit;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public void setSellers(ArrayList<Seller> sellers) {
        this.sellers = sellers;
    }

    public void setCostumers(ArrayList<Costumer> costumers) {
        this.costumers = costumers;
    }

    public void setFundRequests(ArrayList<FundRequest> fundRequests) {
        this.fundRequests = fundRequests;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void setSubCategories(ArrayList<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public String getName() {
        return name;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public String getSupportPhoneNumber() {
        return supportPhoneNumber;
    }

    public int getTotalProfit() {
        return totalProfit;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public ArrayList<Costumer> getCostumers() {
        return costumers;
    }

    public ArrayList<FundRequest> getFundRequests() {
        return fundRequests;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<SubCategory> getSubCategories() {
        return subCategories;
    }
}
