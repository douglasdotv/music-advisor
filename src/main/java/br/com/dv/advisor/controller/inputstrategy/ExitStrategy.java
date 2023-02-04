package br.com.dv.advisor.controller.inputstrategy;

import br.com.dv.advisor.view.MusicAdvisorView;

public class ExitStrategy implements Strategy {

    private final MusicAdvisorView view;
    
    public ExitStrategy(MusicAdvisorView view) {
        this.view = view;
    }

    @Override
    public void handleInput() {
        view.exit();
    }

}
