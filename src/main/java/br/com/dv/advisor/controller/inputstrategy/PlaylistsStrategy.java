package br.com.dv.advisor.controller.inputstrategy;

import br.com.dv.advisor.data.CategoryData;
import br.com.dv.advisor.data.PlaylistData;
import br.com.dv.advisor.model.MusicAdvisorModel;
import br.com.dv.advisor.network.NetworkClient;
import br.com.dv.advisor.view.MusicAdvisorView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class PlaylistsStrategy extends AbstractStrategy {

    private final String input;

    public PlaylistsStrategy(MusicAdvisorModel model, MusicAdvisorView view, String input) {
        super(model, view);
        this.input = input;
    }

    @Override
    public void handleInput() {
        if (input.length() < 10) {
            view.displayErrorMsg("Invalid input");
            return;
        }

        String playlistCategory = input.substring(10).toLowerCase();

        JsonObject jo;

        jo = NetworkClient.makeGetRequest("/categories");

        List<CategoryData> categories;
        if (jo == null) {
            view.displayErrorMsg("Error: could not retrieve categories");
        } else {
            JsonArray categoriesArray = jo.get("categories").getAsJsonObject().get("items").getAsJsonArray();
            categories = parseCategoriesfromJson(categoriesArray);
            model.setCategories(categories);
        }

        String categoryId = null;
        for (CategoryData category : model.getCategories()) {
            if (category.name().toLowerCase().equals(playlistCategory)) {
                categoryId = category.id();
                break;
            }
        }

        if (categoryId == null) {
            view.displayErrorMsg("Unknown category name.");
            return;
        }

        jo = NetworkClient.makeGetRequest(
                "/categories/" +
                        categoryId +
                        "/playlists"
        );

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

                calculateTotalPages(playlistsByCategory);

                int startIndex = getStartIndex();
                int endIndex = getEndIndex(playlistsByCategory);

                view.displayPlaylistsByCategory(playlistsByCategory.subList(startIndex, endIndex));
                view.displayPageInfo(currentPage, totalPages);

                while (scanner.hasNext()) {
                    nextInput = scanner.nextLine().trim();

                    if (nextInput.equals("prev")) {
                        if (currentPage == 0) {
                            view.displayErrorMsg("No more pages.");
                            continue;
                        }
                        currentPage = Math.max(0, currentPage - 1);
                        view.displayPlaylistsByCategory(playlistsByCategory.subList(
                                currentPage * itemsPerPage,
                                Math.min(playlistsByCategory.size(), (currentPage + 1) * itemsPerPage)
                        ));
                        view.displayPageInfo(currentPage, totalPages);
                    } else if (nextInput.equals("next")) {
                        if (currentPage == totalPages - 1) {
                            view.displayErrorMsg("No more pages.");
                            continue;
                        }
                        currentPage = Math.min(totalPages - 1, currentPage + 1);
                        view.displayPlaylistsByCategory(playlistsByCategory.subList(
                                currentPage * itemsPerPage,
                                Math.min(playlistsByCategory.size(), (currentPage + 1) * itemsPerPage)
                        ));
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

}
