package csc305.parkwise.Common.Utils;

import javafx.scene.control.Alert;

public class Utilities {
    public Utilities() {}

    public static void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);

        alert.setTitle("ParkWise");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
