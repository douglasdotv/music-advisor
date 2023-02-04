package br.com.dv.advisor.controller.inputstrategy;

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

public abstract class AbstractStrategy implements Strategy {

    protected final MusicAdvisorModel model;
    protected final MusicAdvisorView view;

    public AbstractStrategy(MusicAdvisorModel model, MusicAdvisorView view) {
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

}
