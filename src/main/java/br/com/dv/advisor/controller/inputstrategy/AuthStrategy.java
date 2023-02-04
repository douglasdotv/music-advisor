package br.com.dv.advisor.controller.inputstrategy;

import br.com.dv.advisor.config.MusicAdvisorConfig;
import br.com.dv.advisor.view.MusicAdvisorView;
import br.com.dv.advisor.oauth2.Authorization;

public class AuthStrategy implements Strategy {

    private final MusicAdvisorView view;

    public AuthStrategy(MusicAdvisorView view) {
        this.view = view;
    }

    @Override
    public void handleInput() {
        view.displayAuthWaitingMsg(MusicAdvisorConfig.AUTH_URL);
        Authorization authorization = new Authorization(view);
        authorization.createHttpServer();
        if (!(MusicAdvisorConfig.AUTH_CODE.isEmpty())) {
            view.displayAuthCodeReceivedMsg();
            authorization.getAccessToken();
        } else {
            view.displayErrorMsg("Code is missing!");
        }
    }

}
