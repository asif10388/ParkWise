package csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts;

import csc305.parkwise.Common.Models.User;
import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.List;
import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class StaffAccountsController
{
    @javafx.fxml.FXML
    private TextField fullNameTextInput;
    @javafx.fxml.FXML
    private ComboBox<String> userTypeCombobox;
    @javafx.fxml.FXML
    private TextField emailAddressInput;
    @javafx.fxml.FXML
    private PasswordField passwordInput;
    @javafx.fxml.FXML
    private TableColumn<StaffAccount, String> staffFullnameColumn;
    @javafx.fxml.FXML
    private TableView<StaffAccount> staffAccountsTableview;
    @javafx.fxml.FXML
    private TableColumn<StaffAccount, String> staffRoleColumn;
    @javafx.fxml.FXML
    private TableColumn<StaffAccount, String> staffUserIdColumn;

    public List<StaffAccount> loadStaffAccountsTable() throws IOException {
        StreamMapper stream = new StreamMapper();
        List<Object> staffObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.StaffObjects)
        );

        List<StaffAccount> staffAccounts = staffObjects.stream()
                .filter(obj -> obj instanceof StaffAccount)
                .map(obj -> (StaffAccount) obj )
                .toList();

        ObservableList<StaffAccount> staffAccountsList = FXCollections.observableList(staffAccounts);
        staffAccountsTableview.setItems(staffAccountsList);

        return staffAccounts;
    };

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        staffUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        staffRoleColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        staffFullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        loadStaffAccountsTable();

        userTypeCombobox.getItems().addAll(
                "Tour Guide",
                "Park Ranger",
                "Ticket Officer",
                "Campground Manager",
                "Wildlife Biologist",
                "Educational Group Leader"
        );
    }

    @javafx.fxml.FXML
    public void onAddUserButtonClick(ActionEvent actionEvent) throws IOException {
        String password = passwordInput.getText();
        String email = emailAddressInput.getText();
        String fullName = fullNameTextInput.getText();
        String userType = userTypeCombobox.getValue();

        if(password.isEmpty() || email.isEmpty() || fullName.isEmpty() || userType.isEmpty()){
            Utilities.showAlert("No fields can be blank", Alert.AlertType.ERROR);
            return;
        }

        if(!StaffAccount.isValidStaffEmail(email)){
            Utilities.showAlert("Email is not a valid staff email!", Alert.AlertType.ERROR);
            return;
        }

        if(!StaffAccount.isValidPassword(password)){
            Utilities.showAlert("Password must be greater than 8 characters", Alert.AlertType.ERROR);
            return;
        }

        StreamMapper stream = new StreamMapper();
        List<Object> staffObjects = ObjectStreamOperation.getObjectsFromFile(stream.getObjectStream(ObjectStreams.StaffObjects));

        List<StaffAccount> staffAccounts = staffObjects.stream()
                .filter(obj -> obj instanceof StaffAccount)
                .map(obj -> (StaffAccount) obj)
                .toList();

        int userId = StaffAccount.generateNewUserId(staffAccounts);

        StaffAccount newStaff = new StaffAccount(
                userId,
                email,
                password,
                fullName,
                userType
        );

        try {
            ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.StaffObjects), false);
            oos.writeObject(newStaff);
            oos.close();
			
			passwordInput.clear();
			fullNameTextInput.clear();
			emailAddressInput.clear();
			userTypeCombobox.getSelectionModel().clearSelection();

			loadStaffAccountsTable();

			Utilities.showAlert("Staff account added successfully", Alert.AlertType.INFORMATION);

        } catch (Exception ignored) {
            Utilities.showAlert("Something went wrong. Please try again", Alert.AlertType.ERROR);
        }
    }

    @javafx.fxml.FXML
    public void onGenerateRandomPasswordButtonClick(ActionEvent actionEvent) {
        passwordInput.setText(User.generateRandomPassword());
    }

    @javafx.fxml.FXML
    public void onLoadUsersButtonClick(ActionEvent actionEvent) throws IOException {
        loadStaffAccountsTable();
    }
}