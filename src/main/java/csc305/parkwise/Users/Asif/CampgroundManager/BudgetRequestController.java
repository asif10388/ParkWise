package csc305.parkwise.Users.Asif.CampgroundManager;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class BudgetRequestController
{
    @javafx.fxml.FXML
    private DatePicker dateOfActionDatePicker;
    @javafx.fxml.FXML
    private ComboBox itemNameCombobox;
    @javafx.fxml.FXML
    private ToggleGroup actionTypeToggleGroup;
    @javafx.fxml.FXML
    private TextField quantityTextInput;
    @javafx.fxml.FXML
    private TextArea commentsTextarea;
    @javafx.fxml.FXML
    private TableView stattAccountsTableview;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onSubmitUsageButtonClick(ActionEvent actionEvent) {
    }
}