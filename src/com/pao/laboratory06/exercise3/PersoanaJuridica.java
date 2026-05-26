package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana
        implements PlataOnlineSMS {

    private final List<String> smsTrimise;

    private double sold;

    public PersoanaJuridica(String nume,
                            String prenume,
                            String telefon,
                            double sold) {

        super(nume, prenume, telefon);

        this.sold = sold;
        this.smsTrimise = new ArrayList<>();
    }

    @Override
    public void autentificare(String user, String parola) {

        if (user == null || user.isBlank()
                || parola == null || parola.isBlank()) {

            throw new IllegalArgumentException("Date autentificare invalide");
        }

        System.out.println(nume + " autentificat");
    }

    @Override
    public double consultareSold() {
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {

        if (suma <= 0 || suma > sold) {
            return false;
        }

        sold -= suma;
        return true;
    }

    @Override
    public boolean trimiteSMS(String mesaj) {

        if (telefon == null || telefon.isBlank()) {
            return false;
        }

        if (mesaj == null || mesaj.isBlank()) {
            return false;
        }

        smsTrimise.add(mesaj);

        return true;
    }

    public List<String> getSmsTrimise() {
        return smsTrimise;
    }
}