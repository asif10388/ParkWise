module csc305.parkwise {
    requires javafx.controls;
    requires javafx.fxml;


    opens csc305.parkwise to javafx.fxml;
    exports csc305.parkwise;
}