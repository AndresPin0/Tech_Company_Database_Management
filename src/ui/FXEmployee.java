package ui;


import com.jfoenix.controls.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.Cd;
import model.Employee;

public class FXEmployee implements Initializable {

    private Cd cr;
    private FXController xGUI;
    private String imagePath;
    @FXML
    private StackPane stackPane;

    @FXML
    private Pane pane;

    @FXML
    private ImageView iSave;

    @FXML
    private ImageView iRemove;

    @FXML
    private ImageView iClear;

    @FXML
    private ImageView iUpdate;

    @FXML
    private JFXTextField txtHeight;

    @FXML
    private JFXRadioButton rbMale;

    @FXML
    private ToggleGroup tgActive;

    @FXML
    private JFXRadioButton rbFemale;

    @FXML
    private ImageView iSearch;

    @FXML
    private ImageView iPhoto;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNationality;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXDatePicker dateBirthday;

    private Employee employeeSelected;

    public FXEmployee(Cd cr, FXController xGUI) {
        this.cr = cr;
        this.xGUI = xGUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImages();
        setTxtProperties();
    }

    public void setTxtProperties() {
        txtName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtNationality.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtNationality.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtLastName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtLastName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });


    }

    public void setImages() {
        iSave.setImage(new Image(new File("resources/img/others/save-disk.png").toURI().toString()));
        iRemove.setImage(new Image(new File("resources/img/others/remove-report.png").toURI().toString()));
        iSearch.setImage(new Image(new File("resources/img/others/search.png").toURI().toString()));
        iClear.setImage(new Image(new File("resources/img/others/clear.png").toURI().toString()));
        iUpdate.setImage(new Image(new File("resources/img/others/update-file.png").toURI().toString()));




    }


    @FXML
    public void onSearchPlayer() throws IOException {
        xGUI.openListPlayers();
    }

    @FXML
    public void onDelete(ActionEvent event) throws IOException {
        if (employeeSelected != null) {
            cr.removePerson(employeeSelected);
            xGUI.saveData();
            xGUI.showAlert(true, "¡Deleted successfully!", stackPane);
            clearGui();
            employeeSelected = null;
        } else {
            xGUI.showAlert(false, "¡You have not selected a player!", stackPane);
        }
    }

    @FXML
    public void onSave(ActionEvent event) throws IOException {
        DecimalFormat df = new DecimalFormat("#.00");

        if ((!txtName.getText().equals("")) && (!txtLastName.getText().equals("")) && (!txtHeight.getText().equals(""))
                && (!txtNationality.getText().equals("")) && (tgActive.getSelectedToggle() != null) && (dateBirthday.getValue() != null)) {
            try{
                String sex;
                int code = cr.getTotalPeople() + 1;
                String name = txtName.getText();
                String lastName = txtLastName.getText();
                JFXRadioButton selected = (JFXRadioButton) tgActive.getSelectedToggle();
                if (selected.getText().equals("Male")) {
                    sex = "Male";

                }  else {
                    sex = "Female";
                }


                String birthday = getDate(dateBirthday.getValue());
                String height = df.format(Double.parseDouble(txtHeight.getText())) ;
                String nationality = txtNationality.getText();

                cr.addEmployee(new Employee(code, name, lastName, sex, birthday, height, nationality));
                clearGui();
                xGUI.saveData();
                xGUI.showAlert(true, "¡Person added!", stackPane);
            }catch (NumberFormatException e){
                xGUI.showAlert(false, "¡The decimal indicator has to be a , !", stackPane);
            }

        } else {
            xGUI.showAlert(false, "¡Can't add the player!", stackPane);
        }
    }

    private String getDate(LocalDate date){

        String day = "";
        String mount = "";

        if (date.getDayOfMonth() < 10) {
            day = "0" + date.getDayOfMonth();
        }

        if (date.getMonthValue() < 10) {
            mount = "0" + date.getMonthValue();
        }

        return day + "/" + mount + "/" + date.getYear();
    }

    @FXML
    public void onUpdate(ActionEvent event) throws IOException {
        DecimalFormat df = new DecimalFormat("#.00");

        if ((!txtName.getText().equals("")) && (!txtLastName.getText().equals("")) && (!txtHeight.getText().equals(""))
                && (!txtNationality.getText().equals("")) && (tgActive.getSelectedToggle() != null) && (dateBirthday.getValue() != null)) {
            employeeSelected.setName(txtName.getText());
            employeeSelected.setLn(txtLastName.getText());
            employeeSelected.setSex(((JFXRadioButton) tgActive.getSelectedToggle()).getText());
            employeeSelected.setBd(getDate(dateBirthday.getValue()));
            employeeSelected.setHeight(df.format(Double.parseDouble(txtHeight.getText())));
            employeeSelected.setNationality(txtNationality.getText());
            xGUI.saveData();
            xGUI.showAlert(true, "¡Person updated!", stackPane);
            clearGui();
            employeeSelected = null;
        }


    }

    public void refreshPlayer(Employee e){
        employeeSelected = e;
        txtName.setText(employeeSelected.getName());
        txtLastName.setText(employeeSelected.getLn());
        String height = employeeSelected.getHeight();
        txtHeight.setText(height.charAt(0) + "." + height.substring(2, 4));
        txtNationality.setText(employeeSelected.getNationality());
        String day = employeeSelected.getBd().substring(0, 2);
        String mount = employeeSelected.getBd().substring(3, 5);
        String year = employeeSelected.getBd().substring(6, 10);
        dateBirthday.setValue(LocalDate.parse(year + "-" + mount + "-" + day));


        if (employeeSelected.getSex().equals("Male")) {
            tgActive.selectToggle(rbMale);
        } else {
            tgActive.selectToggle(rbFemale);
        }

    }


    public void clearGui() {
        txtName.setText("");
        txtLastName.setText("");
        txtHeight.setText("");
        txtNationality.setText("");
        dateBirthday.setValue(null);
        tgActive.selectToggle(null);
        iPhoto.setImage(null);
        imagePath = "";
    }

    @FXML
    public void onClear() {
        clearGui();
        employeeSelected = null;
    }
}
