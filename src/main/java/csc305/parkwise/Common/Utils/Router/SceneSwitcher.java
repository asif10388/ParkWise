package csc305.parkwise.Common.Utils.Router;
import csc305.parkwise.MainApplication;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

public class SceneSwitcher {
    private String fxml;
    private FXMLLoader fxmlLoader;

    public String getFxml() {
        return fxml;
    }

    public void setFxml(String fxml) {
        this.fxml = fxml;
    }

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public void setFxmlLoader(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public SceneSwitcher() {}

    public SceneSwitcher(String fxml) {
        this.fxml = fxml;
        this.fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml));
    }

    public void navigateTo(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(this.fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void openInNewWindow() throws IOException {
        Scene scene = new Scene(this.fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void replaceScene(BorderPane borderPane) throws IOException {
        borderPane.setCenter(this.fxmlLoader.load());
    }
}
