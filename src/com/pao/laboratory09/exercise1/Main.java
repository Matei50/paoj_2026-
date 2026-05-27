package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;

public class Main {

    private static final String OUTPUT_FILE = "output/lab09_ex1.ser";

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine().trim());

        List<Tranzactie> tranzactii = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            String[] tokens = scanner.nextLine().split(" ");

            int id = Integer.parseInt(tokens[0]);
            double suma = Double.parseDouble(tokens[1]);
            String data = tokens[2];
            String contSursa = tokens[3];
            String contDestinatie = tokens[4];
            TipTranzactie tip = TipTranzactie.valueOf(tokens[5]);

            Tranzactie tranzactie = new Tranzactie(
                    id,
                    suma,
                    data,
                    contSursa,
                    contDestinatie,
                    tip
            );

            tranzactie.setNote("procesat");

            tranzactii.add(tranzactie);
        }

        File folder = new File("output");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(OUTPUT_FILE))) {

            out.writeObject(tranzactii);
        }

        List<Tranzactie> restored;

        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(OUTPUT_FILE))) {

            restored = (List<Tranzactie>) in.readObject();
        }

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine().trim();

            if (line.equals("LIST")) {

                for (Tranzactie t : restored) {
                    System.out.println(t);
                }
            }

            else if (line.startsWith("FILTER")) {

                String prefix = line.split(" ")[1];

                boolean found = false;

                for (Tranzactie t : restored) {

                    if (t.getData().startsWith(prefix)) {
                        System.out.println(t);
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("Niciun rezultat.");
                }
            }

            else if (line.startsWith("NOTE")) {

                int id =
                        Integer.parseInt(line.split(" ")[1]);

                boolean found = false;

                for (Tranzactie t : restored) {

                    if (t.getId() == id) {

                        System.out.println(
                                "NOTE[" + id + "]: "
                                        + t.getNote()
                        );

                        found = true;
                    }
                }

                if (!found) {
                    System.out.println(
                            "NOTE[" + id + "]: not found"
                    );
                }
            }
        }
    }
}