package com.pao.project.fooddeliveryplatform.model;

import java.util.List;
import java.util.ArrayList;

public class Cos {
    private List<Produs> produse;

    public Cos(){
        this.produse = new ArrayList<>();
    }

    public void adaugaProdus(Produs produs){
        produse.add(produs);
    }

    public void stergeProdus(Produs produs){
        produse.remove(produs);
    }

    public double calculeazaTotal(){
        double total = 0;

        for (Produs produs : produse) {
            total += produs.getPret();
        }
        return total;
    }

    public int getNrProduse() {
        return produse.size();
    }

    public List<Produs> getProduse(){
        return produse;
    }

    @Override
    public String toString() {
        return "Cos: " + getNrProduse() + " produse, total = " + calculeazaTotal() + " lei";
    }
}
