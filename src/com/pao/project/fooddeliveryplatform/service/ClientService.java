package com.pao.project.fooddeliveryplatform.service;
import com.pao.project.fooddeliveryplatform.exception.ClientNegasitException;
import com.pao.project.fooddeliveryplatform.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ClientService {
    private static ClientService instance;
    private List<Client> clienti;
    private Map<String, Client> clientiMap;

    private ClientService(){
        clienti = new ArrayList<>();
        this.clientiMap = new HashMap<>();
    }

    public static ClientService getInstance() {
        if(instance == null) {
            instance = new ClientService();
        }
        return instance;
    }

    public void adaugaClient(Client c) {
        clienti.add(c);
        clientiMap.put(c.getNume(), c);
    }

    public void stergeClient(Client c) {
        clienti.remove(c);
        clientiMap.remove(c.getNume());
    }

    public Client cautaClient(String nume) {
        Client c = clientiMap.get(nume);
        if(c == null) {
        throw new ClientNegasitException("Clientul nu a fost gasit.");
        }
        return c;
    }

}
