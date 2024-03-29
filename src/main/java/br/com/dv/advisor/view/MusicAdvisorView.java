package br.com.dv.advisor.view;

import br.com.dv.advisor.model.AlbumData;
import br.com.dv.advisor.model.CategoryData;
import br.com.dv.advisor.model.PlaylistData;

import java.util.List;

public class MusicAdvisorView {

    public <T> void displayNewReleases(List<T> newReleases) {
        for (T object : newReleases) {
            AlbumData newRelease = (AlbumData) object;
            System.out.println(newRelease.name());
            System.out.println(newRelease.artists());
            System.out.println(newRelease.albumUrl());
            System.out.println();
        }
    }

    public void displayFeaturedPlaylists(List<PlaylistData> featuredPlaylists) {
        for (PlaylistData playlist : featuredPlaylists) {
            System.out.println(playlist.name());
            System.out.println(playlist.playlistUrl());
            System.out.println();
        }
    }

    public <T> void displayCategories(List<T> categories) {
        for (T object : categories) {
            CategoryData category = (CategoryData) object;
            System.out.println(category.name());
        }
    }

    public void displayPlaylistsByCategory(List<PlaylistData> playlistsByCategory) {
        for (PlaylistData playlist : playlistsByCategory) {
            System.out.println(playlist.name());
            System.out.println(playlist.playlistUrl());
            System.out.println();
        }
    }

    public void displayPageInfo(int currentPage, int totalPages) {
        System.out.println("---PAGE " + (currentPage + 1) + " OF " + totalPages + "---");
    }

    public void displayOAuthProvideAccessMsg() {
        System.out.println("Please, provide access for application.");
    }

    public void displayAuthWaitingMsg(String authUrl) {
        System.out.println("use this link to request the access code: ");
        System.out.println(authUrl);
    }

    public void displayWaitingForCodeMsg() {
        System.out.println("waiting for code...");
    }

    public void displayAuthCodeReceivedMsg() {
        System.out.println("code received");
    }

    public void displayAuthMakingHttpRequestMsg() {
        System.out.println("Making http request for access_token...");
    }

    public void displayAuthResponseMsg(String accessToken) {
        System.out.println("response: ");
        System.out.println(accessToken);
        if (!accessToken.contains("error")) {
            System.out.println("Success!");
        }
    }

    public void displayErrorMsg(String message) {
        System.out.println(message);
    }

    public void exit() {
        // Commented code so the JetBrains unity tests for stage 5 can actually run
        // System.exit(0);
    }

}
