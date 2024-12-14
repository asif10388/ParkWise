package csc305.parkwise.Users.Asif.CampgroundManager.OccupancyReports;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.Campsite;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.CampsiteBooking;
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
import java.util.Optional;
import java.util.Random;

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class OccupancyReportsController {
	@javafx.fxml.FXML
	private DatePicker endDatePicker;
	@javafx.fxml.FXML
	private DatePicker startDatePicker;
	@javafx.fxml.FXML
	private ComboBox<Integer> selectReportCombobox;
	@javafx.fxml.FXML
	private TableColumn<OccupancyReport, Integer> occupancyReportIdColumn;
	@javafx.fxml.FXML
	private Label startDateLabel;
	@javafx.fxml.FXML
	private TableView<OccupancyReport> occupancyReportsTableview;
	@javafx.fxml.FXML
	private Label endDateLabel;
	@javafx.fxml.FXML
	private TableColumn<OccupancyReport, Integer> occupancyReportsCampsiteReservedColumn;

	public List<CampsiteBooking> getCampsiteBookingsFromFile() throws IOException {
		StreamMapper stream = new StreamMapper();
		List<Object> campsiteBookingObjects = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.CampsiteBookingObjects));

		return campsiteBookingObjects.stream()
				.filter(obj -> obj instanceof CampsiteBooking)
				.map(obj -> (CampsiteBooking) obj)
				.toList();
	}

	public List<Campsite> getCampsitesFromFile() throws IOException {
		StreamMapper stream = new StreamMapper();
		List<Object> campsiteObjects = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.CampsiteObjects));

		return campsiteObjects.stream()
				.filter(obj -> obj instanceof Campsite)
				.map(obj -> (Campsite) obj)
				.toList();
	}

	public List<OccupancyReport> getOccupancyReportsFromFile() throws IOException {
		StreamMapper stream = new StreamMapper();
		List<Object> occupancyReportObjects = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.OccupancyReportObjects));

		return occupancyReportObjects.stream()
				.filter(obj -> obj instanceof OccupancyReport)
				.map(obj -> (OccupancyReport) obj)
				.toList();
	}

	@javafx.fxml.FXML
	public void initialize() throws IOException {
		occupancyReportIdColumn.setCellValueFactory(new PropertyValueFactory<>("reportId"));
		occupancyReportsCampsiteReservedColumn.setCellValueFactory(new PropertyValueFactory<>("totalReserved"));

		List<OccupancyReport> occupancyReports = getOccupancyReportsFromFile();
		occupancyReports.forEach(occupancyReport -> selectReportCombobox.getItems().add(occupancyReport.getReportId()));

		endDatePicker.setDisable(true);
	}

	@javafx.fxml.FXML
	public void onGenerateReportButtonClick(ActionEvent actionEvent) throws IOException {
		Random random = new Random();
		int reportId = random.nextInt(1000000);
		ArrayList<Integer> campsiteIds = new ArrayList<>();
		List<CampsiteBooking> campsiteBookings = getCampsiteBookingsFromFile().stream()
				.filter(campsiteBooking -> campsiteBooking.getCheckInDate().isAfter(startDatePicker.getValue()) &&
						campsiteBooking.getCheckOutDate().isBefore(endDatePicker.getValue()))
				.toList();

		campsiteBookings.forEach(campsiteBooking -> campsiteIds.add(campsiteBooking.getCampsiteId()));

		OccupancyReport occupancyReport = new OccupancyReport(
				reportId,
				campsiteBookings.size(),
				endDatePicker.getValue(),
				startDatePicker.getValue(),
				campsiteIds);

		try {
			StreamMapper stream = new StreamMapper();
			ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.OccupancyReportObjects),
					false);

			oos.writeObject(occupancyReport);
			oos.close();

			Utilities.showAlert("Report added successfully!", Alert.AlertType.INFORMATION);

			List<OccupancyReport> occupancyReports = getOccupancyReportsFromFile();
			occupancyReports.forEach(report -> selectReportCombobox.getItems().add(report.getReportId()));
		} catch (IOException e) {
			Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
		}
	}

	@javafx.fxml.FXML
	public void onStartDateSelected(ActionEvent actionEvent) {
		if (startDatePicker.getValue().isAfter(LocalDate.now())) {
			Utilities.showAlert("Start date cannot be in the future", Alert.AlertType.ERROR);
			startDatePicker.setValue(null);
			return;
		}

		endDatePicker.setDisable(false);
	}

	@javafx.fxml.FXML
	public void onEndDateSelected(ActionEvent actionEvent) {
		if (endDatePicker.getValue().isBefore(startDatePicker.getValue())) {
			Utilities.showAlert("End date cannot be before start date or after current date", Alert.AlertType.ERROR);
			endDatePicker.setValue(null);
			return;
		}
	}

	@javafx.fxml.FXML
	public void onReportSelected(ActionEvent actionEvent) throws IOException {
		Optional<OccupancyReport> findOccupancyReport = getOccupancyReportsFromFile().stream()
				.filter(occupancyReport -> occupancyReport.getReportId() == selectReportCombobox.getValue())
				.findFirst();

		if (findOccupancyReport.isPresent()) {
			startDateLabel.setText(findOccupancyReport.get().getStartDate().toString());
			endDateLabel.setText(findOccupancyReport.get().getEndDate().toString());

			ObservableList<OccupancyReport> occupancyReportObservableList = FXCollections
					.observableList(getOccupancyReportsFromFile());
			occupancyReportsTableview.setItems(occupancyReportObservableList);
		}

	}
}