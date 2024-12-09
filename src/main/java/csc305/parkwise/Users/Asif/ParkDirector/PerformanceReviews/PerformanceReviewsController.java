package csc305.parkwise.Users.Asif.ParkDirector.PerformanceReviews;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts.StaffAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class PerformanceReviewsController
{
    @javafx.fxml.FXML
    private ComboBox<Integer> jobRatingCombobox;
    @javafx.fxml.FXML
    private TextArea feedbackTextArea;
    @javafx.fxml.FXML
    private ComboBox<Integer> teamworkRatingCombobox;
    @javafx.fxml.FXML
    private ComboBox<Integer> punctualityRatingCombobox;
    @javafx.fxml.FXML
    private ComboBox<Integer> staffIDCombobox;
    @javafx.fxml.FXML
    private ComboBox<Integer> wokrQualityCombobox;
    @javafx.fxml.FXML
    private Label staffRoleLabel;
    @javafx.fxml.FXML
    private TableView<PerformanceReview> performanceReivewsTableview;
    @javafx.fxml.FXML
    private TableColumn<PerformanceReview, Integer> staffIdColumn;
    @javafx.fxml.FXML
    private TableColumn<PerformanceReview, String> staffNameColumn;
    @javafx.fxml.FXML
    private TableColumn<PerformanceReview, String> staffRoleColumn;
    @javafx.fxml.FXML
    private TableColumn<PerformanceReview, Integer> jobColumn;
    @javafx.fxml.FXML
    private TableColumn<PerformanceReview, Integer> teamworkColumn;
    @javafx.fxml.FXML
    private TableColumn<PerformanceReview, Integer> overallColumn;
    @javafx.fxml.FXML
    private TableColumn<PerformanceReview, Integer> workQualityColumn;
    @javafx.fxml.FXML
    private TableColumn<PerformanceReview, Integer> punctualityColumn;

    private StaffAccount selectedStaffAccount;
    private final List<StaffAccount> staffAccountsList = new ArrayList<>();
    @javafx.fxml.FXML
    private Label demoLabel;

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        jobColumn.setCellValueFactory(new PropertyValueFactory<>("jobRating"));
        staffIdColumn.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        staffNameColumn.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        staffRoleColumn.setCellValueFactory(new PropertyValueFactory<>("staffRole"));
        overallColumn.setCellValueFactory(new PropertyValueFactory<>("overallRating"));
        teamworkColumn.setCellValueFactory(new PropertyValueFactory<>("teamworkRating"));
        workQualityColumn.setCellValueFactory(new PropertyValueFactory<>("workQualityRating"));
        punctualityColumn.setCellValueFactory(new PropertyValueFactory<>("punctualityRating"));

        StreamMapper stream = new StreamMapper();
        List<Object> userObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.UserObjects)
        );

        List<StaffAccount> staffAccounts = userObjects.stream()
                .filter(obj -> obj instanceof StaffAccount)
                .map(obj -> (StaffAccount) obj)
                .filter(obj -> obj.getUserId() != 1001)
                .toList();

        staffAccountsList.addAll(staffAccounts);

        for(StaffAccount staffAccount : staffAccounts){
            staffIDCombobox.getItems().add(staffAccount.getUserId());
        }

        jobRatingCombobox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        wokrQualityCombobox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        teamworkRatingCombobox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        punctualityRatingCombobox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Object> performanceReviewObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.PerformanceReviewObjects)
        );

        List<PerformanceReview> performanceReviews = performanceReviewObjects.stream()
                .filter(obj -> obj instanceof PerformanceReview)
                .map(obj -> (PerformanceReview) obj)
                .toList();

        ObservableList<PerformanceReview> performanceReviewsList = FXCollections.observableList(performanceReviews);
        performanceReivewsTableview.setItems(performanceReviewsList);
    }

    @javafx.fxml.FXML
    public void onReviewStaffButtonClick(ActionEvent actionEvent) throws IOException {
        int staffId = staffIDCombobox.getValue();
        String staffRole = staffRoleLabel.getText();
        String feedback = feedbackTextArea.getText();
        int jobRating = jobRatingCombobox.getValue();
        int teamworkRating = teamworkRatingCombobox.getValue();
        int workQualityRating = wokrQualityCombobox.getValue();
        int punctualityRating = punctualityRatingCombobox.getValue();

        String staffName = selectedStaffAccount.getFullName();

        PerformanceReview performanceReview = new PerformanceReview(
                staffId,
                staffName,
                staffRole,
                jobRating,
                teamworkRating,
                punctualityRating,
                workQualityRating,
                feedback
        );

        try {
            StreamMapper stream = new StreamMapper();
            ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.PerformanceReviewObjects), true);
            oos.writeObject(performanceReview);
            oos.close();

            Utilities.showAlert("Successfully reviewed staff!", Alert.AlertType.INFORMATION);

//            feedbackTextArea.clear();
//            staffIDCombobox.setValue(null);
//            jobRatingCombobox.setValue(null);
//            wokrQualityCombobox.setValue(null);
//            teamworkRatingCombobox.setValue(null);
//            punctualityRatingCombobox.setValue(null);
//            staffRoleLabel.setText("Select ID to get role");
        } catch(Exception e) {
            Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
        }
    }

    @javafx.fxml.FXML
    public void onStaffIdComboboxSelect(ActionEvent actionEvent) {
        int staffId = staffIDCombobox.getValue();

        Optional<StaffAccount> staffRole = staffAccountsList.stream()
                .filter(obj -> obj.getUserId() == staffId)
                .findFirst();

        selectedStaffAccount = staffRole.orElse(null);
        staffRole.ifPresent(staffAccount -> staffRoleLabel.setText(staffAccount.getUserType()));
    }
}