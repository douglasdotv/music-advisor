package br.com.dv.advisor.controller.inputstrategy;

import br.com.dv.advisor.data.AlbumData;
import br.com.dv.advisor.model.MusicAdvisorModel;
import br.com.dv.advisor.network.NetworkClient;
import br.com.dv.advisor.view.MusicAdvisorView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class NewStrategy extends AbstractStrategy {


    public NewStrategy(MusicAdvisorModel model, MusicAdvisorView view) {
        super(model, view);
    }

    @Override
    public void handleInput() {
        JsonObject jo = NetworkClient.makeGetRequest("/new-releases");

        if (jo == null) {
            view.displayErrorMsg("Error: could not retrieve new releases");
        } else {
            JsonArray albums = jo.get("albums").getAsJsonObject().get("items").getAsJsonArray();
            List<AlbumData> newReleases = parseAlbumsFromJson(albums);
            model.setNewReleases(newReleases);
            view.displayNewReleases(model.getNewReleases());
        }
    }

}
