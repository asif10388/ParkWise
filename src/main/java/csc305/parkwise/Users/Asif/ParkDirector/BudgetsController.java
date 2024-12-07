package csc305.parkwise.Users.Asif.ParkDirector;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BudgetsController
{
    @javafx.fxml.FXML
    private TextArea budgetDescriptionTextarea;
    @javafx.fxml.FXML
    private TextField requestTypeInput;
    @javafx.fxml.FXML
    private TextField requesterNameInput;
    @javafx.fxml.FXML
    private TextField requesterRoleInput;
    @javafx.fxml.FXML
    private TextField requestedAmountInput;
    @javafx.fxml.FXML
    private TextField allocatedBudgetInput;
    @javafx.fxml.FXML
    private ComboBox selectRequestCombobox;
    @javafx.fxml.FXML
    private TextField requestedDateInput;
    @javafx.fxml.FXML
    private TableView stattAccountsTableview;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onCreateBudgetButtonClick(ActionEvent actionEvent) {
    }
}