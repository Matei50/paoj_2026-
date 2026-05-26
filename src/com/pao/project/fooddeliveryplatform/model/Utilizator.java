package com.pao.project.fooddeliveryplatform.model;

public abstract class Utilizator {
    private String nume, parola;
    private Adresa adresa;

    public Utilizator(String n, String p, Adresa a) {
        this.nume = n;
        this.parola = p;
        this.adresa = a;
    }

    public String getNume(){
        return nume;
    }

    public String getParola() {
        return parola;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setNume(String n){
        this.nume= n;
    }

    public void setParola(String p) {
        this.parola = p;
    }

    public void setAdresa(Adresa a){
        this.adresa = a;
    }

    public abstract String getRol();

    @Override
    public String toString() {
        return "Utilizator: " + nume + ", adresa: " + adresa;
    }
}
