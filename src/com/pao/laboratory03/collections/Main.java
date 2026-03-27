package com.pao.laboratory03.collections;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // === PARTEA A — HashMap ===
        System.out.println("=== PARTEA A: HashMap — frecvența cuvintelor ===");

        String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};

        Map<String, Integer> freqMap = new HashMap<>();

        // contorizare
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // afișare map
        System.out.println("Frecvență: " + freqMap);

        // verificare cheie
        System.out.println("Conține 'rust'? " + freqMap.containsKey("rust"));

        // chei
        System.out.println("Chei: " + freqMap.keySet());

        // valori
        System.out.println("Valori: " + freqMap.values());

        // entrySet
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


        // === PARTEA B — TreeMap ===
        System.out.println("\n=== PARTEA B: TreeMap — sortare automată ===");

        TreeMap<String, Integer> treeMap = new TreeMap<>(freqMap);

        System.out.println("Sortat: " + treeMap);

        System.out.println("Prima cheie: " + treeMap.firstKey());
        System.out.println("Ultima cheie: " + treeMap.lastKey());


        // === PARTEA C — Map cu obiecte ===
        System.out.println("\n=== PARTEA C: Map cu obiecte ===");

        Map<String, List<String>> materii = new HashMap<>();

        // adăugare date
        materii.put("PAOJ", new ArrayList<>(Arrays.asList("Ana", "Mihai", "Ion")));
        materii.put("BD", new ArrayList<>(Arrays.asList("Ana", "Elena")));

        // afișare studenți PAOJ
        System.out.println("Studenți la PAOJ: " + materii.get("PAOJ"));

        // adăugare student la BD
        materii.get("BD").add("George");

        // afișare actualizată
        System.out.println("Studenți la BD (actualizat): " + materii.get("BD"));
    }
}