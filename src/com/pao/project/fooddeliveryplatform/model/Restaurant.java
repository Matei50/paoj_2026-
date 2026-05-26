package com.pao.project.fooddeliveryplatform.model;

import com.pao.project.fooddeliveryplatform.exception.RestaurantNegasitException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Restaurant implements Comparable<Restaurant>{
    private String nume, program;
    private CategorieRestaurant categorie;
    private List<Produs> listaProduse;
    private double rating;

    public Restaurant(String nume, String program, CategorieRestaurant categorie, double rating) {
        this.nume = nume;
        this.program = program;
        this.rating = rating;
        this.categorie = categorie;
        this.listaProduse = new ArrayList<>();
    }

    public String getNume(){
        return nume;
    }

    public String getProgram() {
        return program;
    }

    public CategorieRestaurant getCategorie() {
        return categorie;
    }

    public double getRating() {
        return rating;
    }

    public List<Produs> getListaProduse() {
        return listaProduse;
    }

    public void adaugaProdus(Produs produs) {
        listaProduse.add(produs);
    }

    public void afiseazaMeniu() {
        for (Produs produs : listaProduse) {
            System.out.println(produs);
        }
    }

    public Restaurant() {
        super();
    }

//    public Restaurant cautaRestaurant(String nume){
//        for (Restaurant r : restaurante) {
//            if(r.getNume().equalsIgnoreCase(nume)){
//                return r;
//            }
//        }
//        throw new RestaurantNegasitException("nu exista");
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(nume, that.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume);
    }

    @Override
    public String toString() {
        return nume + " (" + categorie + "), program: " + program + ", rating: " + rating;
    }

    @Override
    public int compareTo(Restaurant o){
        return this.nume.compareTo(o.nume);
    }

}

