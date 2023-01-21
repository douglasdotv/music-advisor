package br.com.dv.advisor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicAdvisorModel {

    private final List<String> newReleases;
    private final List<String> featuredPlaylists;
    private final List<String> categories;
    private final Map<String, List<String>> playlistsByCategory;
    private final String authorizationUrl;

    public MusicAdvisorModel() {
        newReleases = List.of(
                "Mountains [Sia, Diplo, Labrinth]",
                "Runaway [Lil Peep]",
                "The Greatest Show [Panic! At The Disco]",
                "All Out Life [Slipknot]");

        featuredPlaylists = List.of(
                "Mellow Morning",
                "Wake Up and Smell the Coffee",
                "Monday Motivation",
                "Songs to Sing in the Shower");

        categories = List.of("TOP LISTS", "POP", "MOOD", "LATIN");

        playlistsByCategory = new HashMap<>();

        addToPlaylistsByCategory(categories.get(2), List.of(
                "Walk Like A Badass",
                "Rage Beats",
                "Arab Mood Booster",
                "Sunday Stroll"));

        authorizationUrl = "https://accounts.spotify.com/authorize?client_id=a19ee7dbfda443b2a8150c9101bfd645&redirect_uri=http://localhost:8080&response_type=code";
    }

    public List<String> getNewReleases() {
        return newReleases;
    }

    public List<String> getFeaturedPlaylists() {
        return featuredPlaylists;
    }

    public List<String> getCategories() {
        return categories;
    }

    public Map<String, List<String>> getPlaylistsByCategory() {
        return playlistsByCategory;
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void addToPlaylistsByCategory(String category, List<String> playlist) {
        playlistsByCategory.put(category, playlist);
    }

}
