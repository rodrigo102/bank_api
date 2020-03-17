package com.banco.restservice.dao;

import com.banco.restservice.db.BancoDataBase;
import com.banco.restservice.model.Client;
import com.banco.restservice.model.Error;
import com.banco.restservice.model.Resposta;
import com.banco.restservice.model.Sucesso;

public class BancoDataAccess {

    //instancia do objeto db
    public BancoDataBase db = new BancoDataBase();
    Error warning = new Error();
    Sucesso sucesso = new Sucesso();

    public Resposta create (Client client){
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

        for (Client c: db.getClients()){

            if (c.getCpf().equals(cpf)){
                float value = c.getBalance() + money;
                c.setBalance(value);
            }
        }

        return sucesso;

    }

    public Resposta withdraw (String cpf, float money){
        for (Client c: db.getClients()){
            if (c.getCpf().equals(cpf)){
                c.setBalance(c.getBalance() - money);
            }
        }
        return sucesso;
    }

    public Resposta transfer (String origem, String destino, float money){
        for (Client c: db.getClients()){
            if (c.getCpf().equals(origem)){
                c.setBalance(c.getBalance() - money);
            }
        }
        for (Client c: db.getClients()){
            if (c.getCpf().equals(destino)){
                c.setBalance(c.getBalance() + money);
            }
        }
        return sucesso;

    }
}
