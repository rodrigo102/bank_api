package com.banco.restservice.model;

public class Deposit {
    private String cpf;
    private Float money;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "cpf='" + cpf + '\'' +
                ", money=" + money +
                '}';
    }
}
