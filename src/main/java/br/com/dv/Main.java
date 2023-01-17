package br.com.dv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        List<String> newReleases = List.of("Mountains [Sia, Diplo, Labrinth]",
                "Runaway [Lil Peep]",
                "The Greatest Show [Panic! At The Disco]",
                "All Out Life [Slipknot]");
        List<String> featuredPlaylists = List.of("Mellow Morning",
                "Wake Up and Smell the Coffee",
                "Monday Motivation",
                "Songs to Sing in the Shower");
        List<String> categories = List.of("TOP LISTS", "POP", "MOOD", "LATIN");

        Map<String, List<String>> playlistsByCategory = new HashMap<>();
        playlistsByCategory.put(categories.get(2),
                List.of("Walk Like A Badass",
                        "Rage Beats",
                        "Arab Mood Booster",
                        "Sunday Stroll"));

        while (true) {
            input = scanner.nextLine();

            if (input.equals("featured")) {
                System.out.println("---FEATURED---");
                for (String playlist : featuredPlaylists) {
                    System.out.println(playlist);
                }
            } else if (input.equals("new")) {
                System.out.println("---NEW RELEASES---");
                for (String release : newReleases) {
                    System.out.println(release);
                }
            } else if (input.equals("categories")) {
                System.out.println("---CATEGORIES---");
                for (String category : categories) {
                    System.out.println(category);
                }
            } else if (input.startsWith("playlists")) {
                String[] inputArray = input.split("\\s+");
                String category = inputArray[1].toUpperCase();

                if (!categories.contains(category)) {
                    System.out.println("Invalid input");
                } else {
                    System.out.println("---" + category + " PLAYLISTS---");
                    for (String playlist : playlistsByCategory.get(category)) {
                        System.out.println(playlist);
                    }
                }
            } else if (input.equals("exit")) {
                System.out.println("---GOODBYE!---");
                System.exit(0);
            } else {
                System.out.println("Invalid input");
            }
        }
    }

}
