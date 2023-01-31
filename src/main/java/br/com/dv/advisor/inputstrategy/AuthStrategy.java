package br.com.dv.advisor.inputstrategy;

import br.com.dv.advisor.MusicAdvisorView;
import br.com.dv.advisor.oauth2.Authorization;

public class AuthStrategy implements Strategy {

    private final MusicAdvisorView view;

    public AuthStrategy(MusicAdvisorView view) {
        this.view = view;
    }

    @Override
    public void handleInput() {
        Authorization authorization = new Authorization(view);
        authorization.createHttpServer();
    }

}
