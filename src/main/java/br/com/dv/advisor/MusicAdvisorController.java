package br.com.dv.advisor;

import br.com.dv.advisor.inputstrategy.*;

import java.util.*;

public class MusicAdvisorController {

    private static final MusicAdvisorModel model = new MusicAdvisorModel();
    private static final MusicAdvisorView view = new MusicAdvisorView();
    private static final Map<String, Strategy> inputStrategiesMap = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static String input;
    private static boolean oAuth = false;

    static {
        inputStrategiesMap.put("featured", new FeaturedStrategy(model, view));
        inputStrategiesMap.put("new", new NewStrategy(model, view));
        inputStrategiesMap.put("categories", new CategoriesStrategy(model, view));
        inputStrategiesMap.put("playlists", new PlaylistsStrategy(model, view, input));
        inputStrategiesMap.put("exit", new ExitStrategy(view));
        inputStrategiesMap.put("auth", new AuthStrategy(view));
    }

    public static void run() {
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
                    if (input.equals("playlists")) {
                        System.out.println("Type one of the categories below after \"playlists\": ");
                        strategy = new CategoriesStrategy(model, view);
                    } else {
                        strategy = new PlaylistsStrategy(model, view, input);
                    }
                }
                strategy.handleInput();
            } else {
                if (!oAuth) {
                    view.displayOAuthProvideAccessMsg();
                } else {
                    view.displayInvalidInputMsg();
                }
            }
        }
    }

}
