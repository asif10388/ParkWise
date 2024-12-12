package csc305.parkwise.Users.Asif.CampgroundManager;

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
import java.io.ObjectOutputStream;
import java.util.List;

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class CampsiteAvailabilityController
{
    @javafx.fxml.FXML
    private ToggleGroup campsiteStatusToggleGroup;
    @javafx.fxml.FXML
    private TextArea maintenanceReasonTextarea;
    @javafx.fxml.FXML
    private ComboBox<String> campsiteCombobox;
    @javafx.fxml.FXML
    private TableColumn<Campsite, Integer> campsiteIdColumn;
    @javafx.fxml.FXML
    private TableView<Campsite> campsiteTableview;
    @javafx.fxml.FXML
    private TableColumn<Campsite, String> campsiteStatusColumn;
    @javafx.fxml.FXML
    private TableColumn<Campsite, String> campsiteNameColumn;
    @javafx.fxml.FXML
    private TableColumn<Campsite, String> campsiteLocationColumn;
    @javafx.fxml.FXML
    private RadioButton underMaintenanceRadioBtn;
    @javafx.fxml.FXML
    private RadioButton availableRadioBtn;
    @javafx.fxml.FXML
    private RadioButton reservedRadioBtn;

    public List<Campsite> getCampsitesFromFile() throws IOException{
        StreamMapper stream = new StreamMapper();
        List<Object> campsiteObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.CampsiteObjects)
        );

        return campsiteObjects.stream()
                .filter(obj -> obj instanceof Campsite)
                .map(obj -> (Campsite) obj)
                .toList();
    }

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        campsiteIdColumn.setCellValueFactory(new PropertyValueFactory<>("campsiteId"));
        campsiteNameColumn.setCellValueFactory(new PropertyValueFactory<>("campsiteName"));
        campsiteStatusColumn.setCellValueFactory(new PropertyValueFactory<>("campsiteStatus"));
        campsiteLocationColumn.setCellValueFactory(new PropertyValueFactory<>("campsiteLocation"));

        List<Campsite> campsites =  getCampsitesFromFile();

        ObservableList<Campsite> campsiteList = FXCollections.observableList(campsites);
        campsiteTableview.setItems(campsiteList);

        for (Campsite campsite : campsites){
            campsiteCombobox.getItems().add(campsite.getCampsiteName());
        }

        campsiteStatusToggleGroup = new ToggleGroup();

        reservedRadioBtn.setUserData("Reserved");
        reservedRadioBtn.setToggleGroup(campsiteStatusToggleGroup);

        availableRadioBtn.setUserData("Available");
        availableRadioBtn.setToggleGroup(campsiteStatusToggleGroup);

        underMaintenanceRadioBtn.setUserData("Under Maintenance");
        underMaintenanceRadioBtn.setToggleGroup(campsiteStatusToggleGroup);

        maintenanceReasonTextarea.setDisable(true);
    }

    @javafx.fxml.FXML
    public void onUpdateStatusButtonClick(ActionEvent actionEvent) throws IOException {
        String campsiteName = campsiteCombobox.getValue();
        String maintenanceReason = maintenanceReasonTextarea.getText();
        String selectedStatus = campsiteStatusToggleGroup.getSelectedToggle().getUserData().toString();

        List<Campsite> campsites = getCampsitesFromFile();
        boolean isUpdated = false;

        for (Campsite campsite : campsites) {
            if (campsite.getCampsiteName().equals(campsiteName)) {
                campsite.setMaintenanceReason(maintenanceReason);
                campsite.setCampsiteStatus(selectedStatus);

                isUpdated = true;
                break;
            }
        }

        if (!isUpdated) {
            Utilities.showAlert("Campsite not found!", Alert.AlertType.ERROR);
            return;
        }

        try {
            StreamMapper stream = new StreamMapper();
            ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.CampsiteObjects), true);

            for (Campsite campsite : campsites) {
                oos.writeObject(campsite);
            }

            oos.close();
            Utilities.showAlert("Campsite updated successfully!", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
        }
    }

    @javafx.fxml.FXML
    public void onRadioButtonSelected(ActionEvent actionEvent) {
        String selectedStatus = campsiteStatusToggleGroup.getSelectedToggle().getUserData().toString();
        if(selectedStatus.equals("Under Maintenance")){
            maintenanceReasonTextarea.setDisable(false);
        }
    }
}