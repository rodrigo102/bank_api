package com.banco.restservice.service;

import com.banco.restservice.dao.BancoDataAccess;
import com.banco.restservice.db.BancoDataBase;
import com.banco.restservice.model.*;
import com.banco.restservice.model.Error;
import org.springframework.stereotype.Service;


@Service
public class BancoService {

    private BancoDataAccess banco  = new BancoDataAccess();
    Error warning = new Error();

    public Resposta create(Input input){

        Client client = new Client();
        client.setName(input.getName());
        client.setBalance((float) 0);
        client.setBirth(input.getBirth());
        client.setCpf(input.getCpf());

        for (Client i: banco.db.getClients()){
            if (i.getCpf().equals(input.getCpf())){
                warning.setCode("0x01");
                warning.setMessage("CPF já cadastrado");
                return warning;
            }
        }
        return banco.create(client);
    }

    public BancoDataBase list (){
        return banco.list();
    }

    public Resposta deposit(Deposit deposit){
        String cpf = deposit.getCpf();
        Float money = deposit.getMoney();

        if (money < 0){
            warning.setCode("0x03");
            warning.setMessage("Quantidade inválida");
            return warning;
        }

        for (Client i: banco.db.getClients()){
            if (i.getCpf().equals(cpf)){
                return banco.deposit(cpf, money);
            }
        }
        warning.setCode("0x02");
        warning.setMessage("Cpf inexistente");
        return warning;

    }

    public Resposta withdraw(Deposit deposit){
        String cpf = deposit.getCpf();
        Float money = deposit.getMoney();

        if (money < 0){
            warning.setCode("0x03");
            warning.setMessage("Quantidade inválida");
            return warning;
        }

        for (Client i: banco.db.getClients()){

            if (i.getCpf().equals(cpf)){
                if (i.getBalance()<money){
                    warning.setCode("0x02");
                    warning.setMessage("Cpf inexistente");
                    return warning;
                }
                return banco.withdraw(cpf, money);
            }
        }

        warning.setCode("0x02");
        warning.setMessage("Cpf inexistente");
        return warning;


    }

    public Resposta transfer(Transfer transfer){
        String origem = transfer.getCpfRem();
        String destino = transfer.getCpfDest();
        Float money = transfer.getValue();
        boolean find = false;

        for (Client i: banco.db.getClients()){
            if (i.getCpf().equals(origem)){
                if (i.getBalance() < money){
                    warning.setCode("0x03");
                    warning.setMessage("Quantidade inválida");
                    return warning;
                }
                find = true;

            }

            }
        if (find = false){
            warning.setCode("0x02");
            warning.setMessage("CPF origem inexiste");
            return warning;
        }

        find = false;

        for (Client i: banco.db.getClients()){
            if (i.getCpf().equals(destino)){
                find = true;
            }

        }

        if (find = false){
            warning.setCode("0x02");
            warning.setMessage("CPF do destinatário inexiste");
            return warning;
        }

        return banco.transfer(origem, destino, money);
    }

}
