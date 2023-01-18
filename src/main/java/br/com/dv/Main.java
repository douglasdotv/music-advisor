package br.com.dv;

public class Main {

    public static void main(String[] args) {
        MusicAdvisorModel model = new MusicAdvisorModel();
        MusicAdvisorView view = new MusicAdvisorView();
        MusicAdvisorController controller = new MusicAdvisorController(model, view);
        controller.run();
    }

}
