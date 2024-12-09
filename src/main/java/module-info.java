module csc305.parkwise {
    requires javafx.fxml;
    requires javafx.controls;

    opens csc305.parkwise to javafx.fxml;
    exports csc305.parkwise;

    exports csc305.parkwise.Users.Arman.WildlifeBiologist;
    opens csc305.parkwise.Users.Arman.WildlifeBiologist to javafx.fxml;

    exports csc305.parkwise.Users.Arman.TourGuide;
    opens csc305.parkwise.Users.Arman.TourGuide to javafx.fxml;

    exports csc305.parkwise.Users.Asif.ParkDirector;
    opens csc305.parkwise.Users.Asif.ParkDirector to javafx.fxml;

    exports csc305.parkwise.Users.Asif.CampgroundManager;
    opens csc305.parkwise.Users.Asif.CampgroundManager to javafx.fxml;

    exports csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts;
    opens csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts to javafx.fxml;

    exports csc305.parkwise.Common.Controllers;
    opens csc305.parkwise.Common.Controllers to javafx.fxml;

    exports csc305.parkwise.Common.Utils;
    opens csc305.parkwise.Common.Utils to javafx.fxml;

    exports csc305.parkwise.Common.Utils.Router;
    opens csc305.parkwise.Common.Utils.Router to javafx.fxml;

    exports csc305.parkwise.Common.Utils.Stream;
    opens csc305.parkwise.Common.Utils.Stream to javafx.fxml;

    opens csc305.parkwise.Common.Models to javafx.base;

    exports csc305.parkwise.Users.Asif.ParkDirector.PerformanceReviews;
    opens csc305.parkwise.Users.Asif.ParkDirector.PerformanceReviews to javafx.fxml;

    exports csc305.parkwise.Common.Utils.Router.RoutesEnum;
    opens csc305.parkwise.Common.Utils.Router.RoutesEnum to javafx.fxml;
    exports csc305.parkwise.Users.Asif.ParkDirector.Announcements;
    opens csc305.parkwise.Users.Asif.ParkDirector.Announcements to javafx.fxml;
    exports csc305.parkwise.Users.Asif.ParkDirector.Regulations;
    opens csc305.parkwise.Users.Asif.ParkDirector.Regulations to javafx.fxml;
    exports csc305.parkwise.Users.Asif.CampgroundManager.Campsites;
    opens csc305.parkwise.Users.Asif.CampgroundManager.Campsites to javafx.fxml;
    exports csc305.parkwise.Users.Asif.CampgroundManager.MaintenanceRequests;
    opens csc305.parkwise.Users.Asif.CampgroundManager.MaintenanceRequests to javafx.fxml;
}