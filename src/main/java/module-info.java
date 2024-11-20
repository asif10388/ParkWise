module csc305.parkwise {
    requires javafx.controls;
    requires javafx.fxml;

    opens csc305.parkwise to javafx.fxml;
    exports csc305.parkwise;
    exports csc305.parkwise.Arman.WildlifeBiologist;
    opens csc305.parkwise.Arman.WildlifeBiologist to javafx.fxml;
    exports csc305.parkwise.Arman.TourGuide;
    opens csc305.parkwise.Arman.TourGuide to javafx.fxml;
}