package br.com.dv.advisor.controller.inputstrategy;

import br.com.dv.advisor.config.MusicAdvisorConfig;
import br.com.dv.advisor.controller.MusicAdvisorController;
import br.com.dv.advisor.data.AlbumData;
import br.com.dv.advisor.data.CategoryData;
import br.com.dv.advisor.data.PlaylistData;
import br.com.dv.advisor.model.*;
import br.com.dv.advisor.view.MusicAdvisorView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractStrategy implements Strategy {

    protected final MusicAdvisorController controller;
    protected final MusicAdvisorModel model;
    protected final MusicAdvisorView view;
    protected final Scanner scanner = new Scanner(System.in);
    protected String nextInput;
    protected int currentPage;
    protected int totalPages;
    protected int itemsPerPage = MusicAdvisorConfig.ITEMS_PER_PAGE;

    public AbstractStrategy(MusicAdvisorModel model, MusicAdvisorView view) {
        this.controller = MusicAdvisorController.getInstance();
        this.model = model;
        this.view = view;
    }

    protected List<PlaylistData> parsePlaylistsFromJson(JsonArray playlistsArray) {
        List<PlaylistData> playlists = new ArrayList<>();

        for (int i = 0; i < playlistsArray.size(); ++i) {
            JsonElement jsonElement = playlistsArray.get(i);

            if (jsonElement.isJsonNull()) {
                continue;
            }

            JsonObject playlist = jsonElement.getAsJsonObject();

            String name = playlist.get("name").getAsString();
            String url = playlist.get("external_urls").getAsJsonObject().get("spotify").getAsString();

            playlists.add(new Playlist(name, url));
        }

        return playlists;
    }

    protected List<CategoryData> parseCategoriesfromJson(JsonArray categoriesArray) {
        List<CategoryData> categories = new ArrayList<>();

        for (int i = 0; i < categoriesArray.size(); ++i) {
            JsonElement jsonElement = categoriesArray.get(i);
            JsonObject category = jsonElement.getAsJsonObject();

            String name = category.get("name").getAsString();
            String id = category.get("id").getAsString();

            categories.add(new Category(name, id));
        }

        return categories;
    }

    protected List<AlbumData> parseAlbumsFromJson(JsonArray albumsArray) {
        List<AlbumData> albums = new ArrayList<>();

        for (int i = 0; i < albumsArray.size(); ++i) {
            JsonElement jsonElement = albumsArray.get(i);
            JsonObject album = jsonElement.getAsJsonObject();

            String name = album.get("name").getAsString();
            String url = album.get("external_urls").getAsJsonObject().get("spotify").getAsString();

            List<Artist> artists = new ArrayList<>();
            JsonArray artistsArray = album.get("artists").getAsJsonArray();
            for (int j = 0; j < artistsArray.size(); ++j) {
                String artistName = artistsArray.get(j).getAsJsonObject().get("name").getAsString();
                artists.add(new Artist(artistName));
            }

            albums.add(new Album(name, url, artists));
        }

        return albums;
    }

    protected <T> void calculateTotalPages(List<T> list) {
        currentPage = 0;
        totalPages = (int) Math.ceil(list.size() / (double) itemsPerPage);
    }

    protected int getStartIndex() {
        return currentPage * itemsPerPage;
    }

    protected <T> int getEndIndex(List<T> list) {
        return Math.min((currentPage + 1) * itemsPerPage, list.size());
    }

}
