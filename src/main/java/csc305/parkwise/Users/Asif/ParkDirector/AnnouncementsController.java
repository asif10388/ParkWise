package csc305.parkwise.Users.Asif.ParkDirector;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AnnouncementsController
{
    @javafx.fxml.FXML
    private TextArea announcementBodyInput;
    @javafx.fxml.FXML
    private TextField announcementTitleTextInput;
    @javafx.fxml.FXML
    private DatePicker announcementExpirationDatePicker;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onRelayAnnouncementButtonClick(ActionEvent actionEvent) {
    }
}