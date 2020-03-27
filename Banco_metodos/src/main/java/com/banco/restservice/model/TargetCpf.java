package com.banco.restservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TargetCpf extends Transacao {

    @JsonProperty("targetCpf")
    String targetCpf;

    public String getTargetCpf() {
        return targetCpf;
    }

    public void setTargetCpf(String targetCpf) {
        this.targetCpf = targetCpf;
    }

    @Override
    public String toString() {
        return "TargetCpf{" +
                "targetCpf='" + targetCpf + '\'' +
                '}';
    }
}
