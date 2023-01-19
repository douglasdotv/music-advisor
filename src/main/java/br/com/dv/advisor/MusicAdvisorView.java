package br.com.dv.advisor;

import java.util.List;

public class MusicAdvisorView {

    public void displayNewReleases(List<String> newReleases) {
        System.out.println("---NEW RELEASES---");
        for (String release : newReleases) {
            System.out.println(release);
        }
    }

    public void displayFeaturedPlaylists(List<String> featuredPlaylists) {
        System.out.println("---FEATURED---");
        for (String playlist : featuredPlaylists) {
            System.out.println(playlist);
        }
    }

    public void displayCategories(List<String> categories) {
        System.out.println("---CATEGORIES---");
        for (String category : categories) {
            System.out.println(category);
        }
    }

    public void displayPlaylistsByCategory(String category, List<String> playlists) {
        System.out.println("---" + category + " PLAYLISTS---");
        for (String playlist : playlists) {
            System.out.println(playlist);
        }
    }

    public void displayInvalidInputMsg() {
        System.out.println("Invalid input!");
    }

    public void exit() {
        System.out.println("---GOODBYE!---");
        System.exit(0);
    }

}
