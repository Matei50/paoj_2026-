//package com.pao.laboratory01.comparators;
//
//import java.util.Arrays;
//
//public class Podcast implements Comparable<Podcast> {
//
//    private String title;
//    private int durationInSeconds;
//
//    public Podcast(String title, int durationInSeconds) {
//        this.title = title;
//        this.durationInSeconds = durationInSeconds;
//    }
//
//    public int getDurationInSeconds() {
//        return durationInSeconds;
//    }
//
//    @Override
//    public String toString() {
//        return "Podcast{title='" + title + "', duration=" + durationInSeconds + "}";
//    }
//
//    // sortare naturala dupa titlu
//    @Override
//    public int compareTo(Podcast other) {
//        return this.title.compareTo(other.title);
//    }
//
//    // Metoda main pentru test
//    public static void main(String[] args) {
//
//        Podcast[] podcasts = {
//                new Podcast("Tech Talk", 2400),
//                new Podcast("Arta Conversatiei", 3600),
//                new Podcast("Mindset", 1800)
//        };
//
//        // 1 sortare dupa titlu
//        Arrays.sort(podcasts);
//        System.out.println("Sortate dupa titlu:");
//        System.out.println(Arrays.toString(podcasts));
//
//        // 2 sortare dupa durata (Comparator)
//        Arrays.sort(podcasts, new PodcastLengthComparator());
//        System.out.println("Sortate dupa durata (crescator):");
//        System.out.println(Arrays.toString(podcasts));
//
//        // 3 sortare descrescatoare cu lambda
//        Arrays.sort(podcasts,
//                (p1, p2) -> Integer.compare(p2.getDurationInSeconds(), p1.getDurationInSeconds())
//        );
//
//        System.out.println("Sortate dupa durata (descrescator, lambda):");
//        System.out.println(Arrays.toString(podcasts));
//    }
//}