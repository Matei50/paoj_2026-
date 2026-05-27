package com.pao.laboratory10.exercise1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Tranzactie> coada = new LinkedList<>();

        while (scanner.hasNext()) {
            String comanda = scanner.next();

            switch (comanda) {
                case "ENQUEUE": {
                    Tranzactie t = citesteTranzactie(scanner);
                    coada.addLast(t);
                    break;
                }

                case "DEQUEUE": {
                    if (coada.isEmpty()) {
                        System.out.println("Coada goala.");
                    } else {
                        Tranzactie t = coada.removeFirst();
                        System.out.println("Procesat: " + t);
                    }
                    break;
                }

                case "PUSH": {
                    Tranzactie t = citesteTranzactie(scanner);
                    coada.addFirst(t);
                    break;
                }

                case "POP": {
                    if (coada.isEmpty()) {
                        System.out.println("Coada goala.");
                    } else {
                        Tranzactie t = coada.removeFirst();
                        System.out.println("Extras: " + t);
                    }
                    break;
                }

                case "REMOVE_DEBIT": {
                    int count = 0;

                    Iterator<Tranzactie> iterator = coada.iterator();

                    while (iterator.hasNext()) {
                        Tranzactie t = iterator.next();

                        if (t.getTip() == TipTranzactie.DEBIT) {
                            iterator.remove();
                            count++;
                        }
                    }

                    System.out.println("Eliminat " + count + " tranzactii DEBIT.");
                    break;
                }

                case "REMOVE_BELOW": {
                    double threshold = scanner.nextDouble();
                    int count = 0;

                    Iterator<Tranzactie> iterator = coada.iterator();

                    while (iterator.hasNext()) {
                        Tranzactie t = iterator.next();

                        if (t.getSuma() < threshold) {
                            iterator.remove();
                            count++;
                        }
                    }

                    System.out.printf("Eliminat %d tranzactii sub %.2f RON.%n", count, threshold);
                    break;
                }

                case "PRINT": {
                    for (Tranzactie t : coada) {
                        System.out.println(t);
                    }
                    break;
                }

                case "SIZE": {
                    System.out.println("Dimensiune coada: " + coada.size());
                    break;
                }

                default:
                    break;
            }
        }

        scanner.close();
    }

    private static Tranzactie citesteTranzactie(Scanner scanner) {
        int id = scanner.nextInt();
        double suma = scanner.nextDouble();
        String data = scanner.next();
        TipTranzactie tip = TipTranzactie.valueOf(scanner.next());

        return new Tranzactie(id, suma, data, tip);
    }
}