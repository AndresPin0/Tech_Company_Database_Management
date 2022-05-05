package model.Structures;

import javafx.application.Platform;
import model.Cd;
import ui.FXSplash;

public class LoaderBar extends Thread {

    private LoaderBar bar;
    private FXSplash preloader;
    private Cd fb;

    public LoaderBar(FXSplash preloader, LoaderBar bar, Cd fb) {
        this.preloader = preloader;
        this.bar = bar;
        this.fb = fb;

    }

    @Override
    public void run() {
        Load load = new Load(preloader);
        load.start();
        pause(1000);
        int LOADING_TIME_INTERVAL = 10;
        while (bar.isActive()) {
            if (!currentThread().isInterrupted()) {
                bar.doProgress();
                Platform.runLater(new Thread(() -> {
                    try {
                        preloader.loadBar();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
                pause(LOADING_TIME_INTERVAL);
                double percentage = (bar.getBarWidth() / bar.LOADED_WIDTH) * 100;

                if (percentage >= 75.00 && percentage < 85.00) {
                    pause(100);
                }

                if (percentage >= 95.00 && load.isAlive()) {
                    pause(2000);
                }
            }

        }

        Platform.runLater(new Thread(() -> preloader.postLoaded()));

    }

    private void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
