package csc305.parkwise.Users.Asif.CampgroundManager;

import csc305.parkwise.MainApplication;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CMDashboardController
{
    @javafx.fxml.FXML
    private BorderPane dashboardBorderPane;
    @javafx.fxml.FXML
    private VBox dashboardVbox;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onLogoutButtonClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onCampsiteAvailabilityButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/CampgroundManager/campsite-availability-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onCampsiteConfirmationsButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/CampgroundManager/assign-ranger-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onRentalEquipmentButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/CampgroundManager/rental-equipment-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onCamperEntriesButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/CampgroundManager/camper-entries-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onOccupancyReportsButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/CampgroundManager/occupancy-reports-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onOnsiteRegistrationsButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/CampgroundManager/onsite-registrations-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onCampgroundManagerDashboardButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/CampgroundManager/cm-dashboard.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onMaintenanceRequestsButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/CampgroundManager/maintenance-requests-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}