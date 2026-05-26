package com.pao.project.fooddeliveryplatform.model;
import java.util.Objects;

public class Produs {
    private String nume, categorie;
    private double pret;


    public Produs(String n, String c, double p){
        this.nume= n;
        this.pret = p;
        this.categorie = c;
    }

    public String getNume() {
        return nume;
    }

    public String getCategorie() {
        return categorie;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double p) {
        this.pret = p;
    }

    public void setCategorie(String c) {
        this.categorie = c;
    }

    public void setNume(String n) {
        this.nume = n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produs)) return false;
        Produs produs = (Produs) o;
        return Double.compare(produs.getPret(), pret) == 0 && nume.equals(produs.nume) && categorie.equals(produs.categorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, categorie, pret);
    }

    @Override
    public String toString() {
        return "Produs: " + nume + " pret: " + pret + " lei (" + categorie + ")";
    }
}
