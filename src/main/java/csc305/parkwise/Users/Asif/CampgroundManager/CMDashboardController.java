package csc305.parkwise.Users.Asif.CampgroundManager;

import csc305.parkwise.Common.Utils.Router.RouteMapper;
import csc305.parkwise.Common.Utils.Router.RoutesEnum.CampgroundManagerRoutes;
import csc305.parkwise.Common.Utils.Router.SceneSwitcher;
import csc305.parkwise.MainApplication;
import csc305.parkwise.Users.Asif.CampgroundManager.CampsiteMaintenanceRequests.CampsiteMaintenanceRequestsController;
import csc305.parkwise.Users.Asif.CampgroundManager.RestockSuppliesRequests.RestockSuppliesRequestController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CMDashboardController
{
    @javafx.fxml.FXML
    private BorderPane dashboardBorderPane;
    @javafx.fxml.FXML
    private VBox dashboardVbox;
    @javafx.fxml.FXML
    private Label userIdLabel;
    @javafx.fxml.FXML
    private Label userTypeLabel;

    private String userId;
    private String userType;

    public String getUserId() {
        return userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType){
        userTypeLabel.setText(userType);
        this.userType = userType;
    }

    public void setUserId(String userId){
        userIdLabel.setText(userId);
        this.userId = userId;
    }


    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onLogoutButtonClick(ActionEvent actionEvent) throws IOException {
        SceneSwitcher cmSceneSwitcher = new SceneSwitcher("login.fxml");
        cmSceneSwitcher.navigateTo(actionEvent);
    }

    @javafx.fxml.FXML
    public void onCampsiteAvailabilityButtonClick(Event event) throws IOException {
        SceneSwitcher sceneSwitcher = new SceneSwitcher("Users/Asif/CampgroundManager/campsite-availability-view.fxml");
        sceneSwitcher.replaceScene(dashboardBorderPane);
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
    public void onMaintenanceRequestsButtonClick(Event event) throws IOException {
        RouteMapper routes = new RouteMapper();
        String route = routes.getCampgroundManagerRoute(CampgroundManagerRoutes.MaintenanceRequestsView);

        SceneSwitcher cmSceneSwitcher = new SceneSwitcher(route);
        cmSceneSwitcher.replaceScene(dashboardBorderPane);

        CampsiteMaintenanceRequestsController campsiteMaintenanceRequestsController = cmSceneSwitcher.getFxmlLoader().getController();
        campsiteMaintenanceRequestsController.setUserId(Integer.parseInt(this.getUserId()));
    }

    @javafx.fxml.FXML
    public void onRestockSuppliesButtonClick(Event event) throws IOException {
        RouteMapper routes = new RouteMapper();
        String route = routes.getCampgroundManagerRoute(CampgroundManagerRoutes.RestockSuppliesView);

        SceneSwitcher cmSceneSwitcher = new SceneSwitcher(route);
        cmSceneSwitcher.replaceScene(dashboardBorderPane);

        RestockSuppliesRequestController restockSuppliesRequestController = cmSceneSwitcher.getFxmlLoader().getController();
        restockSuppliesRequestController.setUserId(Integer.parseInt(this.getUserId()));
    }

    @javafx.fxml.FXML
    public void onRecordUsageButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/CampgroundManager/record-equipment-usage-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}