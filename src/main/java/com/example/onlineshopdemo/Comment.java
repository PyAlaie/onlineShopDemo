package com.example.onlineshopdemo;

public class Comment {
    private String text;
    private Product product;
    private Costumer costumer;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "text='" + text + '\'' +
                ", product=" + product +
                ", costumer=" + costumer +
                '}';
    }

    public Comment(String text, Product product, Costumer costumer) {
        this.text = text;
        this.product = product;
        this.costumer = costumer;
    }
}
