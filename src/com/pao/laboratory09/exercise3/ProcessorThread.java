package com.pao.laboratory09.exercise3;

public class ProcessorThread implements Runnable {

    private final CoadaTranzactii coada;

    volatile boolean activ = true;

    public ProcessorThread(CoadaTranzactii coada) {
        this.coada = coada;
    }

    @Override
    public void run() {

        while (activ || !coada.esteGoala()) {

            try {

                Tranzactie tranzactie =
                        coada.extrage();

                System.out.println(
                        "[Processor] Factura #"
                                + tranzactie.getId()
                                + " - "
                                + tranzactie.getSuma()
                                + " RON | "
                                + tranzactie.getData()
                );

                Thread.sleep(80);

            } catch (InterruptedException e) {
                break;
            }
        }
    }
}