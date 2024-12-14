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
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class CampsiteMaintenanceRequestsController {
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
	@javafx.fxml.FXML
	private ComboBox<String> maintenanceTypeCombobox;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public List<CampsiteMaintenanceRequest> getCampsiteMaintenanceRequestsFromFile() throws IOException {
		StreamMapper stream = new StreamMapper();
		List<Object> campsiteMaintenanceRequestObjects = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.CampgroundMaintenanceRequestObjects));

		return campsiteMaintenanceRequestObjects.stream()
				.filter(obj -> obj instanceof CampsiteMaintenanceRequest)
				.map(obj -> (CampsiteMaintenanceRequest) obj)
				.toList();
	}

	public void loadCampsiteMaintenanceRequests() throws IOException {
		List<CampsiteMaintenanceRequest> campsiteMaintenanceRequests = getCampsiteMaintenanceRequestsFromFile();

		ObservableList<CampsiteMaintenanceRequest> campsiteMaintenanceRequestList = FXCollections
				.observableList(campsiteMaintenanceRequests);
		campsiteMaintenanceRequestsTableview.setItems(campsiteMaintenanceRequestList);
	}

	@javafx.fxml.FXML
	public void initialize() throws IOException {
		campsiteMaintenanceStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		campsiteMaintenancePriorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
		campsiteMaintenanceRequestIdColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
		campsiteMaintenanceCampsiteColumn.setCellValueFactory(new PropertyValueFactory<>("campsiteId"));
		campsiteMaintenanceIssueDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

		List<Campsite> campsites = getCampsitesFromFile();
		List<CampsiteMaintenanceRequest> campsiteMaintenanceRequests = getCampsiteMaintenanceRequestsFromFile();

		maintenanceTypeCombobox.getItems().addAll("Repair", "Improvement");
		selectPriorityCombobox.getItems().addAll("High", "Medium", "Low");
		campsites.forEach(obj -> selectCampsiteCombobox.getItems().add(obj.getCampsiteName()));

		loadCampsiteMaintenanceRequests();
	}

	@javafx.fxml.FXML
	public void onSubmitRequestButtonClick(ActionEvent actionEvent) throws IOException {
		if (selectCampsiteCombobox.getValue() == null ||
				selectPriorityCombobox.getValue() == null ||
				issueDescriptionTextarea.getText().isBlank() ||
				maintenanceTypeCombobox.getValue() == null) {
			Utilities.showAlert("Please fill out all fields", Alert.AlertType.ERROR);
			return;
		}

		String priority = selectPriorityCombobox.getValue();
		String campsiteName = selectCampsiteCombobox.getValue();
		String maintenanceType = maintenanceTypeCombobox.getValue();
		String issueDescription = issueDescriptionTextarea.getText();

		StreamMapper stream = new StreamMapper();
		List<Object> campsiteObjects = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.CampsiteObjects));

		Optional<Campsite> findCampsite = campsiteObjects.stream()
				.filter(obj -> obj instanceof Campsite)
				.map(obj -> (Campsite) obj)
				.filter(obj -> obj.getCampsiteName().equals(campsiteName))
				.findFirst();

		if (findCampsite.isPresent()) {
			Random rand = new Random();
			Campsite campsite = findCampsite.get();
			CampsiteMaintenanceRequest campsiteMaintenanceRequest = new CampsiteMaintenanceRequest(
					this.getUserId(),
					rand.nextInt(10000),
					"Pending",
					campsite.getCampsiteId(),
					priority,
					issueDescription,
					maintenanceType,
					LocalDate.now(),
					null);

			try {
				ObjectOutputStream oos = getObjectOutputStream(
						stream.getObjectStream(ObjectStreams.CampgroundMaintenanceRequestObjects), false);
				oos.writeObject(campsiteMaintenanceRequest);
				oos.close();

				loadCampsiteMaintenanceRequests();

				issueDescriptionTextarea.clear();
				selectCampsiteCombobox.setValue(null);
				selectPriorityCombobox.setValue(null);
				maintenanceTypeCombobox.setValue(null);

				Utilities.showAlert("Successfully submitted request!", Alert.AlertType.INFORMATION);
			} catch (IOException e) {

				Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
			}

		}

	}
}