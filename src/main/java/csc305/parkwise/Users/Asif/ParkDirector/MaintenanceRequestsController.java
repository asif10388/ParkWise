package csc305.parkwise.Users.Asif.ParkDirector;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import csc305.parkwise.Users.Asif.CampgroundManager.CampsiteMaintenanceRequests.CampsiteMaintenanceRequest;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.Campsite;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Optional;

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class MaintenanceRequestsController
{
    @javafx.fxml.FXML
    private TextField priorityLevelInput;
    @javafx.fxml.FXML
    private TextArea detailedDescriptionTextfield;
    @javafx.fxml.FXML
    private ToggleGroup approvalDecisionRadioGroup;
    @javafx.fxml.FXML
    private TextArea commentsTextarea;
    @javafx.fxml.FXML
    private TableView stattAccountsTableview;
    @javafx.fxml.FXML
    private TextField requestedByInput;
    @javafx.fxml.FXML
    private ComboBox<Integer> selectRequestCombobox;
    @javafx.fxml.FXML
    private TextField requestTypeInput;
    @javafx.fxml.FXML
    private DatePicker requestedDatePicker;
    @javafx.fxml.FXML
    private RadioButton rejectRadioBtn;
    @javafx.fxml.FXML
    private RadioButton approveRadioBtn;

    public List<CampsiteMaintenanceRequest> getCampgroundMaintenanceRequestsFromFile() throws IOException {
        StreamMapper stream = new StreamMapper();
        List<Object> campgroundMaintenanceRequestObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.CampgroundMaintenanceRequestObjects)
        );

        return campgroundMaintenanceRequestObjects.stream()
                .filter(obj -> obj instanceof CampsiteMaintenanceRequest)
                .map(obj -> (CampsiteMaintenanceRequest) obj)
                .toList();
    }

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        approvalDecisionRadioGroup = new ToggleGroup();

        approveRadioBtn.setUserData("Approved");
        approveRadioBtn.setToggleGroup(approvalDecisionRadioGroup);

        rejectRadioBtn.setUserData("Rejected");
        rejectRadioBtn.setToggleGroup(approvalDecisionRadioGroup);

        List<CampsiteMaintenanceRequest> maintenanceRequests =  getCampgroundMaintenanceRequestsFromFile();
        maintenanceRequests.forEach(maintenanceRequest -> selectRequestCombobox.getItems().add(maintenanceRequest.getRequestId()));
    }

    @javafx.fxml.FXML
    public void onEvaluateRequestButtonClick(ActionEvent actionEvent) throws IOException {
        String selectedApprovalStatus = approvalDecisionRadioGroup.getSelectedToggle().getUserData().toString();
        String comments = commentsTextarea.getText();

        if(selectedApprovalStatus == null){
            Utilities.showAlert("Please select approve or reject!", Alert.AlertType.ERROR);
        }

        List<CampsiteMaintenanceRequest> maintenanceRequests =  getCampgroundMaintenanceRequestsFromFile();

        Optional<CampsiteMaintenanceRequest> findMaintenanceRequest = maintenanceRequests.stream()
                .filter(obj -> obj.getRequestId() == selectRequestCombobox.getValue())
                .findFirst();

        if(findMaintenanceRequest.isPresent()){
            findMaintenanceRequest.get().setStatus(selectedApprovalStatus);

            try {
                StreamMapper stream = new StreamMapper();
                ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.CampgroundMaintenanceRequestObjects), true);

                for (CampsiteMaintenanceRequest maintenanceRequest : maintenanceRequests) {
                    oos.writeObject(maintenanceRequest);
                }

                oos.close();
                Utilities.showAlert("Request " + selectedApprovalStatus + " successfully!", Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
            }
        }
    }

    @javafx.fxml.FXML
    public void onMaintenanceRequestSelected(ActionEvent actionEvent) throws IOException {
        int selectedRequestId = selectRequestCombobox.getValue();
        List<CampsiteMaintenanceRequest> maintenanceRequests =  getCampgroundMaintenanceRequestsFromFile();

        Optional<CampsiteMaintenanceRequest> findMaintenanceRequest = maintenanceRequests.stream()
                .filter(obj -> obj.getRequestId() == selectedRequestId)
                .findFirst();

        if (findMaintenanceRequest.isPresent()) {
            System.out.println(findMaintenanceRequest.get().getRequestId());

            priorityLevelInput.setText(findMaintenanceRequest.get().getPriority());
            requestTypeInput.setText(findMaintenanceRequest.get().getRequestType());
            requestedDatePicker.setValue(findMaintenanceRequest.get().getRequestDate());
            requestedByInput.setText(String.valueOf(findMaintenanceRequest.get().getUserId()));
            detailedDescriptionTextfield.setText(findMaintenanceRequest.get().getDescription());
        }
    }
}