package com.banco.restservice.model;

public class Transfer {

    private String cpfDest;
    private String cpfRem;
    private Float value;

    public String getCpfDest() {
        return cpfDest;
    }

    public void setCpfDest(String cpfDest) {
        this.cpfDest = cpfDest;
    }

    public String getCpfRem() {
        return cpfRem;
    }

    public void setCpfRem(String cpfRem) {
        this.cpfRem = cpfRem;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "cpfDest='" + cpfDest + '\'' +
                ", cpfRem='" + cpfRem + '\'' +
                ", value=" + value +
                '}';
    }
}
