package com.example.onlineshopdemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Order implements Serializable {
    private Cart cart;
    private Date date;
    private int totalPrice;
    private Costumer costumer;
    private ArrayList<Seller> sellers;
    private boolean isChecked = false;
    private boolean isConfirmed = false;

    public Order(Date date, int totalPrice, Costumer costumer, ArrayList<Seller> seller, Cart cart) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.costumer = costumer;
        this.sellers = seller;
        this.cart = cart;
    }

    public Date getDate() {
        return date;
    }

    public Cart getCart() {
        return cart;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public ArrayList<Seller> getSeller() {
        return sellers;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void confirm(){
        this.isChecked = true;
        this.isConfirmed = true;
        costumer.getWallet().withdrawl(totalPrice);

        HashMap<Seller, Integer> amounts = new HashMap<Seller, Integer>();
        for(Item item : cart.getItems()){
            if(amounts.containsKey(item.getProduct().getSeller())){
                amounts.put(item.getProduct().getSeller(), amounts.get(item.getProduct().getSeller()) + item.getCount() * item.getProduct().getPrice());
            }
            else {
                amounts.put(item.getProduct().getSeller(), item.getCount() * item.getProduct().getPrice());
            }

            item.getProduct().decreaseCount(item.getCount());
            costumer.addToPerchasedProducts(item);
        }

        for(Seller seller : amounts.keySet()){
            Notification notification = new Notification("You just sold an item!");
            notification.setText("For more details, ckeckout your trancations");
            seller.addNotification(notification);

            Transaction transaction = new Transaction(new Date(), (int)(amounts.get(seller)*0.9), costumer, seller);
            seller.getWallet().deposite((int)(amounts.get(seller)*0.9));
            seller.addTransaction(transaction);
            OnlineShop.increaseTotalProfit((int)(amounts.get(seller)*0.1));
        }

        Notification notification = new Notification("Your order is confirmed!");
        notification.setText("Your notification was viewed and confirmed by the admins :)");
        costumer.addNotification(notification);
    }

    public void deny(){
        this.isChecked = true;
        this.isConfirmed = false;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cart=" + cart +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                ", costumer=" + costumer +
                '}';
    }
}
