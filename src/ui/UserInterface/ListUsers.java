package userInterface;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.objects.Crud;
import model.Person;
import userInterface.Controller;

public class ListUsers implements Initializable{
	
	private Crud cr;
    private Controller xGUI;

    @FXML
    private TableView<Person> tblPlayer;

    @FXML
    private TableColumn<Person, Integer> tcCode;

    @FXML
    private TableColumn<Person, String> tcFullName;

    @FXML
    private TableColumn<Person, String> tcSex;

    @FXML
    private TableColumn<Person, LocalDate> tcBirthay;

    @FXML
    private TableColumn<Person, Double> tcHeight;

    @FXML
    private TableColumn<Person, String> tcNationality;

    @FXML
    private ComboBox<> cbSearch;
    
    private Person playerSelected;

 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
