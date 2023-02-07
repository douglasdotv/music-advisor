package br.com.dv.advisor;

import br.com.dv.advisor.config.MusicAdvisorConfig;
import br.com.dv.advisor.controller.MusicAdvisorController;

public class Main {

    public static void main(String[] args) {
        /*
        Args can be used to change the default values
        example: -access https://accounts.spotify.com -resource https://api.spotify.com -page 5
         */

        if (args.length > 1 && args[0].equals("-access")) {
            MusicAdvisorConfig.SERVER_PATH = args[1];
        }
        if (args.length > 2 && args[2].equals("-resource")) {
            MusicAdvisorConfig.RESOURCE_PATH = args[3] + "/v1/browse";
        }
        if (args.length > 3 && args[4].equals("-page")) {
            MusicAdvisorConfig.ITEMS_PER_PAGE = Integer.parseInt(args[5]);
        }

        MusicAdvisorController.getInstance().run();
    }

}
