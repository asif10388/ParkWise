package csc305.parkwise.Users.Asif.ParkDirector;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StaffAccountsController
{
    @javafx.fxml.FXML
    private TextField fullNameTextInput;
    @javafx.fxml.FXML
    private ComboBox<String> userTypeCombobox;
    @javafx.fxml.FXML
    private TextField emailAddressInput;
    @javafx.fxml.FXML
    private PasswordField passwordInput;
    @javafx.fxml.FXML
    private TableView stattAccountsTableview;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onAddUserButtonClick(ActionEvent actionEvent) {
    }
}