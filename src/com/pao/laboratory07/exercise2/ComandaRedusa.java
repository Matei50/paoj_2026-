package com.pao.laboratory07.exercise2;

public final class ComandaRedusa extends Comanda {

    private double pret;
    private int discount;

    public ComandaRedusa(String nume, double pret, int discount) {
        super(nume);
        this.pret = pret;
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public double pretFinal() {
        return pret * (1 - discount / 100.0);
    }

    @Override
    public String descriere() {
        return String.format("DISCOUNTED: %s, pret: %.2f lei (-%d%%) [%s] - client: %s",
                nume, pretFinal(), discount, stare, client);
    }
}