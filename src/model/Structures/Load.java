package model.Structures;

import javafx.application.Platform;
import model.Cd;
import ui.FXSplash;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Load extends Thread implements Serializable {

    private boolean loader;
    private final String SRC_FILE = "data/Data.txt";
    private FXSplash fxSplash;
    private ObjectInputStream is;
    private static final long serialVersionUID = 1;

    public Load(FXSplash splash) {
        this.fxSplash = splash;
    }

    @Override
    public void run() {
        try {
            Cd cr = read();
            Platform.runLater(new Thread(() -> fxSplash.setCr(cr)));
            is.close();

        } catch (IOException | ClassNotFoundException e) {
            Cd cr = new Cd();
            Platform.runLater(new Thread(() -> fxSplash.setCr(cr)));
        }
    }

    public Cd read() throws IOException, ClassNotFoundException {
        is = new ObjectInputStream(new FileInputStream(SRC_FILE));
        return (Cd) is.readObject();
    }

}
