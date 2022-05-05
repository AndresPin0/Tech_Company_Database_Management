package model.Structures;

import javafx.application.Platform;
import model.Cd;
import ui.FXSplash;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Load {

    private final String SRC_FILE = "data/Data.txt";
    private FXSplash fxSplash;
    private ObjectInputStream is;

    public Load(FXSplash splash) {
        this.fxSplash = splash;
    }

    public void doAction() {
        try {
            Cd cr = read();
            fxSplash.setCr(cr);
            is.close();

        } catch (IOException | ClassNotFoundException e) {
            Cd cr = new Cd();
            fxSplash.setCr(cr);
        }
    }

    public Cd read() throws IOException, ClassNotFoundException {
        is = new ObjectInputStream(new FileInputStream(SRC_FILE));
        return (Cd) is.readObject();
    }

}
