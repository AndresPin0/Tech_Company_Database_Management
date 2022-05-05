package ui.UserInterface.;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Employee;

public class ListUsers implements Initializable{
	
	private model.Cd cr;
    private userInterface.Controller xGUI;

    @FXML
    private TableView<Employee> tblPlayer;

    @FXML
    private TableColumn<Employee, Integer> tcCode;

    @FXML
    private TableColumn<Employee, String> tcFullName;

    @FXML
    private TableColumn<Employee, String> tcSex;

    @FXML
    private TableColumn<Employee, LocalDate> tcBirthay;

    @FXML
    private TableColumn<Employee, Double> tcHeight;

    @FXML
    private TableColumn<Employee, String> tcNationality;

    @FXML
    private ComboBox<> cbSearch;
    
    private Employee playerSelected;

 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
