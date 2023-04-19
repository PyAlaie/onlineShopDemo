package com.example.onlineshopdemo;

import java.io.Serializable;

public class FundRequest implements Serializable {
    private int amount;
    private Costumer costumer;
    private boolean isChecked = false;
    private boolean isConfirmed = false;

    public FundRequest(int amount, Costumer costumer) {
        this.amount = amount;
        this.costumer = costumer;
    }

    public int getAmount() {
        return amount;
    }

    public Costumer getCostumer() {
        return costumer;
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
        this.costumer.getWallet().deposite(this.amount);
        Notification notification = new Notification("Your fund request confirmed!");
        notification.setText("Your fund request was checked and confirmed by the admins, so "+amount+" amount of money is deposited to your wallet!");
        this.costumer.addNotification(notification);
    }

    public void deny(){
        this.isChecked = true;
        this.isConfirmed = false;
        Notification notification = new Notification("Your fund request is not confirmed!");
        notification.setText("Your fund request was checked and denied by the admins!");
        this.costumer.addNotification(notification);
    }

    @Override
    public String toString() {
        return "FundRequest{" +
                "amount=" + amount +
                ", costumer=" + costumer +
                ", isChecked=" + isChecked +
                ", isConfirmed=" + isConfirmed +
                '}';
    }
}
