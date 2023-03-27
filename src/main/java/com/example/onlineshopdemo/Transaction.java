package com.example.onlineshopdemo;

import java.util.Date;

public class Transaction {
    private Date date;
    private int amount;
    private User from;
    private User to;

    public Transaction(Date date, int amount, User from, User to) {
        this.date = date;
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
