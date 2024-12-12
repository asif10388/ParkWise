package csc305.parkwise.Users.Asif.ParkDirector.Budgets;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import csc305.parkwise.Users.Asif.CampgroundManager.CampsiteMaintenanceRequests.CampsiteMaintenanceRequest;
import csc305.parkwise.Users.Asif.CampgroundManager.RestockSuppliesRequests.RestockSuppliesRequest;
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

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;


public class BudgetsController
{
    @javafx.fxml.FXML
    private TextArea budgetDescriptionTextarea;
    @javafx.fxml.FXML
    private TextField requestTypeInput;
    @javafx.fxml.FXML
    private TextField requestedAmountInput;
    @javafx.fxml.FXML
    private TextField allocatedBudgetInput;
    @javafx.fxml.FXML
    private ComboBox<Integer> selectRequestCombobox;
    @javafx.fxml.FXML
    private TextField priorityLevelInput;
    @javafx.fxml.FXML
    private DatePicker requestDatePicker;
    @javafx.fxml.FXML
    private TextField requesterIdInput;
    @javafx.fxml.FXML
    private TableColumn<Budget, Integer> budgetIdColumn;
    @javafx.fxml.FXML
    private TableColumn<Budget, String> budgetTypeColumn;
    @javafx.fxml.FXML
    private TableColumn<Budget, String> budgetStatus;
    @javafx.fxml.FXML
    private TableView<Budget> budgetsTableview;
    @javafx.fxml.FXML
    private TableColumn<Budget, LocalDate> budgetAllocationDateColumn;
    @javafx.fxml.FXML
    private TableColumn<Budget, Integer> budgetAllocatedAmountColumn;
    @javafx.fxml.FXML
    private TableColumn<Budget, Integer> budgetRequestedByColumn;

    public List<RestockSuppliesRequest> getRestockRequestsFile() throws IOException {
        StreamMapper stream = new StreamMapper();
        List<Object> restockRequestObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.RestockSuppliesRequestObjects)
        );

        return restockRequestObjects.stream()
                .filter(obj -> obj instanceof RestockSuppliesRequest)
                .map(obj -> (RestockSuppliesRequest) obj)
                .toList();
    }

    public List<Budget> getBudgetsFromFile() throws IOException {
        StreamMapper stream = new StreamMapper();
        List<Object> budgetObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.BudgetObjects)
        );

        return budgetObjects.stream()
                .filter(obj -> obj instanceof Budget)
                .map(obj -> (Budget) obj)
                .toList();
    }

    @javafx.fxml.FXML
    public void initialize() throws IOException {

        budgetIdColumn.setCellValueFactory(new PropertyValueFactory<>("budgetId"));
        budgetStatus.setCellValueFactory(new PropertyValueFactory<>("budgetStatus"));
        budgetTypeColumn.setCellValueFactory(new PropertyValueFactory<>("budgetType"));
        budgetRequestedByColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        budgetAllocationDateColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        budgetAllocatedAmountColumn.setCellValueFactory(new PropertyValueFactory<>("allocatedAmount"));

        List<RestockSuppliesRequest> maintenanceRequests =  getRestockRequestsFile();
        List<Budget> budgets = getBudgetsFromFile();

        maintenanceRequests.forEach(maintenanceRequest -> selectRequestCombobox.getItems().add(maintenanceRequest.getRequestId()));

        ObservableList<Budget> budgetList = FXCollections.observableList(budgets);
        budgetsTableview.setItems(budgetList);
    }

    @javafx.fxml.FXML
    public void onCreateBudgetButtonClick(ActionEvent actionEvent) throws IOException {
        if(allocatedBudgetInput.getText().isEmpty()){
            Utilities.showAlert("Allocated amount cannot be empty!", Alert.AlertType.ERROR);
            return;
        }

        if(Integer.parseInt(allocatedBudgetInput.getText()) <= 0){
            Utilities.showAlert("Please enter a valid amount", Alert.AlertType.ERROR);
            return;
        }

        String budgetDescription = budgetDescriptionTextarea.getText();
        int allocatedAmount = Integer.parseInt(allocatedBudgetInput.getText());

        List<RestockSuppliesRequest> restockSuppliesRequests = getRestockRequestsFile();

        Optional<RestockSuppliesRequest> findRestockSuppliesRequest = restockSuppliesRequests.stream()
                .filter(restockSuppliesRequest -> restockSuppliesRequest.getRequestId() == selectRequestCombobox.getValue())
                .findFirst();

        if(findRestockSuppliesRequest.isPresent()){
            findRestockSuppliesRequest.get().setStatus("Approved");

            Budget newBudget = new Budget(
                    findRestockSuppliesRequest.get().getUserId(),
                    1001,
                    findRestockSuppliesRequest.get().getRequestId(),
                    findRestockSuppliesRequest.get().getRequestType(),
                    "Approved",
                    LocalDate.now(),
                    allocatedAmount,
                    budgetDescription
            );

            try {
                StreamMapper stream = new StreamMapper();
                ObjectOutputStream oos2 = getObjectOutputStream(stream.getObjectStream(ObjectStreams.BudgetObjects), false);
                ObjectOutputStream oos1 = getObjectOutputStream(stream.getObjectStream(ObjectStreams.RestockSuppliesRequestObjects), true);

                for(RestockSuppliesRequest restockSuppliesRequest: restockSuppliesRequests){
                    oos1.writeObject(restockSuppliesRequest);
                }

                oos2.writeObject(newBudget);

                oos1.close();
                oos2.close();

                Utilities.showAlert("Successfully created budget", Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
            }
        }
    }

    @javafx.fxml.FXML
    public void onRequestSelected(ActionEvent actionEvent) throws IOException {
        int selectedRequestId = selectRequestCombobox.getValue();
        List<RestockSuppliesRequest> restockSuppliesRequest =  getRestockRequestsFile();

        Optional<RestockSuppliesRequest> findRestockSuppliesRequest = restockSuppliesRequest.stream()
                .filter(obj -> obj.getRequestId() == selectedRequestId)
                .findFirst();

        if (findRestockSuppliesRequest.isPresent()) {
            priorityLevelInput.setText(findRestockSuppliesRequest.get().getPriority());
            requestTypeInput.setText(findRestockSuppliesRequest.get().getRequestType());
            requestDatePicker.setValue(findRestockSuppliesRequest.get().getCreatedAt());
            requesterIdInput.setText(String.valueOf(findRestockSuppliesRequest.get().getUserId()));
            requestedAmountInput.setText(String.valueOf(findRestockSuppliesRequest.get().getRequiredAmount()));
        }
    }
}