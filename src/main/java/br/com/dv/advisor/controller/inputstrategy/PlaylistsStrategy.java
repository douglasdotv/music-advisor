package br.com.dv.advisor.controller.inputstrategy;

import br.com.dv.advisor.data.CategoryData;
import br.com.dv.advisor.data.PlaylistData;
import br.com.dv.advisor.model.MusicAdvisorModel;
import br.com.dv.advisor.network.NetworkClient;
import br.com.dv.advisor.view.MusicAdvisorView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
public class PlaylistsStrategy extends AbstractStrategy {

    private final String input;

    public PlaylistsStrategy(MusicAdvisorModel model, MusicAdvisorView view, String input) {
        super(model, view);
        this.input = input;
    }

    @Override
    public void handleInput() {
        String playlistCategory = input.substring(10).toLowerCase();

        JsonObject jo;

        jo = NetworkClient.makeGetRequest("/categories");

        List<CategoryData> categories = new ArrayList<>();
        if (jo == null) {
            view.displayErrorMsg("Error: could not retrieve categories");
        } else {
            JsonArray categoriesArray = jo.get("categories").getAsJsonObject().get("items").getAsJsonArray();
            categories = parseCategoriesfromJson(categoriesArray);
            model.setCategories(categories);
        }

        String categoryId = null;
        for (CategoryData category : categories) {
            if (category.name().toLowerCase().equals(playlistCategory)) {
                categoryId = category.id();
                break;
            }
        }

        if (categoryId == null) {
            view.displayErrorMsg("Unknown category name.");
            return;
        }

        jo = NetworkClient.makeGetRequest("/categories/" + categoryId + "/playlists");

        if (jo == null) {
            view.displayErrorMsg("Error: could not retrieve playlists");
        } else {
            if (jo.has("error")) {
                JsonObject error = jo.get("error").getAsJsonObject();
                String message = error.get("message").getAsString();
                view.displayErrorMsg(message);
            } else {
                JsonArray playlists = jo.get("playlists").getAsJsonObject().get("items").getAsJsonArray();
                List<PlaylistData> playlistsByCategory = parsePlaylistsFromJson(playlists);
                model.setPlaylistsByCategory(playlistsByCategory);
                view.displayPlaylistsByCategory(model.getPlaylistsByCategory());
            }
        }
    }

}
