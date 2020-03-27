package com.banco.restservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SenderCpf extends Transacao{

    @JsonProperty("senderCpf")
    String senderCpf;

    public String getSenderCpf() {
        return senderCpf;
    }

    public void setSenderCpf(String senderCpf) {
        this.senderCpf = senderCpf;
    }

    @Override
    public String toString() {
        return "SenderCpf{" +
                "senderCpf='" + senderCpf + '\'' +
                '}';
    }
}
