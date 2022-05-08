package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.Employee;

public class FXController implements Initializable {

    @FXML
    private StackPane stackPane;
    @FXML
    private JFXTextField txtAmount;
    @FXML
    private Pane pProgressBar;

    private model.Cd fb;
    private final FXEmployee employee;
    private final FXListEmployees fxListEmployees;

    public FXController(model.Cd fb) {
        this.fb = fb;
        employee = new FXEmployee(this.fb, this);
        fxListEmployees = new FXListEmployees(this.fb, this);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setFb(model.Cd fb) {
        this.fb = fb;
    }

    public void saveData() throws IOException {
        String SRC_FILE = "data/Data.txt";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SRC_FILE));
        oos.writeObject(this.fb);
        oos.close();
    }

    public void newStage(Parent root) {
        Stage newStage = new Stage();
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.setTitle("Simulation");
        newStage.setResizable(false);
        newStage.show();
    }

    public void openListPlayers() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ListEmployees.fxml"));
        fxmlLoader.setController(fxListEmployees);
        Parent root = fxmlLoader.load();
        newStage(root);
    }

    public void showAlert(boolean success, String msg, StackPane stackPane) {
        JFXDialogLayout content = new JFXDialogLayout();
        JFXButton button = new JFXButton("Okay");
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        button.setOnAction((ActionEvent event) -> {
            dialog.close();
        });
        content.setActions(button);
        String header = (success) ? "¡Success!" : "¡Error!";
        content.setHeading(new Text(header));
        content.setBody(new Text(msg));
        dialog.show();
    }

    @FXML
    public void onBPlayers(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Employee.fxml"));
        fxmlLoader.setController(employee);
        Parent root = fxmlLoader.load();
        newStage(root);
    }

    @FXML
    public void onBStats(ActionEvent event) {
        //¿?
    }

    @FXML
    public void onImport(ActionEvent event) throws IOException {
        try {
            int amount = Integer.parseInt(txtAmount.getText());
            pProgressBar.setVisible(true);
            boolean imported = fb.importData(fb, amount);
            String msg = (imported) ? "Success" : "Error";
            pProgressBar.setVisible(false);
            showAlert(imported, msg, stackPane);
            txtAmount.setText("");
            saveData();
        }catch (NumberFormatException e){
            this.showAlert(false, "¡Format number is incorrect!", stackPane);

        }
    }


    public void refreshPlayer(Employee p) {
        employee.refreshEmployee(p);
    }
}
