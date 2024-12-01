package csc305.parkwise.Users.Asif.CampgroundManager;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class OnsiteRegistrationsComtroller
{
    @javafx.fxml.FXML
    private DatePicker checkInDatePicker;
    @javafx.fxml.FXML
    private Label payableAmountLabel;
    @javafx.fxml.FXML
    private ComboBox selectCampsiteCombobox;
    @javafx.fxml.FXML
    private ComboBox userIdCombobox;
    @javafx.fxml.FXML
    private DatePicker checkOutDatePicker;
    @javafx.fxml.FXML
    private ComboBox parkRangersCombobox;
    @javafx.fxml.FXML
    private CheckBox paidCheckbox;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onReserveCampsiteButtonClick(ActionEvent actionEvent) {
    }
}