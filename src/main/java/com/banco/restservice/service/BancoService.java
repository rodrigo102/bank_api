package com.banco.restservice.service;

import com.banco.restservice.dao.BancoDataAccess;
import com.banco.restservice.db.BancoDataBase;
import com.banco.restservice.model.*;
import org.springframework.stereotype.Service;


@Service
public class BancoService {

    private BancoDataAccess banco  = new BancoDataAccess();
    public Resposta create(Input input){

        Client client = new Client();
        client.setName(input.getName());
        client.setBalance((float) 0);
        client.setBirth(input.getBirth());
        client.setCpf(input.getCpf());

        return banco.create(client);

    }

    public BancoDataBase list (){
        return banco.list();
    }

    public Resposta deposit(Deposit deposit){
        String cpf = deposit.getCpf();
        Float money = deposit.getMoney();
        System.out.println(money);
        return banco.deposit(cpf, money);
    }

    public Resposta withdraw(Deposit deposit){
        String cpf = deposit.getCpf();
        Float money = deposit.getMoney();
        return banco.withdraw(cpf, money);
    }

    public Resposta transfer(Transfer transfer){
        String origem = transfer.getCpfRem();
        String destino = transfer.getCpfDest();
        Float money = transfer.getValue();
        return banco.transfer(origem, destino, money);
    }

}
