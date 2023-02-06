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

            calculateTotalPages(featuredPlaylists);

            int startIndex = getStartIndex();
            int endIndex = getEndIndex(featuredPlaylists);

            view.displayFeaturedPlaylists(featuredPlaylists.subList(startIndex, endIndex));
            view.displayPageInfo(currentPage, totalPages);

            while (scanner.hasNext()) {
                nextInput = scanner.nextLine().trim();

                if (nextInput.equals("prev")) {
                    if (currentPage == 0) {
                        view.displayErrorMsg("No more pages.");
                        continue;
                    }
                    currentPage = Math.max(0, currentPage - 1);
                    view.displayFeaturedPlaylists(featuredPlaylists.subList(
                                    currentPage * itemsPerPage,
                                    Math.min(featuredPlaylists.size(), (currentPage + 1) * itemsPerPage)
                            )
                    );
                    view.displayPageInfo(currentPage, totalPages);
                } else if (nextInput.equals("next")) {
                    if (currentPage == totalPages - 1) {
                        view.displayErrorMsg("No more pages.");
                        continue;
                    }
                    currentPage = Math.min(totalPages - 1, currentPage + 1);
                    view.displayFeaturedPlaylists(featuredPlaylists.subList(
                                    currentPage * itemsPerPage,
                                    Math.min(featuredPlaylists.size(), (currentPage + 1) * itemsPerPage)
                            )
                    );
                    view.displayPageInfo(currentPage, totalPages);
                } else {
                    if (nextInput.startsWith("playlists")) {
                        if (nextInput.length() < 11) {
                            view.displayErrorMsg("Invalid input");
                        } else {
                            controller.setCurrentStrategy(new PlaylistsStrategy(model, view, nextInput));
                            controller.getCurrentStrategy().handleInput();
                            break;
                        }
                    } else if (controller.getInputStrategiesMap().containsKey(nextInput)) {
                        Strategy nextStrategy = controller.getInputStrategiesMap().get(nextInput);
                        controller.setCurrentStrategy(nextStrategy);
                        controller.getCurrentStrategy().handleInput();
                        break;
                    } else {
                        view.displayErrorMsg("Invalid input");
                    }
                }
            }
        }
    }

}
