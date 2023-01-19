package br.com.dv.advisor.inputstrategy;

import br.com.dv.advisor.MusicAdvisorModel;
import br.com.dv.advisor.MusicAdvisorView;

public class PlaylistsStrategy implements Strategy {

    private final MusicAdvisorModel model;
    private final MusicAdvisorView view;
    private final String input;

    public PlaylistsStrategy(MusicAdvisorModel model, MusicAdvisorView view, String input) {
        this.model = model;
        this.view = view;
        this.input = input;
    }

    @Override
    public void handleInput() {
        String[] inputArray = input.split("\\s+");

        String category = inputArray[1].toUpperCase();

        if (model.getCategories().contains(category)) {
            view.displayPlaylistsByCategory(category, model.getPlaylistsByCategory().get(category));
        } else {
            view.displayInvalidInputMsg();
        }
    }

}
