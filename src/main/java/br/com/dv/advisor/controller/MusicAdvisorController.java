package br.com.dv.advisor.controller;

import br.com.dv.advisor.controller.inputstrategy.*;
import br.com.dv.advisor.model.SpotifyApiData;
import br.com.dv.advisor.view.MusicAdvisorView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MusicAdvisorController {

    private static final MusicAdvisorController instance = new MusicAdvisorController();
    private static final SpotifyApiData data = new SpotifyApiData();
    private static final MusicAdvisorView view = new MusicAdvisorView();
    private static final Map<String, Strategy> inputStrategiesMap = new HashMap<>();
    private static String input;
    private static boolean oAuth = false;
    private static Strategy currentStrategy = null;

    static {
        inputStrategiesMap.put("featured", new FeaturedStrategy(data, view));
        inputStrategiesMap.put("new", new NewStrategy(data, view));
        inputStrategiesMap.put("categories", new CategoriesStrategy(data, view));
        inputStrategiesMap.put("playlists", new PlaylistsStrategy(data, view, input));
        inputStrategiesMap.put("exit", new ExitStrategy(view));
        inputStrategiesMap.put("auth", new AuthStrategy(view));
    }

    private MusicAdvisorController() {
    }

    public static MusicAdvisorController getInstance() {
        return instance;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                input = scanner.nextLine().trim();
                String[] inputArray = input.split("\\s+");
                Strategy strategy = inputStrategiesMap.get(inputArray[0]);

                if (strategy instanceof ExitStrategy) {
                    break;
                }

                if (strategy instanceof AuthStrategy) {
                    oAuth = true;
                }

                if (strategy != null && oAuth) {
                    if (inputArray[0].equals("playlists")) {
                        strategy = new PlaylistsStrategy(data, view, input);
                    }
                    currentStrategy = strategy;
                    try {
                        strategy.handleInput();
                    } catch (Exception e) {
                        view.displayErrorMsg(e.getMessage());
                    }
                } else {
                    if (!oAuth) {
                        view.displayOAuthProvideAccessMsg();
                    } else {
                        view.displayErrorMsg("Invalid input");
                    }
                }
            }
        }
    }

    public Strategy getCurrentStrategy() {
        return currentStrategy;
    }

    public void setCurrentStrategy(Strategy currentStrategy) {
        MusicAdvisorController.currentStrategy = currentStrategy;
    }

    public Map<String, Strategy> getInputStrategiesMap() {
        return inputStrategiesMap;
    }

}
