package com.banco.restservice.controller;

import com.banco.restservice.db.BancoDataBase;
import com.banco.restservice.model.Deposit;
import com.banco.restservice.model.Input;
import com.banco.restservice.model.Resposta;
import com.banco.restservice.model.Transfer;
import com.banco.restservice.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @PostMapping("/v1/client")
    public Resposta Create(@RequestBody Input input){
        return this.bancoService.create(input);
    }

    @GetMapping("/v1/list")
    public BancoDataBase List(){
        return this.bancoService.list();
    }

    @PostMapping("/v1/deposit")
    public Resposta deposit(@RequestBody Deposit deposit){
        System.out.println(deposit);
        return this.bancoService.deposit(deposit);
    }

    @PostMapping("/v1/withdraw")
    public Resposta withdraw(@RequestBody Deposit deposit){

        return this.bancoService.withdraw(deposit);
    }

    @PostMapping("/v1/transfer")
    public Resposta withdraw(@RequestBody Transfer transfer){
        return this.bancoService.transfer(transfer);
    }
}
