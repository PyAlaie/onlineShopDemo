package com.example.onlineshopdemo;

import java.io.Serializable;

public class Item implements Serializable {
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

    @Override
    public String toString() {
        return "Item{" +
                "product=" + product +
                ", count=" + count +
                '}';
    }
}
