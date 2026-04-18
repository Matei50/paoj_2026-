package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer> {
    private double sold;
    private boolean autentificat;

    public Inginer(String nume, String prenume, String telefon, double salariu, double sold) {
        super(nume, prenume, telefon, salariu);
        if (sold < 0) {
            throw new IllegalArgumentException("Soldul nu poate fi negativ.");
        }
        this.sold = sold;
        this.autentificat = false;
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.isBlank() || parola == null || parola.isBlank()) {
            throw new IllegalArgumentException("User si parola nu pot fi nule sau goale.");
        }
        autentificat = true;
    }

    @Override
    public double consultareSold() {
        if (!autentificat) {
            throw new IllegalStateException("Utilizatorul nu este autentificat.");
        }
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0) {
            throw new IllegalArgumentException("Suma trebuie sa fie pozitiva.");
        }
        if (!autentificat) {
            throw new IllegalStateException("Utilizatorul nu este autentificat.");
        }
        if (suma > sold) {
            return false;
        }
        sold -= suma;
        return true;
    }

    @Override
    public int compareTo(Inginer altul) {
        return this.nume.compareTo(altul.nume);
    }

    @Override
    public String toString() {
        return String.format("Inginer %s %s, salariu=%.2f, sold=%.2f",
                nume, prenume, salariu, sold);
    }
}