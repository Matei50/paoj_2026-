package com.pao.laboratory03.enums;

public class Main {
    public static void main(String[] args) {

        // === a) values() ===
        System.out.println("=== Toate prioritățile ===");
        for (Priority p : Priority.values()) {
            System.out.println(p.getEmoji() + " " + p.name() +
                    " (level=" + p.getLevel() + ", color=" + p.getColor() + ")");
        }

        // === b) switch ===
        System.out.println("\n=== Switch pe prioritate ===");

        Priority priority = Priority.HIGH;

        switch (priority) {
            case LOW:
                System.out.println("Prioritate scăzută.");
                break;
            case MEDIUM:
                System.out.println("Prioritate medie.");
                break;
            case HIGH:
                System.out.println("⚠️ Atenție! Prioritate ridicată!");
                break;
            case CRITICAL:
                System.out.println("🔥 CRITICAL! Intervenție urgentă!");
                break;
        }

        // === c) valueOf ===
        System.out.println("\n=== valueOf ===");
        Priority pFromString = Priority.valueOf("HIGH");
        System.out.println("Priority.valueOf(\"HIGH\") = " + pFromString);

        // === d) comparare ===
        System.out.println("\n=== Comparare enum ===");
        System.out.println("HIGH == HIGH? " + (Priority.HIGH == Priority.HIGH));
        System.out.println("HIGH == LOW? " + (Priority.HIGH == Priority.LOW));

        // === e) name() și ordinal() ===
        System.out.println("\n=== name() și ordinal() ===");
        for (Priority p : Priority.values()) {
            System.out.println(p + ": name=" + p.name() + ", ordinal=" + p.ordinal());
        }
    }
}