package com.banco.restservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;

public class Client {


    private String name;
    private Date birth;
    private String cpf;
    private Float balance;
    @JsonIgnore
    private ArrayList<Transacao> extrato = new ArrayList<Transacao>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public ArrayList<Transacao> getExtrato() {
        return extrato;
    }

    public void setExtrato(ArrayList<Transacao> extrato) {
        this.extrato = extrato;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", birth='" + birth + '\'' +
                ", cpf='" + cpf + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}

