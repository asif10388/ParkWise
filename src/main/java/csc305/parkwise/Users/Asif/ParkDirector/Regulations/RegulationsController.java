package csc305.parkwise.Users.Asif.ParkDirector.Regulations;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import csc305.parkwise.Users.Asif.ParkDirector.Announcements.Announcement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class RegulationsController
{
    @javafx.fxml.FXML
    private TextField ruleSummaryInput;
    @javafx.fxml.FXML
    private ComboBox<String> regulationTypeCombobox;
    @javafx.fxml.FXML
    private TextArea ruleDescriptionInput;
    @javafx.fxml.FXML
    private DatePicker effectiveDatePicker;
    @javafx.fxml.FXML
    private CheckBox educationalGroupLeaderCheckbox;
    @javafx.fxml.FXML
    private CheckBox wildlifeBiologistCheckbox;
    @javafx.fxml.FXML
    private CheckBox ticketOfficerCheckbox;
    @javafx.fxml.FXML
    private CheckBox parkRangerCheckbox;
    @javafx.fxml.FXML
    private CheckBox tourGuideCheckbox;
    @javafx.fxml.FXML
    private CheckBox parkVisitorCheckbox;
    @javafx.fxml.FXML
    private CheckBox campgroundManagerCheckbox;
    @javafx.fxml.FXML
    private TableView<Regulation> regulationsTableview;
    @javafx.fxml.FXML
    private TableColumn<Regulation, String> ruleStatusColumn;
    @javafx.fxml.FXML
    private TableColumn<Regulation, LocalDate> ruleDateModifiedColumn;
    @javafx.fxml.FXML
    private TableColumn<Regulation, String> ruleSummaryColumn;
    @javafx.fxml.FXML
    private TableColumn<Regulation, String> regulationTypeColumn;

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        ruleStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        ruleSummaryColumn.setCellValueFactory(new PropertyValueFactory<>("ruleSummary"));
        ruleDateModifiedColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        regulationTypeColumn.setCellValueFactory(new PropertyValueFactory<>("regulationType"));

        StreamMapper stream = new StreamMapper();
        List<Object> regulationObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.RegulationObjects)
        );

        List<Regulation> regulations = regulationObjects.stream()
                .filter(obj -> obj instanceof Regulation)
                .map(obj -> (Regulation) obj)
                .toList();

        ObservableList<Regulation> regulationList = FXCollections.observableList(regulations);
        regulationsTableview.setItems(regulationList);

        regulationTypeCombobox.getItems().addAll("General", "Visitor Conduct", "Staff Procedures");
    }

    @javafx.fxml.FXML
    public void onCreateNewRegulationButtonClick(ActionEvent actionEvent) {
        ArrayList<String> appliesTo = new ArrayList<>();

        String tourGuideCheckboxValue = tourGuideCheckbox.isSelected() ? "Tour Guide" : null;
        String parkRangerCheckboxValue = parkRangerCheckbox.isSelected() ? "Park Ranger" : null;
        String parkVisitorCheckboxValue = parkVisitorCheckbox.isSelected() ? "Park Visitor" : null;
        String ticketOfficerCheckboxValue = ticketOfficerCheckbox.isSelected() ? "Ticket Officer" : null;
        String campgroundManagerCheckboxValue = campgroundManagerCheckbox.isSelected() ? "Campground Manager" : null;
        String wildlifeBiologistCheckboxValue = wildlifeBiologistCheckbox.isSelected() ? "Wildlife Biologist" : null;
        String educationalGroupLeaderCheckboxValue = educationalGroupLeaderCheckbox.isSelected() ? "Educational Group Leader" : null;


        if(ruleSummaryInput.getText().isEmpty() ||
            ruleDescriptionInput.getText().isEmpty() ||
            effectiveDatePicker.getValue() == null ||
            regulationTypeCombobox.getValue() == null
        ) {
            Utilities.showAlert("Missing required fields", Alert.AlertType.ERROR);
            return;
        }

        if(tourGuideCheckboxValue == null && parkRangerCheckboxValue == null && parkVisitorCheckboxValue == null && ticketOfficerCheckboxValue == null
            && campgroundManagerCheckboxValue == null && wildlifeBiologistCheckboxValue == null && educationalGroupLeaderCheckboxValue == null) {
            Utilities.showAlert("Please select at least one user to apply the regulation to", Alert.AlertType.ERROR);
            return;
        }

        if(tourGuideCheckboxValue != null){
            appliesTo.add(tourGuideCheckboxValue);
        }

        if(parkRangerCheckboxValue != null){
            appliesTo.add(parkRangerCheckboxValue);
        }

        if(parkVisitorCheckboxValue != null){
            appliesTo.add(parkVisitorCheckboxValue);
        }

        if(ticketOfficerCheckboxValue != null){
            appliesTo.add(ticketOfficerCheckboxValue);
        }

        if(campgroundManagerCheckboxValue != null){
            appliesTo.add(campgroundManagerCheckboxValue);
        }

        if(wildlifeBiologistCheckboxValue != null){
            appliesTo.add(wildlifeBiologistCheckboxValue);
        }

        if(educationalGroupLeaderCheckboxValue != null){
            appliesTo.add(educationalGroupLeaderCheckboxValue);
        }

        String ruleSummary = ruleSummaryInput.getText();
        String ruleDescription = ruleDescriptionInput.getText();
        LocalDate effectiveDate = effectiveDatePicker.getValue();
        String regulationType = regulationTypeCombobox.getValue();

        Regulation regulation = new Regulation(
            ruleSummary,
            ruleDescription,
            regulationType,
            effectiveDate,
            appliesTo,
            "active",
            LocalDate.now()
        );

        try {
            StreamMapper stream = new StreamMapper();
            ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.RegulationObjects), false);
            oos.writeObject(regulation);
            oos.close();

            Utilities.showAlert("Successfully added regulation!", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
        }

    }
}