package com.pao.laboratory09.exercise3;

public class ATMThread extends Thread {

    private final int atmId;

    private final CoadaTranzactii coada;

    public ATMThread(
            int atmId,
            CoadaTranzactii coada
    ) {

        this.atmId = atmId;
        this.coada = coada;

        setName("ATM-" + atmId);
    }

    @Override
    public void run() {

        for (int i = 1; i <= 4; i++) {

            int tranzactieId =
                    (atmId - 1) * 4 + i;

            Tranzactie tranzactie =
                    new Tranzactie(
                            tranzactieId,
                            100 * tranzactieId,
                            "2026-05-06"
                    );

            try {

                System.out.println(
                        "[ATM-" + atmId + "] trimite: "
                                + tranzactie
                );

                coada.adauga(tranzactie);

                Thread.sleep(50);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}