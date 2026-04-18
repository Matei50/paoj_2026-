package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class CIMColaborator extends Colaborator implements PersoanaFizica {
    private boolean bonus;

    public CIMColaborator() {
    }

    public CIMColaborator(String nume, String prenume, double venitBrutLunar, boolean bonus) {
        super(nume, prenume, venitBrutLunar);
        this.bonus = bonus;
    }

    @Override
    public void citeste(Scanner in) {
        nume = in.next();
        prenume = in.next();
        venitBrutLunar = in.nextDouble();

        bonus = false;
        if (in.hasNext()) {
            String token = in.next();
            if ("DA".equals(token)) {
                bonus = true;
            } else if ("NU".equals(token)) {
                bonus = false;
            }
        }
    }

    @Override
    public String tipContract() {
        return "CIM";
    }

    @Override
    public boolean areBonus() {
        return bonus;
    }

    @Override
    public TipColaborator getTip() {
        return TipColaborator.CIM;
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double venitNet = venitBrutLunar * 12 * 0.55;
        if (bonus) {
            venitNet *= 1.10;
        }
        return venitNet;
    }
}