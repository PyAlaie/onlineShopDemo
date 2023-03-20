package com.example.onlineshopdemo;

public class FundRequest {
    private int amount;
    private Costumer costumer;
    private boolean isChecked = false;
    private boolean isConfirmed = false;

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
    }

    public void deny(){
        this.isChecked = true;
        this.isConfirmed = false;
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
