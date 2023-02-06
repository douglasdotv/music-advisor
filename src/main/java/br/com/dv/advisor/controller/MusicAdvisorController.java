package br.com.dv.advisor.controller;

import br.com.dv.advisor.model.MusicAdvisorModel;
import br.com.dv.advisor.controller.inputstrategy.*;
import br.com.dv.advisor.view.MusicAdvisorView;

import java.util.*;

public class MusicAdvisorController {

    private static final MusicAdvisorController instance = new MusicAdvisorController();
    private static final MusicAdvisorModel model = new MusicAdvisorModel();
    private static final MusicAdvisorView view = new MusicAdvisorView();
    private static final Map<String, Strategy> inputStrategiesMap = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static String input;
    private static boolean oAuth = false;
    private static Strategy currentStrategy = null;

    static {
        inputStrategiesMap.put("featured", new FeaturedStrategy(model, view));
        inputStrategiesMap.put("new", new NewStrategy(model, view));
        inputStrategiesMap.put("categories", new CategoriesStrategy(model, view));
        inputStrategiesMap.put("playlists", new PlaylistsStrategy(model, view, input));
        inputStrategiesMap.put("exit", new ExitStrategy(view));
        inputStrategiesMap.put("auth", new AuthStrategy(view));
    }

    private MusicAdvisorController() { }

    public static MusicAdvisorController getInstance() {
        return instance;
    }

    public void run() {
        while (true) {
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
                    strategy = new PlaylistsStrategy(model, view, input);
                }
                currentStrategy = strategy;
                strategy.handleInput();
            } else {
                if (!oAuth) {
                    view.displayOAuthProvideAccessMsg();
                } else {
                    view.displayErrorMsg("Invalid input");
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
