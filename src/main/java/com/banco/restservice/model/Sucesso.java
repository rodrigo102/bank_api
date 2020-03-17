package com.banco.restservice.model;

public class Sucesso extends Resposta {

    private String message = "Operacao concluída com sucesso!";

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Sucesso{" +
                "message='" + message + '\'' +
                '}';
    }
}
