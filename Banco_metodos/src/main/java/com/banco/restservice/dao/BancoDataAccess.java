package com.banco.restservice.dao;

import com.banco.restservice.db.BancoDataBase;
import com.banco.restservice.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class BancoDataAccess {

    //instancia do objeto db
    public BancoDataBase db = new BancoDataBase();

    public Resposta create (Client client){
        db.getClients().add(client);
        Sucesso sucesso = new Sucesso();
        return sucesso;
    }

    public BancoDataBase list(){
        for (Client i:db.getClients()){
            System.out.println(i);
        }
        return db;
    }

    public Resposta deposit (String cpf, String idSender, Float money){
        for (Client c: db.getClients()){
            if (c.getCpf().equals(cpf)){
                float value = c.getBalance() + money;
                c.setBalance(value);

                SenderCpf sndrCpf = new SenderCpf();
                sndrCpf.setDateTime(new Date());
                sndrCpf.setAmount(money);
                sndrCpf.setType("Deposit");
                sndrCpf.setSenderCpf(idSender);
                c.getExtrato().add(sndrCpf);

            }
        }

        Sucesso sucesso = new Sucesso();
        return sucesso;
    }

    public Resposta withdraw (String cpf, float money){
        for (Client c: db.getClients()){
            if (c.getCpf().equals(cpf)){
                c.setBalance(c.getBalance() - money);
                Transacao transacao = new Transacao();
                transacao.setDateTime(new Date());
                transacao.setAmount(money);
                transacao.setType("Withdraw");
                c.getExtrato().add(transacao);

            }
        }

        Sucesso sucesso = new Sucesso();
        return sucesso;
    }

    public Resposta transfer (String origem, String destino, float money){
        for (Client c: db.getClients()){
            if (c.getCpf().equals(origem)){
                c.setBalance(c.getBalance() - money);
                TargetCpf targetCpf = new TargetCpf();
                targetCpf.setDateTime(new Date());
                targetCpf.setType("Transfer");
                targetCpf.setTargetCpf(destino);
                targetCpf.setAmount(money);
                c.getExtrato().add(targetCpf);
            }
        }
        for (Client c: db.getClients()){
            if (c.getCpf().equals(destino)){
                c.setBalance(c.getBalance() + money);
                SenderCpf senderCpf = new SenderCpf();
                senderCpf.setDateTime(new Date());
                senderCpf.setType("Transfer");
                senderCpf.setSenderCpf(origem);
                senderCpf.setAmount(money);
                c.getExtrato().add(senderCpf);
            }
        }


        Sucesso sucesso = new Sucesso();
        return sucesso;


    }

    public Client GetByCpf(String cpf) {
        System.out.println(cpf);
        for (Client c: db.getClients()){
            if (c.getCpf().equals(cpf)){
                System.out.println(c);
                return c;
            }
        }
        System.out.println("null");
        return null;
    }

    public Resposta update(Input input) {
        for(Client i: db.getClients()){
            if (i.getCpf().equals(input.getCpf())){
                i.setName(input.getName());
                i.setBirth(input.getBirth());
                Sucesso sucesso = new Sucesso();
                return sucesso;
            }
        }
        return null;
    }

    public Resposta delete(String cpf) {
        db.setClients(db.getClients().stream().filter(i -> !i.getCpf().equals(cpf)).collect(Collectors.toList()));
        Sucesso sucesso = new Sucesso();
        return sucesso;

    }

    public ArrayList<Transacao> getExtrato(String cpf) {
        for (Client i: db.getClients()){
            if (i.getCpf().equals(cpf)){
                return i.getExtrato();
            }
        }
        return null;
    }
}
