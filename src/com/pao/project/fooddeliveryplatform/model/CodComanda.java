package com.pao.project.fooddeliveryplatform.model;

public final class CodComanda {
    private final String valoare;

    public CodComanda(String valoare){
        this.valoare = valoare;
    }

    public String getValoare() {
        return valoare;
    }

    @Override
    public String toString() {
        return valoare;
    }
}
