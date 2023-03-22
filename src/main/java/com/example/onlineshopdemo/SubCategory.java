package com.example.onlineshopdemo;

public class SubCategory{
    private String subTitle;
    private Category category;

    public SubCategory(Category category, String subTitle) {
        this.category = category;
        this.subTitle = subTitle;
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
