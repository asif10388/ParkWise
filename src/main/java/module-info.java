module csc305.parkwise {
    requires javafx.fxml;
    requires javafx.controls;

    opens csc305.parkwise to javafx.fxml;
    exports csc305.parkwise;

    exports csc305.parkwise.Common;
    opens csc305.parkwise.Common to javafx.fxml;

    exports csc305.parkwise.Users.Arman.WildlifeBiologist;
    opens csc305.parkwise.Users.Arman.WildlifeBiologist to javafx.fxml;

    exports csc305.parkwise.Users.Arman.TourGuide;
    opens csc305.parkwise.Users.Arman.TourGuide to javafx.fxml;

    exports csc305.parkwise.Users.Asif.ParkDirector;
    opens csc305.parkwise.Users.Asif.ParkDirector to javafx.fxml;

}