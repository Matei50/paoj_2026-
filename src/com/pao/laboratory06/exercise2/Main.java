package com.pao.laboratory06.exercise2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        List<Colaborator> colaboratori = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String tip = in.next();

            Colaborator colaborator = switch (tip) {
                case "CIM" -> new CIMColaborator();
                case "PFA" -> new PFAColaborator();
                case "SRL" -> new SRLColaborator();
                default -> throw new IllegalArgumentException("Tip necunoscut: " + tip);
            };

            colaborator.citeste(in);
            colaboratori.add(colaborator);
        }

        colaboratori.sort(Comparator.comparingDouble(Colaborator::calculeazaVenitNetAnual).reversed());

        for (Colaborator colaborator : colaboratori) {
            colaborator.afiseaza();
        }

        System.out.println();

        Colaborator maxim = colaboratori.stream()
                .max(Comparator.comparingDouble(Colaborator::calculeazaVenitNetAnual))
                .orElse(null);

        if (maxim != null) {
            System.out.println("Colaborator cu venit net maxim: " + maxim.descriere());
        } else {
            System.out.println("Colaborator cu venit net maxim: -");
        }

        System.out.println();
        System.out.println("Colaboratori persoane juridice:");
        colaboratori.stream()
                .filter(c -> c instanceof PersoanaJuridica)
                .sorted(Comparator.comparingDouble(Colaborator::calculeazaVenitNetAnual).reversed())
                .forEach(Colaborator::afiseaza);

        System.out.println();
        System.out.println("Sume și număr colaboratori pe tip:");

        Map<TipColaborator, Double> sume = new EnumMap<>(TipColaborator.class);
        Map<TipColaborator, Integer> numere = new EnumMap<>(TipColaborator.class);

        for (TipColaborator tip : TipColaborator.values()) {
            sume.put(tip, 0.0);
            numere.put(tip, 0);
        }

        for (Colaborator colaborator : colaboratori) {
            TipColaborator tip = colaborator.getTip();
            sume.put(tip, sume.get(tip) + colaborator.calculeazaVenitNetAnual());
            numere.put(tip, numere.get(tip) + 1);
        }

        for (TipColaborator tip : TipColaborator.values()) {
            System.out.printf("%s: suma = %.2f lei, număr = %d%n",
                    tip, sume.get(tip), numere.get(tip));
        }
    }
}