package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Cd;
import model.LoadingBar;
import model.Structures.LoaderBarT;

public class FXSplash implements Initializable {

    @FXML
    private ImageView iLogo;

    @FXML
    private ProgressBar loadingBar;

    private Cd fb;
    private LoadingBar bar;
    private boolean isLoaded;
    private FXController xMenu;
    private Stage preloaderStage;
    private Scene scene;

    private static final int COUNT_LIMIT = 3000;

    public FXSplash(Cd fb) {
        this.fb = null;
        bar = new LoadingBar();
        isLoaded = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bar.setActive(true);
        System.out.println(new File("").getAbsolutePath());
        new LoaderBarT(this, bar, fb).start();
    }



    public void postLoaded() {
        ((Stage) loadingBar.getScene().getWindow()).close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Inicio.fxml"));
            fxmlLoader.setController(new FXController(fb));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene((scene));
            stage.getIcons().add(new Image(new File("resources/img/logo/logo_small_icon_only.png").toURI().toString()));
            stage.setResizable(false);
            stage.setTitle("Simulation");
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public FXController getxMenu() {
        return xMenu;
    }

    public void setCr(Cd fb) {
        this.fb = fb;
    }

    public void setxMenu(FXController xMenu) {
        this.xMenu = xMenu;
    }

    public void setProgress(double progress) {
        loadingBar.setProgress(progress);
    }
}
