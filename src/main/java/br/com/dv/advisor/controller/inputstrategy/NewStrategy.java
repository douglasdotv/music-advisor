package br.com.dv.advisor.controller.inputstrategy;

import br.com.dv.advisor.model.AlbumData;
import br.com.dv.advisor.model.SpotifyApiData;
import br.com.dv.advisor.network.NetworkClient;
import br.com.dv.advisor.view.MusicAdvisorView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class NewStrategy extends AbstractStrategy {

    public NewStrategy(SpotifyApiData data, MusicAdvisorView view) {
        super(data, view);
    }

    @Override
    public void handleInput() {
        JsonObject jo = NetworkClient.makeGetRequest("/new-releases");

        if (jo == null) {
            view.displayErrorMsg("Error: could not retrieve new releases");
        } else {
            JsonArray albums = jo.get("albums").getAsJsonObject().get("items").getAsJsonArray();
            List<AlbumData> newReleases = parseAlbumsFromJson(albums);

            data.setNewReleases(newReleases);

            calculateTotalPages(data.getNewReleases());

            view.displayNewReleases(data.getNewReleases().subList(getStartingIndex(), getEndIndex(data.getNewReleases())));

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

                    view.displayNewReleases(data.getNewReleases().subList(getStartingIndex(), getEndIndex(data.getNewReleases())));

                    view.displayPageInfo(currentPage, totalPages);
                } else if (nextInput.equals("next")) {
                    if (currentPage == totalPages - 1) {
                        view.displayErrorMsg("No more pages.");
                        continue;
                    }
                    currentPage = Math.min(totalPages - 1, currentPage + 1);

                    view.displayNewReleases(data.getNewReleases().subList(getStartingIndex(), getEndIndex(data.getNewReleases())));

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


