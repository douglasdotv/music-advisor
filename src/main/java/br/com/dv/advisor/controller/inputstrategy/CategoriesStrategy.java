package br.com.dv.advisor.controller.inputstrategy;

import br.com.dv.advisor.data.CategoryData;
import br.com.dv.advisor.model.MusicAdvisorModel;
import br.com.dv.advisor.network.NetworkClient;
import br.com.dv.advisor.view.MusicAdvisorView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class CategoriesStrategy extends AbstractStrategy {

    public CategoriesStrategy(MusicAdvisorModel model, MusicAdvisorView view) {
        super(model, view);
    }

    @Override
    public void handleInput() {
        JsonObject jo = NetworkClient.makeGetRequest("/categories");

        if (jo == null) {
            view.displayErrorMsg("Error: could not retrieve categories");
        } else {
            JsonArray categories = jo.get("categories").getAsJsonObject().get("items").getAsJsonArray();
            List<CategoryData> playlistCategories = parseCategoriesfromJson(categories);
            model.setCategories(playlistCategories);
            view.displayCategories(model.getCategories());
        }
    }

}
