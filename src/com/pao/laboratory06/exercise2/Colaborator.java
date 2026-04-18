package com.pao.laboratory06.exercise2;

public abstract class Colaborator implements IOperatiiCitireScriere {
    protected String nume;
    protected String prenume;
    protected double venitBrutLunar;

    public Colaborator() {
    }

    public Colaborator(String nume, String prenume, double venitBrutLunar) {
        this.nume = nume;
        this.prenume = prenume;
        this.venitBrutLunar = venitBrutLunar;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public double getVenitBrutLunar() {
        return venitBrutLunar;
    }

    public abstract double calculeazaVenitNetAnual();

    public abstract TipColaborator getTip();

    public String descriere() {
        return String.format("%s: %s %s, venit net anual: %.2f lei",
                tipContract(), nume, prenume, calculeazaVenitNetAnual());
    }

    @Override
    public void afiseaza() {
        System.out.println(descriere());
    }
}