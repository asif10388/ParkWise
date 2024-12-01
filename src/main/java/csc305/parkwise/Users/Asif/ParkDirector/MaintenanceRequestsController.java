package csc305.parkwise.Users.Asif.ParkDirector;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class MaintenanceRequestsController
{
    @javafx.fxml.FXML
    private ComboBox resubmissionDatePicker;
    @javafx.fxml.FXML
    private TextField priorityLevelInput;
    @javafx.fxml.FXML
    private TextArea detailedDescriptionTextfield;
    @javafx.fxml.FXML
    private TextField locationInput;
    @javafx.fxml.FXML
    private ToggleGroup approvalDecisionRadioGroup;
    @javafx.fxml.FXML
    private CheckBox resubmitRequestCheckbox;
    @javafx.fxml.FXML
    private TextField reqeustSummaryInput;
    @javafx.fxml.FXML
    private TextArea commentsTextarea;
    @javafx.fxml.FXML
    private TextField requestedDateInput;
    @javafx.fxml.FXML
    private TableView stattAccountsTableview;
    @javafx.fxml.FXML
    private TextField requestedByInput;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onEvaluateRequestButtonClick(ActionEvent actionEvent) {
    }
}