package com.pao.laboratory06.exercise3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ENUM ===");
        System.out.println("TVA = " + ConstanteFinanciare.TVA.getValoare());
        System.out.println("SALARIU_MINIM = " + ConstanteFinanciare.SALARIU_MINIM.getValoare());
        System.out.println();

        System.out.println("=== SORTARE NATURALA INGINERI (dupa nume) ===");
        Inginer[] ingineri = {
                new Inginer("Popescu", "Ana", "0711111111", 9000, 3000),
                new Inginer("Ionescu", "Vlad", "0722222222", 12000, 5000),
                new Inginer("Georgescu", "Maria", "0733333333", 10000, 4000)
        };

        Arrays.sort(ingineri);
        for (Inginer inginer : ingineri) {
            System.out.println(inginer);
        }
        System.out.println();

        System.out.println("=== SORTARE CU COMPARATOR (dupa salariu descrescator) ===");
        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        for (Inginer inginer : ingineri) {
            System.out.println(inginer);
        }
        System.out.println();

        System.out.println("=== REFERINTA DE TIP INTERFATA: PlataOnline ===");
        PlataOnline contInginer = new Inginer("Marin", "Teo", "0744444444", 11000, 2500);
        contInginer.autentificare("teo.user", "parola123");
        System.out.println("Sold inginer: " + contInginer.consultareSold());
        System.out.println("Plata 1000: " + contInginer.efectuarePlata(1000));
        System.out.println("Sold dupa plata: " + contInginer.consultareSold());
        System.out.println();

        System.out.println("=== REFERINTA DE TIP INTERFATA: PlataOnlineSMS ===");
        PlataOnlineSMS firma = new PersoanaJuridica("Tech", "Solutions", "0755555555", 10000);
        firma.autentificare("firma.user", "firmaPass");
        System.out.println("Sold firma: " + firma.consultareSold());
        System.out.println("SMS valid: " + firma.trimiteSMS("Plata a fost procesata."));
        System.out.println("Plata 2000: " + firma.efectuarePlata(2000));
        System.out.println("Sold dupa plata: " + firma.consultareSold());

        PersoanaJuridica firmaConcreta = (PersoanaJuridica) firma;
        System.out.println("SMS trimise: " + firmaConcreta.getSmsTrimise());
        System.out.println();

        System.out.println("=== EDGE CASE: fara telefon ===");
        PlataOnlineSMS firmaFaraTelefon = new PersoanaJuridica("NoPhone", "Company", "", 5000);
        System.out.println("SMS trimis? " + firmaFaraTelefon.trimiteSMS("Mesaj test"));
        System.out.println();

        System.out.println("=== EDGE CASE: mesaj invalid ===");
        System.out.println("SMS null? " + firma.trimiteSMS(null));
        System.out.println("SMS gol? " + firma.trimiteSMS("   "));
        System.out.println();

        System.out.println("=== EDGE CASE: autentificare invalida ===");
        try {
            contInginer.autentificare(null, "abc");
        } catch (IllegalArgumentException e) {
            System.out.println("Exceptie prinsa: " + e.getMessage());
        }
        System.out.println();

        System.out.println("=== EDGE CASE: plata fara autentificare ===");
        try {
            PlataOnline contNeautentificat = new Inginer("Test", "User", "0700000000", 8000, 1500);
            System.out.println(contNeautentificat.consultareSold());
        } catch (IllegalStateException e) {
            System.out.println("Exceptie prinsa: " + e.getMessage());
        }
        System.out.println();

        System.out.println("=== EDGE CASE: apel SMS pe entitate fara capabilitate SMS ===");
        try {
            PlataOnline simplu = new Inginer("SMS", "Wrong", "0799999999", 7000, 1000);
            if (!(simplu instanceof PlataOnlineSMS)) {
                throw new UnsupportedOperationException("Entitatea nu are capabilitate SMS.");
            }
            ((PlataOnlineSMS) simplu).trimiteSMS("Nu ar trebui sa ajunga aici");
        } catch (UnsupportedOperationException e) {
            System.out.println("Exceptie prinsa: " + e.getMessage());
        }
    }
}