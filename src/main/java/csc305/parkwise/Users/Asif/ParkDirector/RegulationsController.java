package csc305.parkwise.Users.Asif.ParkDirector;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegulationsController
{
    @javafx.fxml.FXML
    private TextField ruleSummaryInput;
    @javafx.fxml.FXML
    private ComboBox regulationTypeCombobox;
    @javafx.fxml.FXML
    private TextArea ruleDescriptionInput;
    @javafx.fxml.FXML
    private DatePicker effectiveDatePicker;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onCreateNewRegulationButtonClick(ActionEvent actionEvent) {
    }
}