package com.pao.laboratory10.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Tranzactie> tranzactii = List.of(

                new Tranzactie(1, 1500, "2024-01-10", "CONT_A", TipTranzactie.CREDIT),
                new Tranzactie(2, 300, "2024-01-15", "CONT_B", TipTranzactie.DEBIT),
                new Tranzactie(3, 700, "2024-01-22", "CONT_A", TipTranzactie.CREDIT),

                new Tranzactie(4, 1200, "2024-02-03", "CONT_C", TipTranzactie.DEBIT),
                new Tranzactie(5, 950, "2024-02-10", "CONT_D", TipTranzactie.CREDIT),
                new Tranzactie(6, 400, "2024-02-14", "CONT_B", TipTranzactie.DEBIT),

                new Tranzactie(7, 2000, "2024-03-01", "CONT_A", TipTranzactie.CREDIT),
                new Tranzactie(8, 250, "2024-03-05", "CONT_E", TipTranzactie.DEBIT),
                new Tranzactie(9, 1750, "2024-03-20", "CONT_F", TipTranzactie.CREDIT),
                new Tranzactie(10, 500, "2024-03-25", "CONT_A", TipTranzactie.DEBIT)
        );

        System.out.println("=== 1. Toate tranzactiile CREDIT ===");

        tranzactii.stream()
                .filter(t -> t.getTip() == TipTranzactie.CREDIT)
                .forEach(System.out::println);


        System.out.println("\n=== 2. Total procesat ===");

        double total = tranzactii.stream()
                .mapToDouble(Tranzactie::getSuma)
                .sum();

        System.out.printf("Total procesat: %.2f RON%n", total);


        System.out.println("\n=== 3. Total pe luna ===");

        Map<String, Double> totalPeLuna =
                tranzactii.stream()
                        .collect(Collectors.groupingBy(
                                t -> t.getData().substring(0, 7),
                                TreeMap::new,
                                Collectors.summingDouble(
                                        Tranzactie::getSuma
                                )
                        ));

        totalPeLuna.forEach((luna, suma) ->
                System.out.printf(
                        "%s: %.2f RON%n",
                        luna,
                        suma
                )
        );


        System.out.println("\n=== 4. Top 3 tranzactii ===");

        tranzactii.stream()
                .sorted(
                        Comparator.comparingDouble(
                                Tranzactie::getSuma
                        ).reversed()
                )
                .limit(3)
                .forEach(System.out::println);


        System.out.println("\n=== 5. Conturi sursa unice ===");

        List<String> conturi =
                tranzactii.stream()
                        .map(Tranzactie::getContSursa)
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println(conturi);


        System.out.println("\n=== 6. Suma medie ===");

        double medie =
                tranzactii.stream()
                        .mapToDouble(Tranzactie::getSuma)
                        .average()
                        .orElse(0.0);

        System.out.printf(
                "Suma medie: %.2f RON%n",
                medie
        );


        System.out.println("\n=== 7. Extras de cont lunar ===");

        Map<String, List<Tranzactie>> extras =
                tranzactii.stream()
                        .collect(Collectors.groupingBy(
                                t -> t.getData().substring(0, 7),
                                TreeMap::new,
                                Collectors.toList()
                        ));

        extras.forEach((luna, lista) -> {

            double suma =
                    lista.stream()
                            .mapToDouble(
                                    Tranzactie::getSuma
                            )
                            .sum();

            System.out.printf(
                    "EXTRAS DE CONT - %s: %d tranzactii, total: %.2f RON%n",
                    luna,
                    lista.size(),
                    suma
            );
        });
    }
}