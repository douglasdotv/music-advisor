package br.com.dv.advisor;

public class Main {

    public static void main(String[] args) {
        if (args.length > 1 && args[0].equals("-access")) {
            MusicAdvisorConfig.SERVER_PATH = args[1];
        }

        MusicAdvisorController.run();
    }

}
