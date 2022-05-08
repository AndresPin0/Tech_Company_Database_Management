package model.Structures;

import javafx.application.Platform;
import model.Cd;
import model.LoadingBar;
import ui.FXSplash;

public class LoaderBarT extends Thread {

    private final FXSplash preloader;

    public LoaderBarT(FXSplash preloader, LoadingBar bar, Cd fb) {
        this.preloader = preloader;

    }

    @Override
    public void run() {
        Load load = new Load(preloader);

        System.out.println("Antes de la carga de datos");
        load.doAction();
        System.out.println("Despues de la carga de datos");

        pause(1000);

        //run On UI Thread
        Platform.runLater(()->preloader.setProgress(0.1));
        System.out.println("Despues de la pausa");
        pause(1000);
        Platform.runLater(()->preloader.setProgress(0.2));
        pause(1000);
        Platform.runLater(()->preloader.setProgress(0.3));
        pause(1000);
        Platform.runLater(()->preloader.setProgress(0.4));
        pause(1000);
        Platform.runLater(()->preloader.setProgress(0.5));
        pause(1000);
        Platform.runLater(()->preloader.setProgress(0.6));
        pause(1000);
        Platform.runLater(()->preloader.setProgress(0.7));
        pause(1000);
        Platform.runLater(()->preloader.setProgress(0.8));
        pause(1000);
        Platform.runLater(()->preloader.setProgress(0.9));
        pause(1000);
        Platform.runLater(()->preloader.setProgress(1));

        Platform.runLater(preloader::postLoaded);

    }

    private void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
