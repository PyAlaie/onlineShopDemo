package com.example.onlineshopdemo;

import java.io.Serializable;

public class Notification implements Serializable {
    private String title;
    private String text = null;

    public Notification(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
