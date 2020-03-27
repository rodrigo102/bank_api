package com.banco.restservice.service;

import com.banco.restservice.dao.BancoDataAccess;
import com.banco.restservice.db.BancoDataBase;
import com.banco.restservice.model.*;
import com.banco.restservice.model.Error;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class BancoService {

    private BancoDataAccess banco  = new BancoDataAccess();


    public Resposta create(Input input){



        Client client = new Client();
        client.setName(input.getName());
        client.setBalance((float) 0);
        client.setBirth(input.getBirth());
        client.setCpf(input.getCpf());

        for (Client i: banco.db.getClients()){
            if (i.getCpf().equals(input.getCpf())){
                Error warning = new Error();
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
        String idSender = deposit.getIdSender();
        if (money < 0){
            Error warning = new Error();
            warning.setCode("0x03");
            warning.setMessage("Quantidade inválida");
            return warning;
        }
        for (Client i: banco.db.getClients()){
            if (i.getCpf().equals(cpf)){
                return banco.deposit(cpf, idSender, money);
            }
        }
        Error warning = new Error();
        warning.setCode("0x02");
        warning.setMessage("Cpf inexistente");
        return warning;

    }

    public Resposta withdraw(Deposit deposit){
        String cpf = deposit.getCpf();
        Float money = deposit.getMoney();
        if (money < 0){
            Error warning = new Error();
            warning.setCode("0x03");
            warning.setMessage("Quantidade inválida");
            return warning;
        }
        for (Client i: banco.db.getClients()){
            if (i.getCpf().equals(cpf)){
                if (i.getBalance()<money){
                    Error warning = new Error();
                    warning.setCode("0x02");
                    warning.setMessage("Cpf inexistente");
                    return warning;
                }
                return banco.withdraw(cpf, money);
            }
        }
        Error warning = new Error();
        warning.setCode("0x02");
        warning.setMessage("Cpf inexistente");
        return warning;
    }

    public Resposta transfer(Transfer transfer){
        String origem = transfer.getCpfRem();
        System.out.println(origem);
        String destino = transfer.getCpfDest();
        System.out.println(destino);
        Float money = transfer.getValue();
        boolean find = false;
        for (Client i: banco.db.getClients()){
            System.out.println("TO AQUI PATRAO");
            if (i.getCpf().equals(origem)){
                if (i.getBalance() < money){
                    Error warning = new Error();
                    warning.setCode("0x03");
                    warning.setMessage("Quantidade inválida");
                    return warning;
                }
                find = true;

            }

            }
        if (find = false){
            Error warning = new Error();
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
            Error warning = new Error();
            warning.setCode("0x02");
            warning.setMessage("CPF do destinatário inexiste");
            return warning;
        }
        return banco.transfer(origem, destino, money);
    }

    public Client getByCpf(String cpf) {
        return banco.GetByCpf(cpf);
    }

    public Resposta update(Input input) {
        Client client = new Client();
        client.setName(input.getName());
        client.setBirth(input.getBirth());
        client.setCpf(input.getCpf());

        Error warning = new Error();
        warning.setCode("0x02");
        warning.setMessage("CPF inexiste");
        return warning;
    }

    public Resposta delete(String cpf) {
        for (Client i: banco.db.getClients()){
            if (i.getCpf().equals(cpf)){
                return banco.delete(cpf);
            }
        }
        Error warning = new Error();
        warning.setCode("0x02");
        warning.setMessage("CPF inexiste");
        return warning;
    }

    public ArrayList<Transacao> getExtrato(String cpf){
        for (Client i: banco.db.getClients()){
            if (i.getCpf().equals(cpf)){
                return banco.getExtrato(cpf);
            }
        }
        return null;
    }
}
