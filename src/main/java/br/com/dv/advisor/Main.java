package br.com.dv.advisor;

public class Main {

    private static final MusicAdvisorModel model = new MusicAdvisorModel();
    private static final MusicAdvisorView view = new MusicAdvisorView();
    private static final MusicAdvisorController controller = new MusicAdvisorController(model, view);

    public static void main(String[] args) {
        controller.run();
    }

}
