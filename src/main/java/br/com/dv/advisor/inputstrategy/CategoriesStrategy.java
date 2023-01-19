package br.com.dv.advisor.inputstrategy;

import br.com.dv.advisor.MusicAdvisorModel;
import br.com.dv.advisor.MusicAdvisorView;

public class CategoriesStrategy implements Strategy {

    private final MusicAdvisorModel model;
    private final MusicAdvisorView view;

    public CategoriesStrategy(MusicAdvisorModel model, MusicAdvisorView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handleInput() {
        view.displayCategories(model.getCategories());
    }

}
