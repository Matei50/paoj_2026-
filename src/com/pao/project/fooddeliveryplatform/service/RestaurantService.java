package com.pao.project.fooddeliveryplatform.service;
import com.pao.project.fooddeliveryplatform.exception.RestaurantNegasitException;
import com.pao.project.fooddeliveryplatform.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class RestaurantService {
    private static RestaurantService instance;
    private List<Restaurant> restaurante;
    private Map<String, Restaurant> restauranteMap;

    private RestaurantService() {
        restaurante = new ArrayList<>();
        this.restauranteMap = new HashMap<>();
    }

    public static RestaurantService getInstance() {
        if (instance == null) {
            instance = new RestaurantService();
        }
        return instance;
    }

    public void adaugaRestaurant(Restaurant r){
        restaurante.add(r);
        restauranteMap.put(r.getNume(), r);
    }

    void stergeRestaurant(String nume){
        restaurante.removeIf(r -> r.getNume().equalsIgnoreCase(nume));
        restauranteMap.remove(nume);
    }

    public Restaurant cautaRestaurant(String nume){
        Restaurant r = restauranteMap.get(nume);
        if(r == null){
            throw new RestaurantNegasitException("Nu exista");
        }
        return r;
    }

    public void afiseazaRestaurante() {
        for(Restaurant r : restaurante)
            System.out.println(r);
    }
}
