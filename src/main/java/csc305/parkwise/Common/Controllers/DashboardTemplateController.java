package csc305.parkwise.Common.Controllers;

import csc305.parkwise.Common.Utils.SceneSwitcher;
import javafx.event.ActionEvent;

import java.io.IOException;

public class DashboardTemplateController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onLogoutButtonClick(ActionEvent actionEvent) throws IOException {
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        sceneSwitcher.navigateTo(actionEvent, "login.fxml");
    }
}