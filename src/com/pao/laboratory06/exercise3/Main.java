package com.pao.laboratory06.exercise3;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Inginer[] ingineri = {
                new Inginer("Ana", "Popescu", "0711", 9000, 5000),
                new Inginer("Mihai", "Ionescu", "0722", 12000, 7000),
                new Inginer("Dan", "Georgescu", "0733", 8000, 3000)
        };

        System.out.println("=== Sortare naturala ===");

        Arrays.sort(ingineri);

        for (Inginer i : ingineri) {
            System.out.println(i);
        }

        System.out.println("\n=== Sortare dupa salariu ===");

        Arrays.sort(ingineri, new ComparatorInginerSalariu());

        for (Inginer i : ingineri) {
            System.out.println(i);
        }

        System.out.println("\n=== PlataOnline reference ===");

        PlataOnline p = ingineri[0];

        p.autentificare("user", "pass");

        System.out.println("Sold: " + p.consultareSold());

        System.out.println("Plata 1000: " + p.efectuarePlata(1000));

        System.out.println("\n=== Persoana juridica + SMS ===");

        PlataOnlineSMS firma =
                new PersoanaJuridica(
                        "FirmaX",
                        "SRL",
                        "0744",
                        20000
                );

        firma.autentificare("firma", "1234");

        System.out.println("SMS trimis: " +
                firma.trimiteSMS("Factura emisa"));

        PersoanaJuridica pj =
                (PersoanaJuridica) firma;

        System.out.println("SMS-uri:");
        System.out.println(pj.getSmsTrimise());

        System.out.println("\n=== Fara telefon ===");

        PersoanaJuridica faraTelefon =
                new PersoanaJuridica(
                        "NoPhone",
                        "SRL",
                        "",
                        1000
                );

        System.out.println(
                faraTelefon.trimiteSMS("Test")
        );

        System.out.println("\n=== Enum ===");

        System.out.println(
                "TVA = " +
                        ConstanteFinanciare.TVA.getValoare()
        );

        System.out.println("\n=== Exceptii ===");

        try {
            p.autentificare("", "");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}