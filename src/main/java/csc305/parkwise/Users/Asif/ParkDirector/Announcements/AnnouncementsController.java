package csc305.parkwise.Users.Asif.ParkDirector.Announcements;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class AnnouncementsController {
	@javafx.fxml.FXML
	private TextArea announcementBodyInput;
	@javafx.fxml.FXML
	private TextField announcementTitleTextInput;
	@javafx.fxml.FXML
	private DatePicker announcementExpirationDatePicker;
	@javafx.fxml.FXML
	private CheckBox educationalGroupLeaderCheckbox;
	@javafx.fxml.FXML
	private CheckBox wildlifeBiologistCheckbox;
	@javafx.fxml.FXML
	private CheckBox ticketOfficerCheckbox;
	@javafx.fxml.FXML
	private CheckBox campgroundManagerCheckbox;
	@javafx.fxml.FXML
	private CheckBox parkRangerCheckbox;
	@javafx.fxml.FXML
	private CheckBox tourGuideCheckbox;
	@javafx.fxml.FXML
	private CheckBox parkVisitorCheckbox;
	@javafx.fxml.FXML
	private TableColumn<Announcement, String> announcementStatusColumn;
	@javafx.fxml.FXML
	private TableColumn<Announcement, LocalDate> announcementDateOfAnnouncementColumn;
	@javafx.fxml.FXML
	private TableColumn<Announcement, String> announcementTitleColumn;
	@javafx.fxml.FXML
	private TableColumn<Announcement, String> announcementAuthorColumn;
	@FXML
	private TableView<Announcement> announcementsTableview;

	public void loadAnnouncements() throws IOException {
		StreamMapper stream = new StreamMapper();
		List<Object> announcementObjects = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.AnnouncementObjects));

		List<Announcement> announcements = announcementObjects.stream()
				.filter(obj -> obj instanceof Announcement)
				.map(obj -> (Announcement) obj)
				.toList();

		ObservableList<Announcement> announcementList = FXCollections.observableList(announcements);
		announcementsTableview.setItems(announcementList);
	}

	@FXML
	public void initialize() throws IOException {
		announcementTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		announcementStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		announcementAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("announcer"));
		announcementDateOfAnnouncementColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

		loadAnnouncements();

	}

	@javafx.fxml.FXML
	public void onRelayAnnouncementButtonClick(ActionEvent actionEvent) throws IOException {
		ArrayList<String> announcementReceivers = new ArrayList<>();

		String tourGuideCheckboxValue = tourGuideCheckbox.isSelected() ? "Tour Guide" : null;
		String parkRangerCheckboxValue = parkRangerCheckbox.isSelected() ? "Park Ranger" : null;
		String parkVisitorCheckboxValue = parkVisitorCheckbox.isSelected() ? "Park Visitor" : null;
		String ticketOfficerCheckboxValue = ticketOfficerCheckbox.isSelected() ? "Ticket Officer" : null;
		String campgroundManagerCheckboxValue = campgroundManagerCheckbox.isSelected() ? "Campground Manager" : null;
		String wildlifeBiologistCheckboxValue = wildlifeBiologistCheckbox.isSelected() ? "Wildlife Biologist" : null;
		String educationalGroupLeaderCheckboxValue = educationalGroupLeaderCheckbox.isSelected()
				? "Educational Group Leader"
				: null;

		if (announcementTitleTextInput.getText().isEmpty() || announcementBodyInput.getText().isEmpty()
				|| announcementExpirationDatePicker.getValue() == null) {
			Utilities.showAlert("Missing required fields", Alert.AlertType.ERROR);
			return;
		}

		if (tourGuideCheckboxValue == null && parkRangerCheckboxValue == null && parkVisitorCheckboxValue == null
				&& ticketOfficerCheckboxValue == null
				&& campgroundManagerCheckboxValue == null && wildlifeBiologistCheckboxValue == null
				&& educationalGroupLeaderCheckboxValue == null) {
			Utilities.showAlert("Please select at least one user to send the announcement to", Alert.AlertType.ERROR);
			return;
		}

		if (tourGuideCheckboxValue != null) {
			announcementReceivers.add(tourGuideCheckboxValue);
		}

		if (parkRangerCheckboxValue != null) {
			announcementReceivers.add(parkRangerCheckboxValue);
		}

		if (parkVisitorCheckboxValue != null) {
			announcementReceivers.add(parkVisitorCheckboxValue);
		}

		if (ticketOfficerCheckboxValue != null) {
			announcementReceivers.add(ticketOfficerCheckboxValue);
		}

		if (campgroundManagerCheckboxValue != null) {
			announcementReceivers.add(campgroundManagerCheckboxValue);
		}

		if (wildlifeBiologistCheckboxValue != null) {
			announcementReceivers.add(wildlifeBiologistCheckboxValue);
		}

		if (educationalGroupLeaderCheckboxValue != null) {
			announcementReceivers.add(educationalGroupLeaderCheckboxValue);
		}

		if (!Announcement.isValidExpirationDate(announcementExpirationDatePicker.getValue())) {
			Utilities.showAlert("Expiration date must be in the future", Alert.AlertType.ERROR);
			return;
		}

		String announcementBody = announcementBodyInput.getText();
		String announcementTitle = announcementTitleTextInput.getText();
		LocalDate announcementExpirationDate = announcementExpirationDatePicker.getValue();

		Announcement announcement = new Announcement(
				announcementBody,
				announcementTitle,
				announcementExpirationDate,
				announcementReceivers,
				"Active",
				Announcement.getAnnouncerName(),
				LocalDate.now());

		try {
			StreamMapper stream = new StreamMapper();
			ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.AnnouncementObjects),
					false);
			oos.writeObject(announcement);
			oos.close();

			announcementBodyInput.clear();
			announcementReceivers.clear();
			announcementTitleTextInput.clear();
			announcementExpirationDatePicker.setValue(null);

			tourGuideCheckbox.setSelected(false);
			parkRangerCheckbox.setSelected(false);
			parkVisitorCheckbox.setSelected(false);
			ticketOfficerCheckbox.setSelected(false);
			campgroundManagerCheckbox.setSelected(false);
			wildlifeBiologistCheckbox.setSelected(false);
			educationalGroupLeaderCheckbox.setSelected(false);

			loadAnnouncements();

			Utilities.showAlert("Successfully added announcement!", Alert.AlertType.INFORMATION);
		} catch (IOException e) {
			Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
		}
	}
}