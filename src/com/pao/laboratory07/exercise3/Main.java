package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Comanda> comenzi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] t = sc.nextLine().split(" ");

            switch (t[0]) {
                case "STANDARD" -> comenzi.add(
                        new ComandaStandard(t[1], Double.parseDouble(t[2]), t[3])
                );
                case "DISCOUNTED" -> comenzi.add(
                        new ComandaRedusa(t[1], Double.parseDouble(t[2]), Integer.parseInt(t[3]), t[4])
                );
                case "GIFT" -> comenzi.add(
                        new ComandaGratuita(t[1], t[2])
                );
            }
        }


        for (Comanda c : comenzi) {
            System.out.println(c.descriere());
        }
        System.out.println();

        while (true) {
            String line = sc.nextLine().trim();

            if (line.equals("QUIT")) {
                break;
            }

            if (line.equals("STATS")) {
                System.out.println("--- STATS ---");

                Map<String, Double> medii = comenzi.stream()
                        .collect(Collectors.groupingBy(
                                Comanda::tip,
                                LinkedHashMap::new,
                                Collectors.averagingDouble(Comanda::pretFinal)
                        ));

                medii.forEach((k, v) ->
                        System.out.printf("%s: medie = %.2f lei%n", k, v)
                );
            } else if (line.startsWith("FILTER")) {
                double threshold = Double.parseDouble(line.split(" ")[1]);

                System.out.printf("--- FILTER (>= %.2f) ---%n", threshold);

                comenzi.stream()
                        .filter(c -> c.pretFinal() >= threshold)
                        .forEach(c -> System.out.println(c.descriereScurta()));
            } else if (line.equals("SORT")) {
                System.out.println("--- SORT (by client, then by pret) ---");

                comenzi.stream()
                        .sorted(Comparator
                                .comparing(Comanda::getClient)
                                .thenComparing(Comanda::pretFinal))
                        .forEach(c -> System.out.println(c.descriereScurta()));
            } else if (line.equals("SPECIAL")) {
                System.out.println("--- SPECIAL (discount > 15%) ---");

                comenzi.stream()
                        .filter(c -> c instanceof ComandaRedusa)
                        .map(c -> (ComandaRedusa) c)
                        .filter(c -> c.getDiscount() > 15)
                        .forEach(c -> System.out.println(c.descriereScurta()));
            }

            System.out.println();
        }
    }
}