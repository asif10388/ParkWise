package csc305.parkwise.Users.Asif.CampgroundManager;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class CampsiteAvailabilityController
{
    @javafx.fxml.FXML
    private ToggleGroup campsiteStatusToggleGroup;
    @javafx.fxml.FXML
    private TextArea maintenanceReasonTextarea;
    @javafx.fxml.FXML
    private ComboBox campsiteCombobox;
    @javafx.fxml.FXML
    private TableView stattAccountsTableview;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onUpdateStatusButtonClick(ActionEvent actionEvent) {
    }
}