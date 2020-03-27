package com.banco.restservice.controller;

import com.banco.restservice.db.BancoDataBase;
import com.banco.restservice.model.*;
import com.banco.restservice.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BancoController {

    //Autowired para injeção de dependencias

    @Autowired
    private BancoService bancoService;

    //New client creation
    @PostMapping("/v1/client")
    public Resposta Create(@RequestBody Input input){
        return this.bancoService.create(input);
    }

    //list all the clients with GET
    @GetMapping("/v1/client")
    public BancoDataBase List(){
        return this.bancoService.list();
    }

    //list a client using as key a CPF
    @GetMapping("/v1/client/{cpf}")
    public Client getByCpf(@PathVariable String cpf) { return this.bancoService.getByCpf(cpf);}

    @PostMapping("/v1/deposit")
    public Resposta deposit(@RequestBody Deposit deposit){
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

    //update a client file using PUT and the cpf as key
    @PutMapping("/v1/client/{cpf}")
    public Resposta update(@PathVariable String cpf, @RequestBody Input input){
        return this.bancoService.update(input);
    }

    @DeleteMapping("/v1/client/{cpf}")
    public Resposta delete(@PathVariable String cpf){
        return this.bancoService.delete(cpf);
    }

    @GetMapping ("/v1/extrato/{cpf}")

    public ArrayList<Transacao> extrato (@PathVariable String cpf){
        return this.bancoService.getExtrato(cpf);
    }
}
