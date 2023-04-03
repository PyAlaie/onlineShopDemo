package com.example.onlineshopdemo;

import java.io.Serializable;

public class Category implements Serializable {
    private String title;

    public Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
