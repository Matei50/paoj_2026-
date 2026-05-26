package com.pao.laboratory04.enums;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Toate prioritatile ===");

        for (Priority p : Priority.values()) {
            System.out.println(
                    p.getEmoji() + " " + p.name() +
                            " (level=" + p.getLevel() +
                            ", color=" + p.getColor() + ")"
            );
        }

        System.out.println("\n=== Switch pe prioritate ===");

        Priority p = Priority.HIGH;

        switch (p) {
            case LOW:
                System.out.println("Prioritate mica");
                break;
            case MEDIUM:
                System.out.println("Prioritate medie");
                break;
            case HIGH:
                System.out.println("Atentie! Prioritate ridicata!");
                break;
            case CRITICAL:
                System.out.println("URGENT!");
                break;
        }

        System.out.println("\n=== valueOf ===");

        Priority fromString = Priority.valueOf("HIGH");
        System.out.println("Priority.valueOf(\"HIGH\") = " + fromString);

        System.out.println("\n=== Comparare enum ===");

        System.out.println("HIGH == HIGH? " + (Priority.HIGH == Priority.HIGH));
        System.out.println("HIGH == LOW? " + (Priority.HIGH == Priority.LOW));

        System.out.println("\n=== name() si ordinal() ===");

        for (Priority pr : Priority.values()) {
            System.out.println(
                    pr + ": name=" + pr.name() +
                            ", ordinal=" + pr.ordinal()
            );
        }
    }
}