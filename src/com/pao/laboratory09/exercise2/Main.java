package com.pao.laboratory09.exercise2;

import com.pao.laboratory09.exercise1.TipTranzactie;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class Main {

    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";

    private static final int RECORD_SIZE = 32;

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine().trim());

        File folder = new File("output");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (DataOutputStream out =
                     new DataOutputStream(
                             new FileOutputStream(OUTPUT_FILE))) {

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

                byte[] idBytes =
                        ByteBuffer.allocate(4)
                                .order(ByteOrder.LITTLE_ENDIAN)
                                .putInt(id)
                                .array();

                out.write(idBytes);

                byte[] sumaBytes =
                        ByteBuffer.allocate(8)
                                .order(ByteOrder.LITTLE_ENDIAN)
                                .putDouble(suma)
                                .array();

                out.write(sumaBytes);

                byte[] dataBytes =
                        String.format("%-10s", data)
                                .getBytes();

                out.write(dataBytes);

                out.writeByte(
                        tip == TipTranzactie.CREDIT ? 0 : 1
                );

                out.writeByte(0);

                out.write(new byte[8]);
            }
        }

        RandomAccessFile raf =
                new RandomAccessFile(OUTPUT_FILE, "rw");

        while (scanner.hasNextLine()) {

            String line =
                    scanner.nextLine().trim();

            if (line.startsWith("READ")) {

                int idx =
                        Integer.parseInt(
                                line.split(" ")[1]
                        );

                printRecord(raf, idx);
            }

            else if (line.startsWith("UPDATE")) {

                String[] parts =
                        line.split(" ");

                int idx =
                        Integer.parseInt(parts[1]);

                String status = parts[2];

                byte statusByte = switch (status) {
                    case "PROCESSED" -> 1;
                    case "REJECTED" -> 2;
                    default -> 0;
                };

                raf.seek((long) idx * RECORD_SIZE + 23);

                raf.writeByte(statusByte);

                System.out.println(
                        "Updated [" + idx + "]: " + status
                );
            }

            else if (line.equals("PRINT_ALL")) {

                long total =
                        raf.length() / RECORD_SIZE;

                for (int i = 0; i < total; i++) {
                    printRecord(raf, i);
                }
            }
        }

        raf.close();
    }

    private static void printRecord(
            RandomAccessFile raf,
            int idx
    ) throws IOException {

        raf.seek((long) idx * RECORD_SIZE);

        byte[] record =
                new byte[RECORD_SIZE];

        raf.readFully(record);

        ByteBuffer bb =
                ByteBuffer.wrap(record)
                        .order(ByteOrder.LITTLE_ENDIAN);

        int id = bb.getInt();

        double suma = bb.getDouble();

        byte[] dataBytes = new byte[10];

        bb.get(dataBytes);

        String data =
                new String(dataBytes).trim();

        byte tipByte = bb.get();

        byte statusByte = bb.get();

        String tip =
                tipByte == 0
                        ? "CREDIT"
                        : "DEBIT";

        String status = switch (statusByte) {
            case 1 -> "PROCESSED";
            case 2 -> "REJECTED";
            default -> "PENDING";
        };

        System.out.printf(
                "[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s%n",
                idx,
                id,
                data,
                tip,
                suma,
                status
        );
    }
}