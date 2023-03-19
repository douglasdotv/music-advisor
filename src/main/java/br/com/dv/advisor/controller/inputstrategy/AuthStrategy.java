package br.com.dv.advisor.controller.inputstrategy;

import br.com.dv.advisor.config.MusicAdvisorConfig;
import br.com.dv.advisor.view.MusicAdvisorView;
import br.com.dv.advisor.oauth2.Authorization;

public class AuthStrategy implements Strategy {

    private final MusicAdvisorView view;
    private final Authorization authorization;

    public AuthStrategy(MusicAdvisorView view) {
        this.view = view;
        this.authorization = new Authorization(view);
    }

    @Override
    public void handleInput() {
        view.displayAuthWaitingMsg(MusicAdvisorConfig.AUTH_URL);

        authorization.createHttpServer();

        if (!MusicAdvisorConfig.AUTH_CODE.isEmpty()) {
            view.displayAuthCodeReceivedMsg();
            authorization.getAccessToken();
        } else {
            view.displayErrorMsg("Code is missing!");
        }
    }

}
