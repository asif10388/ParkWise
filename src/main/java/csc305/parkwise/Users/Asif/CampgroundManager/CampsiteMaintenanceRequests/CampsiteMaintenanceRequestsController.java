package csc305.parkwise.Users.Asif.CampgroundManager.CampsiteMaintenanceRequests;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.Campsite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CampsiteMaintenanceRequestsController
{
    @javafx.fxml.FXML
    private ComboBox<String> selectPriorityCombobox;
    @javafx.fxml.FXML
    private ComboBox<String> selectCampsiteCombobox;
    @javafx.fxml.FXML
    private TextArea issueDescriptionTextarea;

    private int userId;
    @javafx.fxml.FXML
    private TableColumn<CampsiteMaintenanceRequest, String> campsiteMaintenanceStatusColumn;
    @javafx.fxml.FXML
    private TableView<CampsiteMaintenanceRequest> campsiteMaintenanceRequestsTableview;
    @javafx.fxml.FXML
    private TableColumn<CampsiteMaintenanceRequest, String> campsiteMaintenancePriorityColumn;
    @javafx.fxml.FXML
    private TableColumn<CampsiteMaintenanceRequest, String> campsiteMaintenanceIssueDescriptionColumn;
    @javafx.fxml.FXML
    private TableColumn<CampsiteMaintenanceRequest, Integer> campsiteMaintenanceRequestIdColumn;
    @javafx.fxml.FXML
    private TableColumn<CampsiteMaintenanceRequest, String> campsiteMaintenanceCampsiteColumn;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Campsite> getCampsitesFromFile() throws IOException {
        StreamMapper stream = new StreamMapper();
        List<Object> campsiteObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.CampsiteObjects)
        );

        return campsiteObjects.stream()
                .filter(obj -> obj instanceof Campsite)
                .map(obj -> (Campsite) obj)
                .toList();
    }

    public List<CampsiteMaintenanceRequest> getCampsiteMaintenanceRequestsFromFile() throws IOException{
        StreamMapper stream = new StreamMapper();
        List<Object> campsiteMaintenanceRequestObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.CampgroundMaintenanceRequestObjects)
        );

        return campsiteMaintenanceRequestObjects.stream()
                .filter(obj -> obj instanceof CampsiteMaintenanceRequest)
                .map(obj -> (CampsiteMaintenanceRequest) obj)
                .toList();
    }

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        campsiteMaintenanceStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        campsiteMaintenancePriorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        campsiteMaintenanceRequestIdColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        campsiteMaintenanceCampsiteColumn.setCellValueFactory(new PropertyValueFactory<>("campsiteId"));
        campsiteMaintenanceIssueDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        List<Campsite> campsites =  getCampsitesFromFile();
        List<CampsiteMaintenanceRequest> campsiteMaintenanceRequests =  getCampsiteMaintenanceRequestsFromFile();

        selectPriorityCombobox.getItems().addAll("High", "Medium", "Low");
        campsites.forEach(obj -> selectCampsiteCombobox.getItems().add(obj.getCampsiteName()));

        ObservableList<CampsiteMaintenanceRequest> campsiteMaintenanceRequestList = FXCollections.observableList(campsiteMaintenanceRequests);
        campsiteMaintenanceRequestsTableview.setItems(campsiteMaintenanceRequestList);
    }

    @javafx.fxml.FXML
    public void onSubmitRequestButtonClick(ActionEvent actionEvent) throws IOException {
        if(selectCampsiteCombobox.getValue() == null || selectPriorityCombobox.getValue() == null && issueDescriptionTextarea.getText().isBlank()){
            Utilities.showAlert("Please fill out all fields", Alert.AlertType.ERROR);
        }

        String priority = selectPriorityCombobox.getValue();
        String campsiteName = selectCampsiteCombobox.getValue();
        String issueDescription = issueDescriptionTextarea.getText();

        StreamMapper stream = new StreamMapper();
        List<Object> campsiteObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.CampsiteObjects)
        );

        Optional<Campsite> findCampsite = campsiteObjects.stream()
                .filter(obj -> obj instanceof Campsite)
                .map(obj -> (Campsite) obj)
                .filter(obj -> obj.getCampsiteName().equals(campsiteName))
                .findFirst();

        if(findCampsite.isPresent()){
            Campsite campsite = findCampsite.get();
            CampsiteMaintenanceRequest campsiteMaintenanceRequest = new CampsiteMaintenanceRequest(
                    this.getUserId(),
                    101,
                    "Pending",
                    campsite.getCampsiteId(),
                    priority,
                    issueDescription
            );

            boolean submitRequest = campsiteMaintenanceRequest.submitMaintenanceRequest();

            if (submitRequest) {
                Utilities.showAlert("Successfully submitted request!", Alert.AlertType.INFORMATION);
            } else {
                Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
            }
        }

    }
}