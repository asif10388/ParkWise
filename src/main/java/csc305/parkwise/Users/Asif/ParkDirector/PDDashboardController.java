package csc305.parkwise.Users.Asif.ParkDirector;

import csc305.parkwise.MainApplication;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PDDashboardController
{
    @javafx.fxml.FXML
    private BorderPane dashboardBorderPane;
    @javafx.fxml.FXML
    private VBox dashboardVbox;
    @javafx.fxml.FXML
    private Label userIdLabel;
    @javafx.fxml.FXML
    private Label userTypeLabel;

    public void setUserType(String userType){
        userTypeLabel.setText(userType);
    }

    public void setUserId(String userId){
        userIdLabel.setText(userId);
    }

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onLogoutButtonClick(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void onStaffAccountsMenuButtonClick(Event event) {
     try {
         FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/ParkDirector/staff-accounts-view.fxml"));
         dashboardBorderPane.setCenter(loader.load());
     } catch (Exception e){
       throw new RuntimeException(e);
     }
    }

    @javafx.fxml.FXML
    public void onFinancialReportsButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/ParkDirector/financial-reports-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onRegulationsButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/ParkDirector/regulations-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onResearchProposalsButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/ParkDirector/research-proposals-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onBudgetsButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/ParkDirector/budgets-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onAnnouncementsButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/ParkDirector/announcements-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onPerformanceReviewsButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/ParkDirector/performance-reviews-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onMaintenanceRequestsButtonClick(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Users/Asif/ParkDirector/maintenance-requests-view.fxml"));
            dashboardBorderPane.setCenter(loader.load());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}