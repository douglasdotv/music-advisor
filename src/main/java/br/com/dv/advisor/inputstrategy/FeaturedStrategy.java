package br.com.dv.advisor.inputstrategy;

import br.com.dv.advisor.MusicAdvisorModel;
import br.com.dv.advisor.MusicAdvisorView;

public class FeaturedStrategy implements Strategy {

    private final MusicAdvisorModel model;
    private final MusicAdvisorView view;

    public FeaturedStrategy(MusicAdvisorModel model, MusicAdvisorView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handleInput() {
        view.displayFeaturedPlaylists(model.getFeaturedPlaylists());
    }

}
