package com.example.onlineshopdemo;

import java.io.Serializable;

public class Wallet implements Serializable {
    private int balance;

    public void deposite(int amount){
        this.balance += amount;
    }

    public void withdrawl(int amount){
        if(this.balance >= amount){
            this.balance -= amount;
        }
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "balance=" + balance +
                '}';
    }
}
