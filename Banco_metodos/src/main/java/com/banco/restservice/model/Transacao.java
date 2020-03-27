package com.banco.restservice.model;

import java.util.Date;

public class Transacao {
    private Date dateTime;
    private double amount;
    private String type;



    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return "Transacao{" +
                "dateTime=" + dateTime +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
