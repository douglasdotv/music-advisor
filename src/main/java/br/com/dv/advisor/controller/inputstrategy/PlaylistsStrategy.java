package br.com.dv.advisor.controller.inputstrategy;

import br.com.dv.advisor.model.CategoryData;
import br.com.dv.advisor.model.PlaylistData;
import br.com.dv.advisor.model.SpotifyApiData;
import br.com.dv.advisor.network.NetworkClient;
import br.com.dv.advisor.view.MusicAdvisorView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class PlaylistsStrategy extends AbstractStrategy {

    private final String input;

    public PlaylistsStrategy(SpotifyApiData data, MusicAdvisorView view, String input) {
        super(data, view);
        this.input = input;
    }

    @Override
    public void handleInput() {
        // Check if input is valid (if it's not, display an error message and return)
        if (input.length() < 10) {
            view.displayErrorMsg("Invalid input");
            return;
        }

        // Get the category name from the input
        String playlistCategory = input.substring(10).toLowerCase();

        // Get all categories from API
        JsonObject jo = NetworkClient.makeGetRequest("/categories");
        List<CategoryData> categories;
        if (jo == null) {
            view.displayErrorMsg("Error: could not retrieve categories");
        } else {
            JsonArray categoriesArray = jo.get("categories").getAsJsonObject().get("items").getAsJsonArray();
            categories = parseCategoriesfromJson(categoriesArray);
            data.setCategories(categories);
        }

        // Check if the category name from the input is present in the list of categories retrieved from the API
        // If it is, get the category id
        String categoryId = null;
        for (CategoryData category : data.getCategories()) {
            if (category.name().toLowerCase().equals(playlistCategory)) {
                categoryId = category.id();
                break;
            }
        }

        // If it isn't, display an error message and return
        if (categoryId == null) {
            view.displayErrorMsg("Unknown category name.");
            return;
        }

        // Assuming the category id was found, use it to retrieve the playlists from the API
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
                data.setPlaylistsByCategory(playlistsByCategory);

                calculateTotalPages(data.getPlaylistsByCategory());

                view.displayPlaylistsByCategory(data.getPlaylistsByCategory()
                        .subList(
                                getStartingIndex(),
                                getEndIndex(data.getPlaylistsByCategory())
                        )
                );

                view.displayPageInfo(currentPage, totalPages);

                /*
                if the input is "prev" or "next", the loop will keep going to let the user navigate through the pages
                 */
                boolean shouldExit = false;
                while (scanner.hasNext() && !shouldExit) {
                    nextInput = scanner.nextLine().trim();

                    if (nextInput.equals("prev")) {
                        if (currentPage == 0) {
                            view.displayErrorMsg("No more pages.");
                            continue;
                        }
                        currentPage = Math.max(0, currentPage - 1);

                        view.displayPlaylistsByCategory(data.getPlaylistsByCategory()
                                .subList(
                                        getStartingIndex(),
                                        getEndIndex(data.getPlaylistsByCategory())
                                )
                        );

                        view.displayPageInfo(currentPage, totalPages);
                    } else if (nextInput.equals("next")) {
                        if (currentPage == totalPages - 1) {
                            view.displayErrorMsg("No more pages.");
                            continue;
                        }
                        currentPage = Math.min(totalPages - 1, currentPage + 1);

                        view.displayPlaylistsByCategory(data.getPlaylistsByCategory()
                                .subList(
                                        getStartingIndex(),
                                        getEndIndex(data.getPlaylistsByCategory())
                                )
                        );

                        view.displayPageInfo(currentPage, totalPages);
                    } else {
                        /*
                        if the input is not "prev" or "next":

                        - if the input is
                        "featured", "categories", "playlists <existing category>", "new" or "exit",
                        shouldExit will be true and the pagination loop will break, making way for the next strategy

                        - if the input is invalid
                        (i.e. not "featured", "categories", "playlists <ec>", "new" or "exit"),
                        shouldExit will be false and the pagination loop will keep going
                         */
                        shouldExit = handleNextInput(nextInput);
                    }
                }

            }
        }
    }

}
