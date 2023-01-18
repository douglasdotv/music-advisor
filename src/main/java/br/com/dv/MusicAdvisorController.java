package br.com.dv;

import java.util.Scanner;

public class MusicAdvisorController {

    private final MusicAdvisorModel model;
    private final MusicAdvisorView view;

    public MusicAdvisorController(MusicAdvisorModel model, MusicAdvisorView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        String input;
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            input = scanner.nextLine();

            if (input.equals("featured")) {
                view.displayFeaturedPlaylists(model.getFeaturedPlaylists());
            } else if (input.equals("new")) {
                view.displayNewReleases(model.getNewReleases());
            } else if (input.equals("categories")) {
                view.displayCategories(model.getCategories());
            } else if (input.startsWith("playlists")) {
                String[] inputArray = input.split("\\s+");
                String category = inputArray[1].toUpperCase();
                if (!model.getCategories().contains(category)) {
                    view.displayInvalidInputMsg();
                } else {
                    view.displayPlaylistsByCategory(category, model.getPlaylistsByCategory().get(category));
                }
            } else if (input.equals("exit")) {
                view.exit();
                exit = true;
            } else {
                view.displayInvalidInputMsg();
            }
        }
    }

}
