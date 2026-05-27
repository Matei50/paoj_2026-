package com.pao.laboratory09.exercise3;

import java.util.LinkedList;
import java.util.Queue;

public class CoadaTranzactii {

    private final Queue<Tranzactie> coada =
            new LinkedList<>();

    private static final int CAPACITATE = 5;

    public synchronized void adauga(
            Tranzactie tranzactie
    ) throws InterruptedException {

        while (coada.size() >= CAPACITATE) {

            System.out.println(
                    "[" + Thread.currentThread().getName()
                            + "] astept loc..."
            );

            wait();
        }

        coada.add(tranzactie);

        notifyAll();
    }

    public synchronized Tranzactie extrage()
            throws InterruptedException {

        while (coada.isEmpty()) {
            wait();
        }

        Tranzactie t = coada.poll();

        notifyAll();

        return t;
    }

    public synchronized boolean esteGoala() {
        return coada.isEmpty();
    }
}