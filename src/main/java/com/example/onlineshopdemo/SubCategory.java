package com.example.onlineshopdemo;

import java.io.Serializable;

public class SubCategory implements Serializable {
    private String subTitle;
    private Category category;

    public SubCategory(Category category, String subTitle) {
        this.category = category;
        this.subTitle = subTitle;
    }

    public Category getCategory() {
        return category;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Override
    public String toString() {
        return this.category.getTitle() + "/" + this.subTitle;
    }
}
