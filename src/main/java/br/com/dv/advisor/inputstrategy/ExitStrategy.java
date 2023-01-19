package br.com.dv.advisor.inputstrategy;

import br.com.dv.advisor.MusicAdvisorView;

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
