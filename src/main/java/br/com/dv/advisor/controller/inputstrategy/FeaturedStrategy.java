package br.com.dv.advisor.controller.inputstrategy;

import br.com.dv.advisor.data.PlaylistData;
import br.com.dv.advisor.model.MusicAdvisorModel;
import br.com.dv.advisor.network.NetworkClient;
import br.com.dv.advisor.view.MusicAdvisorView;
import com.google.gson.*;

import java.util.List;

public class FeaturedStrategy extends AbstractStrategy {

    public FeaturedStrategy(MusicAdvisorModel model, MusicAdvisorView view) {
        super(model, view);
    }

    @Override
    public void handleInput() {
        JsonObject jo = NetworkClient.makeGetRequest("/featured-playlists");

        if (jo == null) {
            view.displayErrorMsg("Error: could not retrieve featured playlists");
        } else {
            JsonArray playlists = jo.get("playlists").getAsJsonObject().get("items").getAsJsonArray();
            List<PlaylistData> featuredPlaylists = parsePlaylistsFromJson(playlists);
            model.setFeaturedPlaylists(featuredPlaylists);
            view.displayFeaturedPlaylists(model.getFeaturedPlaylists());
        }
    }
}
