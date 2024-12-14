package csc305.parkwise.Users.Asif.CampgroundManager;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.Campsite;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.CampsiteBooking;
import csc305.parkwise.Users.Asif.CampgroundManager.RestockSuppliesRequests.RestockSuppliesRequest;
import csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts.StaffAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class AssignRangerController {

	@javafx.fxml.FXML
	private Label bookingStatusLabel;
	@javafx.fxml.FXML
	private Label bookerNameLabel;
	@javafx.fxml.FXML
	private ComboBox<Integer> selectCampsiteBookingCombobox;
	@javafx.fxml.FXML
	private Label bookingCampsiteLabel;
	@javafx.fxml.FXML
	private Label checkoutDateLabel;
	@javafx.fxml.FXML
	private Label durationOfStayLabel;
	@javafx.fxml.FXML
	private Label checkInDateLabel;
	@javafx.fxml.FXML
	private TableColumn<CampsiteBooking, Integer> campsiteBookingIdColumn;
	@javafx.fxml.FXML
	private TableView<CampsiteBooking> campsiteBookingTableview;
	@javafx.fxml.FXML
	private TableColumn<CampsiteBooking, Integer> campsiteBookingCampsiteIdColumn;
	@javafx.fxml.FXML
	private TableColumn<CampsiteBooking, Integer> campsiteBookingUserIdColumn;
	@javafx.fxml.FXML
	private TableColumn<CampsiteBooking, LocalDate> campsiteBookingCheckOutDateColumn;
	@javafx.fxml.FXML
	private ComboBox<Integer> selectRangerCombobox;
	@javafx.fxml.FXML
	private TableColumn<CampsiteBooking, Integer> campsiteBookingRangerIdColumn;
	@javafx.fxml.FXML
	private TableColumn<CampsiteBooking, LocalDate> campsiteBookingCheckInDateColumn;
	@javafx.fxml.FXML
	private Button assignCamperToSiteButton;

	public List<CampsiteBooking> getCampsiteBookingsFromFile() throws IOException {
		StreamMapper stream = new StreamMapper();
		List<Object> campsiteBookingObjects = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.CampsiteBookingObjects));

		return campsiteBookingObjects.stream()
				.filter(obj -> obj instanceof CampsiteBooking)
				.map(obj -> (CampsiteBooking) obj)
				.toList();
	}

	public List<StaffAccount> getStaffAccountsFromFile() throws IOException {
		StreamMapper stream = new StreamMapper();
		List<Object> staffAccounts = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.StaffObjects));

		return staffAccounts.stream()
				.filter(obj -> obj instanceof StaffAccount)
				.map(obj -> (StaffAccount) obj)
				.filter(staffAccount -> Objects.equals(staffAccount.getUserType(), "Park Ranger"))
				.toList();
	}

	public void loadCampsiteBookings() throws IOException {
		List<CampsiteBooking> campsiteBookings = getCampsiteBookingsFromFile();
		ObservableList<CampsiteBooking> campsiteBookingList = FXCollections.observableList(campsiteBookings);
		campsiteBookingTableview.setItems(campsiteBookingList);
	}

	@javafx.fxml.FXML
	public void initialize() throws IOException {
		campsiteBookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
		campsiteBookingUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
		campsiteBookingRangerIdColumn.setCellValueFactory(new PropertyValueFactory<>("parkRangerId"));
		campsiteBookingCampsiteIdColumn.setCellValueFactory(new PropertyValueFactory<>("campsiteId"));
		campsiteBookingCheckInDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
		campsiteBookingCheckOutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

		List<CampsiteBooking> campsiteBookings = getCampsiteBookingsFromFile();
		campsiteBookings.forEach(
				campsiteBooking -> selectCampsiteBookingCombobox.getItems().add(campsiteBooking.getBookingId()));

		loadCampsiteBookings();

		selectRangerCombobox.setDisable(true);
		assignCamperToSiteButton.setDisable(true);

	}

	@javafx.fxml.FXML
	public void onAssignCamperToSiteButtonClick(ActionEvent actionEvent) throws IOException {
		int parkRangerId = selectRangerCombobox.getValue();
		int campsiteBookingId = selectCampsiteBookingCombobox.getValue();

		List<CampsiteBooking> campsiteBookings = getCampsiteBookingsFromFile();

		Optional<CampsiteBooking> findCampsiteBooking = campsiteBookings.stream()
				.filter(campsiteBooking -> campsiteBooking.getBookingId() == selectCampsiteBookingCombobox.getValue())
				.findFirst();

		if (findCampsiteBooking.isPresent()) {
			findCampsiteBooking.get().setParkRangerId(parkRangerId);

			try {
				StreamMapper stream = new StreamMapper();
				ObjectOutputStream oos = getObjectOutputStream(
						stream.getObjectStream(ObjectStreams.CampsiteBookingObjects), true);

				for (CampsiteBooking campsiteBooking : campsiteBookings) {
					oos.writeObject(campsiteBooking);
				}

				oos.close();

				Utilities.showAlert("Successfully assigned new ranger to campsite booking",
						Alert.AlertType.INFORMATION);

				loadCampsiteBookings();
			} catch (IOException e) {
				Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
			}
		}
	}

	@javafx.fxml.FXML
	public void onCampsiteBookingSelected(ActionEvent actionEvent) throws IOException {
		List<CampsiteBooking> campsiteBookings = getCampsiteBookingsFromFile();

		Optional<CampsiteBooking> findCampsite = campsiteBookings.stream()
				.filter(campsiteBooking -> campsiteBooking.getBookingId() == selectCampsiteBookingCombobox.getValue())
				.findFirst();

		if (findCampsite.isPresent()) {
			List<StaffAccount> staffAccounts = getStaffAccountsFromFile().stream()
					.filter(staffAccount -> staffAccount.getUserId() != findCampsite.get().getParkRangerId())
					.toList();

			bookerNameLabel.setText(String.valueOf(findCampsite.get().getBookingId()));
			checkInDateLabel.setText(String.valueOf(findCampsite.get().getCheckInDate()));
			checkoutDateLabel.setText(String.valueOf(findCampsite.get().getCheckOutDate()));
			bookingCampsiteLabel.setText(String.valueOf(findCampsite.get().getCampsiteId()));
			bookingStatusLabel.setText(String.valueOf(findCampsite.get().getBookingStatus()));
			durationOfStayLabel.setText(String.valueOf(findCampsite.get().getDurationInDays()));

			selectRangerCombobox.setDisable(false);
			staffAccounts.forEach(staffAccount -> selectRangerCombobox.getItems().add(staffAccount.getUserId()));
		}
	}

	@javafx.fxml.FXML
	public void onRangerSelected(ActionEvent actionEvent) {
		assignCamperToSiteButton.setDisable(false);
	}
}