package csc305.parkwise.Users.Asif.CampgroundManager.RestockSuppliesRequests;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import csc305.parkwise.Users.Asif.CampgroundManager.CampsiteMaintenanceRequests.CampsiteMaintenanceRequest;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.Campsite;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.Equipment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

public class RestockSuppliesRequestController {
	@javafx.fxml.FXML
	private TextField restockQuantityTextInput;
	@javafx.fxml.FXML
	private ComboBox<String> restockPriorityLevelCombobox;
	@javafx.fxml.FXML
	private ComboBox<String> restockItemNameCombobox;
	@javafx.fxml.FXML
	private TextField requiredAmountTextInput;
	@javafx.fxml.FXML
	private TextArea reasonOfRequestTextarea;

	private int userId;
	@javafx.fxml.FXML
	private TableColumn<RestockSuppliesRequest, Integer> restockSuppliesItemIdColumn;
	@javafx.fxml.FXML
	private TableColumn<RestockSuppliesRequest, Integer> restockSuppliesQuantityColumn;
	@javafx.fxml.FXML
	private TableColumn<RestockSuppliesRequest, Integer> restockSuppliesRequiredAmountColumn;
	@javafx.fxml.FXML
	private TableView<RestockSuppliesRequest> restockSuppliesRequestsTableview;
	@javafx.fxml.FXML
	private TableColumn<RestockSuppliesRequest, LocalDate> restockSuppliesCreatedAtColumn;
	@javafx.fxml.FXML
	private TableColumn<RestockSuppliesRequest, String> restockSuppliesStatusColumn;
	@javafx.fxml.FXML
	private TableColumn<RestockSuppliesRequest, Integer> restockSuppliesRequestIdColumn;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Equipment> getEquipmentsFromFile() throws IOException {
		StreamMapper stream = new StreamMapper();
		List<Object> equipmentObjects = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.EquipmentObjects));

		return equipmentObjects.stream()
				.filter(obj -> obj instanceof Equipment)
				.map(obj -> (Equipment) obj)
				.toList();
	}

	public List<RestockSuppliesRequest> getRestockSuppliesRequestsFromFile() throws IOException {
		StreamMapper stream = new StreamMapper();
		List<Object> restockSuppliesRequestObjects = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.RestockSuppliesRequestObjects));

		return restockSuppliesRequestObjects.stream()
				.filter(obj -> obj instanceof RestockSuppliesRequest)
				.map(obj -> (RestockSuppliesRequest) obj)
				.toList();
	}

	public void loadRestockSuppliesRequests() throws IOException {
		List<RestockSuppliesRequest> restockSuppliesRequests = getRestockSuppliesRequestsFromFile();

		ObservableList<RestockSuppliesRequest> restockSuppliesRequestList = FXCollections
				.observableList(restockSuppliesRequests);
		restockSuppliesRequestsTableview.setItems(restockSuppliesRequestList);
	}

	@javafx.fxml.FXML
	public void initialize() throws IOException {
		restockSuppliesItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		restockSuppliesStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		restockSuppliesQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		restockSuppliesCreatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
		restockSuppliesRequestIdColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
		restockSuppliesRequiredAmountColumn.setCellValueFactory(new PropertyValueFactory<>("requiredAmount"));

		List<Equipment> equipments = getEquipmentsFromFile();
		restockPriorityLevelCombobox.getItems().addAll("High", "Medium", "Low");
		equipments.forEach(equipment -> restockItemNameCombobox.getItems().add(equipment.getItemName()));

		loadRestockSuppliesRequests();
	}

	@javafx.fxml.FXML
	public void onSubmitRequestButtonClick(ActionEvent actionEvent) throws IOException {
		if (restockItemNameCombobox.getValue() == null ||
				restockQuantityTextInput.getText().isBlank() ||
				restockPriorityLevelCombobox.getValue() == null ||
				requiredAmountTextInput.getText().isBlank()) {
			Utilities.showAlert("Please fill out all necessary fields", Alert.AlertType.ERROR);
			return;
		}

		if (Integer.parseInt(restockQuantityTextInput.getText()) <= 0) {
			Utilities.showAlert("Please enter a quantity greater than 0", Alert.AlertType.ERROR);
			return;
		}

		if (Integer.parseInt(requiredAmountTextInput.getText()) <= 0) {
			Utilities.showAlert("Amount must be greater than 0", Alert.AlertType.ERROR);
			return;
		}

		String itemName = restockItemNameCombobox.getValue();
		String requestReason = reasonOfRequestTextarea.getText();
		String priorityLevel = restockPriorityLevelCombobox.getValue();
		int quantity = Integer.parseInt(restockQuantityTextInput.getText());
		int requiredAmount = Integer.parseInt(requiredAmountTextInput.getText());

		StreamMapper stream = new StreamMapper();
		List<Object> equipmentObjects = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.EquipmentObjects));

		Optional<Equipment> findEquipment = equipmentObjects.stream()
				.filter(obj -> obj instanceof Equipment)
				.map(obj -> (Equipment) obj)
				.filter(obj -> obj.getItemName().equals(itemName))
				.findFirst();

		if (findEquipment.isPresent()) {
			Random random = new Random();
			int requestId = random.nextInt(1000000);

			Equipment equipment = findEquipment.get();
			RestockSuppliesRequest restockSuppliesRequest = new RestockSuppliesRequest(
					this.getUserId(),
					equipment.getItemId(),
					quantity,
					requestId,
					"Pending",
					priorityLevel,
					"Restock",
					requiredAmount,
					LocalDate.now(),
					requestReason);

			boolean submitRequest = restockSuppliesRequest.submitRestockSuppliesRequest();

			if (submitRequest) {
				Utilities.showAlert("Successfully submitted restock request!", Alert.AlertType.INFORMATION);
				loadRestockSuppliesRequests();
			} else {
				Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
			}
		}

	}
}