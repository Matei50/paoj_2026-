package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS {
    private double sold;
    private boolean autentificat;
    private final List<String> smsTrimise;

    public PersoanaJuridica(String nume, String prenume, String telefon, double sold) {
        super(nume, prenume, telefon);
        if (sold < 0) {
            throw new IllegalArgumentException("Soldul nu poate fi negativ.");
        }
        this.sold = sold;
        this.autentificat = false;
        this.smsTrimise = new ArrayList<>();
    }

    public List<String> getSmsTrimise() {
        return smsTrimise;
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
            throw new IllegalStateException("Entitatea nu este autentificata.");
        }
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0) {
            throw new IllegalArgumentException("Suma trebuie sa fie pozitiva.");
        }
        if (!autentificat) {
            throw new IllegalStateException("Entitatea nu este autentificata.");
        }
        if (suma > sold) {
            return false;
        }
        sold -= suma;
        return true;
    }

    @Override
    public boolean trimiteSMS(String mesaj) {
        if (mesaj == null || mesaj.isBlank()) {
            return false;
        }
        if (telefon == null || telefon.isBlank()) {
            return false;
        }
        smsTrimise.add(mesaj);
        return true;
    }

    @Override
    public String toString() {
        return String.format("PersoanaJuridica %s %s, sold=%.2f, smsTrimise=%s",
                nume, prenume, sold, smsTrimise);
    }
}