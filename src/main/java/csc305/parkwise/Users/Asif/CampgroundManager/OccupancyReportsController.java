package csc305.parkwise.Users.Asif.CampgroundManager;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class OccupancyReportsController
{
    @javafx.fxml.FXML
    private TableView stattAccountsTableview;
    @javafx.fxml.FXML
    private CheckBox reservedCheckbox;
    @javafx.fxml.FXML
    private DatePicker endDatePicker;
    @javafx.fxml.FXML
    private CheckBox availableCheckbox;
    @javafx.fxml.FXML
    private DatePicker startDatePicker;
    @javafx.fxml.FXML
    private CheckBox underMaintenanceCheckbox;
    @javafx.fxml.FXML
    private CheckBox includeDetailedBreakdownCheckbox;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @Deprecated
    public void onEvaluateRequestButtonClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onDownloadPDFButtonClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onGenerateReportButtonClick(ActionEvent actionEvent) {
    }
}