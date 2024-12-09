package csc305.parkwise.Users.Asif.CampgroundManager.MaintenanceRequests;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MaintenanceRequestsController
{
    @javafx.fxml.FXML
    private ComboBox selectPriorityCombobox;
    @javafx.fxml.FXML
    private ComboBox selectCampsiteCombobox;
    @javafx.fxml.FXML
    private TextArea issueDescriptionTextarea;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onSubmitRequestButtonClick(ActionEvent actionEvent) {
    }
}