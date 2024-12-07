package csc305.parkwise.Common.Controllers;

import java.io.IOException;

import csc305.parkwise.Common.Utils.SceneSwitcher;
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
        SceneSwitcher sceneSwitcher = new SceneSwitcher();

        sceneSwitcher.navigateTo(actionEvent, "Users/Asif/ParkDirector/pd-dashboard.fxml");
        // path pattern: Users/Asif/ParkDirector/pd-dashboard.fxml
    }
}