package com.example.onlineshopdemo;

public class Item {
    private Product product;
    private int count;

    public Item(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }
}
