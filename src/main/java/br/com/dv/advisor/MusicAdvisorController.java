package br.com.dv.advisor;

import br.com.dv.advisor.inputstrategy.*;

import java.util.*;

public class MusicAdvisorController {

    private final MusicAdvisorModel model;
    private final MusicAdvisorView view;
    private final Map<String, Strategy> inputStrategiesMap;
    private final Scanner scanner = new Scanner(System.in);
    private String input;

    public MusicAdvisorController(MusicAdvisorModel model, MusicAdvisorView view) {
        this.model = model;
        this.view = view;
        this.inputStrategiesMap = new HashMap<>();
        inputStrategiesMap.put("featured", new FeaturedStrategy(model, view));
        inputStrategiesMap.put("new", new NewStrategy(model, view));
        inputStrategiesMap.put("categories", new CategoriesStrategy(model, view));
        inputStrategiesMap.put("exit", new ExitStrategy(view));
        inputStrategiesMap.put("playlists", new PlaylistsStrategy(model, view, input));
    }

    public void run() {
        while (true) {
            input = scanner.nextLine().trim();
            String[] inputArray = input.split("\\s+");

            Strategy strategy = inputStrategiesMap.get(inputArray[0]);

            if (strategy != null) {
                if (inputArray[0].equals("playlists")) {
                    if (input.equals("playlists")) {
                        System.out.println("Type one of the categories below after \"playlists\": ");
                        strategy = new CategoriesStrategy(model, view);
                    } else {
                        strategy = new PlaylistsStrategy(model, view, input);
                    }
                }

                strategy.handleInput();

                if (strategy instanceof ExitStrategy) {
                    break;
                }
            } else {
                view.displayInvalidInputMsg();
            }
        }
    }

}
