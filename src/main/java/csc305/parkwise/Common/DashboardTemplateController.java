package csc305.parkwise.Common;

import javafx.event.ActionEvent;

import java.io.IOException;

public class DashboardTemplateController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onLogoutButtonClick(ActionEvent actionEvent) throws IOException {
        new SceneSwitcher(actionEvent, "login.fxml");
    }
}