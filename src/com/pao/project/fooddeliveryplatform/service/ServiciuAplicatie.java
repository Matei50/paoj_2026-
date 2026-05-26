package com.pao.project.fooddeliveryplatform.service;
import com.pao.project.fooddeliveryplatform.exception.ProdusIndisponibilException;
import com.pao.project.fooddeliveryplatform.exception.RestaurantNegasitException;
import com.pao.project.fooddeliveryplatform.model.Client;
import com.pao.project.fooddeliveryplatform.model.Comanda;
import com.pao.project.fooddeliveryplatform.model.Produs;
import com.pao.project.fooddeliveryplatform.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ServiciuAplicatie {
    private List<Restaurant> restaurante;
    private List<Client> clienti;
    private List<Comanda> comenzi;
    private Map<String, Restaurant> restauranteMap;

    public ServiciuAplicatie() {
        this.restaurante = new ArrayList<>();
        this.clienti = new ArrayList<>();
        this.comenzi = new ArrayList<>();
        this.restauranteMap = new HashMap<>();
    }

    public void adaugareRestaurante(Restaurant r) {
        restaurante.add(r);
        restauranteMap.put(r.getNume(), r);
    }
    public Restaurant cautaRestaurant(String nume){
        for (Restaurant r : restaurante){
            if(r.getNume().equalsIgnoreCase(nume))
                return r;
        }
        throw new RestaurantNegasitException("Restaurantul nu a fost gasit.");
    }

    public void adaugareClient(Client c){
        clienti.add(c);
    }

    public Comanda plaseazaComanda(Client client){
        Comanda c = new Comanda(comenzi.size() + 1, client);

        for (Produs p : client.getCos().getProduse()){
            c.adaugaProdus(p);
        }

        comenzi.add(c);
        client.getCos().getProduse().clear();

        return c;
    }

    public void adaugaProdusInCos(Client client, Produs produs) {
        if(produs == null) {
            throw new ProdusIndisponibilException("Produsul nu exista");
        }
        client.getCos().adaugaProdus(produs);
    }





}
