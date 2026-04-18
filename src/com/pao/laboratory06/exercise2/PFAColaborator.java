package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class PFAColaborator extends Colaborator implements PersoanaFizica {
    private static final double SALARIU_MINIM_BRUT_LUNAR = 4050.0;
    private static final double SALARIU_MINIM_BRUT_ANUAL = SALARIU_MINIM_BRUT_LUNAR * 12.0;

    private double cheltuieliLunare;

    public PFAColaborator() {
    }

    public PFAColaborator(String nume, String prenume, double venitBrutLunar, double cheltuieliLunare) {
        super(nume, prenume, venitBrutLunar);
        this.cheltuieliLunare = cheltuieliLunare;
    }

    @Override
    public void citeste(Scanner in) {
        nume = in.next();
        prenume = in.next();
        venitBrutLunar = in.nextDouble();
        cheltuieliLunare = in.nextDouble();
    }

    @Override
    public String tipContract() {
        return "PFA";
    }

    @Override
    public TipColaborator getTip() {
        return TipColaborator.PFA;
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double venitNet = (venitBrutLunar - cheltuieliLunare) * 12.0;

        double impozit = 0.10 * venitNet;

        double prag6 = 6.0 * SALARIU_MINIM_BRUT_ANUAL;
        double prag12 = 12.0 * SALARIU_MINIM_BRUT_ANUAL;
        double prag24 = 24.0 * SALARIU_MINIM_BRUT_ANUAL;
        double prag72 = 72.0 * SALARIU_MINIM_BRUT_ANUAL;

        double cass;
        if (venitNet < prag6) {
            cass = 0.10 * prag6;
        } else if (venitNet <= prag72) {
            cass = 0.10 * venitNet;
        } else {
            cass = 0.10 * prag72;
        }

        double cas;
        if (venitNet < prag12) {
            cas = 0.0;
        } else if (venitNet <= prag24) {
            cas = 0.25 * prag12;
        } else {
            cas = 0.25 * prag24;
        }

        return venitNet - impozit - cass - cas;
    }
}