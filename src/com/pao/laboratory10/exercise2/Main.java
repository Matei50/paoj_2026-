package com.pao.laboratory10.exercise2;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n =
                Integer.parseInt(
                        scanner.nextLine().trim()
                );

        List<Tranzactie> lista =
                new ArrayList<>();

        for (int i = 0; i < n; i++) {

            String[] tokens =
                    scanner.nextLine().split(" ");

            int id =
                    Integer.parseInt(tokens[0]);

            double suma =
                    Double.parseDouble(tokens[1]);

            String data = tokens[2];

            TipTranzactie tip =
                    TipTranzactie.valueOf(tokens[3]);

            lista.add(
                    new Tranzactie(
                            id,
                            suma,
                            data,
                            tip
                    )
            );
        }

        while (scanner.hasNextLine()) {

            String line =
                    scanner.nextLine().trim();

            if (line.equals("UNIQUE_IDS")) {

                LinkedHashSet<Integer> ids =
                        new LinkedHashSet<>();

                for (Tranzactie t : lista) {
                    ids.add(t.getId());
                }

                System.out.println(
                        "IDs unice (" +
                                ids.size() +
                                "): " +
                                ids
                );
            }

            else if (
                    line.equals("MONTHLY_REPORT")
            ) {

                TreeMap<String, double[]> raport =
                        new TreeMap<>();

                for (Tranzactie t : lista) {

                    String luna =
                            t.getData()
                                    .substring(0, 7);

                    raport.putIfAbsent(
                            luna,
                            new double[2]
                    );

                    if (
                            t.getTip()
                                    == TipTranzactie.CREDIT
                    ) {

                        raport.get(luna)[0]
                                += t.getSuma();

                    } else {

                        raport.get(luna)[1]
                                += t.getSuma();
                    }
                }

                for (
                        Map.Entry<String, double[]> e
                        : raport.entrySet()
                ) {

                    System.out.printf(
                            "%s: CREDIT %.2f RON, DEBIT %.2f RON%n",
                            e.getKey(),
                            e.getValue()[0],
                            e.getValue()[1]
                    );
                }
            }

            else if (line.startsWith("TOP")) {

                int top =
                        Integer.parseInt(
                                line.split(" ")[1]
                        );

                List<Tranzactie> copie =
                        new ArrayList<>(lista);

                copie.sort(
                        Comparator.comparingDouble(
                                Tranzactie::getSuma
                        ).reversed()
                );

                System.out.println(
                        "Top " + top + ":"
                );

                for (
                        int i = 0;
                        i < Math.min(top, copie.size());
                        i++
                ) {

                    System.out.println(
                            copie.get(i)
                    );
                }
            }

            else if (
                    line.equals("SORT_ASC")
            ) {

                Collections.sort(
                        lista,
                        Comparator.comparingDouble(
                                Tranzactie::getSuma
                        )
                );

                for (Tranzactie t : lista) {
                    System.out.println(t);
                }
            }

            else if (
                    line.equals("SORT_DESC")
            ) {

                Collections.sort(
                        lista,
                        Comparator.comparingDouble(
                                Tranzactie::getSuma
                        ).reversed()
                );

                for (Tranzactie t : lista) {
                    System.out.println(t);
                }
            }

            else if (
                    line.equals("REVERSE")
            ) {

                Collections.reverse(lista);

                for (Tranzactie t : lista) {
                    System.out.println(t);
                }
            }

            else if (
                    line.equals("MIN_MAX")
            ) {

                Tranzactie min =
                        Collections.min(
                                lista,
                                Comparator.comparingDouble(
                                        Tranzactie::getSuma
                                )
                        );

                Tranzactie max =
                        Collections.max(
                                lista,
                                Comparator.comparingDouble(
                                        Tranzactie::getSuma
                                )
                        );

                System.out.println(
                        "MIN: " + min
                );

                System.out.println(
                        "MAX: " + max
                );
            }

            else if (
                    line.equals("CME_DEMO")
            ) {

                try {

                    for (Tranzactie t : lista) {
                        lista.remove(t);
                    }

                } catch (
                        ConcurrentModificationException e
                ) {

                    System.out.println(
                            "ConcurrentModificationException prins: modificare in iteratie detectata."
                    );
                }
            }
        }
    }
}