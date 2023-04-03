package com.example.onlineshopdemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Cart implements Serializable {
    private ArrayList<Item> items = new ArrayList<>();
    private boolean checkedOut = false;

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", checkedOut=" + checkedOut +
                '}';
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public Order checkout(Costumer costumer){
        Date date = new Date();
        ArrayList<Seller> sellers = new ArrayList<>();
        for(Item item : items){
            if(!sellers.contains(item.getProduct().getSeller())){
                sellers.add(item.getProduct().getSeller());
            }
        }
        this.checkedOut = true;
        return new Order(date, this.calculteTotalPrice(), costumer, sellers, this);
    }

    public int calculteTotalPrice(){
        int res = 0;
        for(Item item : items){
            res += item.getCount() * item.getProduct().getPrice();
        }
        return res;
    }
}
