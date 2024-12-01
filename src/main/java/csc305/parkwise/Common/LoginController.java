package csc305.parkwise.Common;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class LoginController
{
    @javafx.fxml.FXML
    private TextField userIdInput;
    @javafx.fxml.FXML
    private TextField passwordInput;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
        new SceneSwitcher(actionEvent, "Users/Asif/CampgroundManager/cm-dashboard.fxml");
        // path pattern: Users/Asif/ParkDirector/pd-dashboard.fxml
    }
}