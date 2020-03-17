package com.banco.restservice.dao;

import com.banco.restservice.db.BancoDataBase;
import com.banco.restservice.model.Client;
import com.banco.restservice.model.Error;
import com.banco.restservice.model.Resposta;
import com.banco.restservice.model.Sucesso;

public class BancoDataAccess {

    //instancia do objeto db
    private BancoDataBase db = new BancoDataBase();
    Error warning = new Error();
    Sucesso sucesso = new Sucesso();

    public Resposta create (Client client){
        for (Client i: db.getClients()){
            if (i.getCpf().equals(client.getCpf())){

                warning.setCode("0x01");
                warning.setMessage("CPF já cadastrado");
                return warning;
            }
        }

        db.getClients().add(client);

        return sucesso;
    }

    public BancoDataBase list(){
        for (Client i:db.getClients()){
            System.out.println(i);
        }
        return db;

    }

    public Resposta deposit (String cpf, Float money){

        if (money < 0){
            warning.setCode("0x03");
            warning.setMessage("Quantidade inválida");
            return warning;
        }

        for (Client c: db.getClients()){

            if (c.getCpf().equals(cpf)){
                float value = c.getBalance() + money;
                c.setBalance(value);
                return sucesso;
            }
        }

        warning.setCode("0x02");
        warning.setMessage("Cpf inexistente");
        return warning;

    }

    public Resposta withdraw (String cpf, float money){
        for (Client c: db.getClients()){
            if (c.getCpf().equals(cpf)){
                if (c.getBalance() < 0){
                    warning.setCode("0x04");
                    warning.setMessage("Saldo insuficiente");
                    return warning;
                }
                c.setBalance(c.getBalance() - money);
                return sucesso;
            }
        }

        return sucesso;

    }

    public Resposta transfer (String origem, String destino, float money){
        boolean achou = false; //flag para indicar se o cpf foi encontrado ou não
        for (Client c: db.getClients()){
            if (c.getCpf().equals(origem)){
                if (c.getBalance() < 0){
                    warning.setCode("0x04");
                    warning.setMessage("Saldo insuficiente");
                    return warning;
                }
                c.setBalance(c.getBalance() - money);
                achou = true;
            }
        }

        if (achou == false) {

            warning.setCode("0x02");
            warning.setMessage("Cpf inexistente");
            return warning;
        }

        achou = false;
        for (Client c: db.getClients()){
            if (c.getCpf().equals(destino)){
                if (c.getBalance() < 0){
                    warning.setCode("0x04");
                    warning.setMessage("Saldo insuficiente");
                    return warning;
                }

                c.setBalance(c.getBalance() + money);
                return sucesso;
            }
        }

        if (achou == false) {

            warning.setCode("0x02");
            warning.setMessage("Cpf inexistente");
            return warning;
        }

        return sucesso;

    }
}
