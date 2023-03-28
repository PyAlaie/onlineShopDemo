package com.example.onlineshopdemo;

import java.util.ArrayList;

public class Product {
    private String name;
    private int price;
    private int count;
    public String desciption;
    private Seller seller = null;
    private SubCategory category = null;
    private ArrayList<Comment>  comments = new ArrayList<>();

    public Product(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public Product(String name, int price, int count, SubCategory category) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.category = category;
    }

    public Product(String name, int price, int count,Seller s, SubCategory category) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.category = category;
        this.seller = s;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public void setCategory(SubCategory category) {
        this.category = category;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public SubCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", desciption='" + desciption + '\'' +
                ", seller=" + seller +
                ", category=" + category +
                '}';
    }

    public void decreaseCount(int amount){
        this.count -= amount;
    }

    public void addComment(Comment comment){
        if(!comment.getText().trim().equals("")){
            comments.add(comment);
        }
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
