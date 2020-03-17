package com.banco.restservice.db;

import com.banco.restservice.model.Client;

import java.util.ArrayList;
import java.util.List;

public class BancoDataBase {

    private List<Client> clients = new ArrayList<>();

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;

    }
}




