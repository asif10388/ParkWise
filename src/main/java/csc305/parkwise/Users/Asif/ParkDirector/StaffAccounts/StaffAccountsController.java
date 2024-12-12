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
    @javafx.fxml.FXML
    private Label showResultLabel;

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        staffUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        staffRoleColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        staffFullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        List<Object> userObjects = ObjectStreamOperation.getObjectsFromFile(
                "src/main/java/csc305/parkwise/Common/Streams/StaffObjects.bin"
        );

        List<StaffAccount> staffAccounts = userObjects.stream()
                        .filter(obj -> obj instanceof StaffAccount)
                        .map(obj -> (StaffAccount) obj )
                        .toList();

        ObservableList<StaffAccount> staffAccountsList = FXCollections.observableList(staffAccounts);
        staffAccountsTableview.setItems(staffAccountsList);

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

        String outputStr = "";

        if(password.isEmpty() || email.isEmpty() || fullName.isEmpty() || userType.isEmpty()){
            Utilities.showAlert("No fields can be blank", Alert.AlertType.ERROR);
            return;
        }

        if(password.length() < 8){
            Utilities.showAlert("Password must be between 8 and 16 characters", Alert.AlertType.ERROR);
            return;
        }

        List<Object> userObjects = ObjectStreamOperation.getObjectsFromFile(
                "src/main/java/csc305/parkwise/Common/Streams/StaffObjects.bin"
        );

        StaffAccount getLastStaffAccount = userObjects.stream()
                .filter(obj -> obj instanceof StaffAccount)
                .map(obj -> (StaffAccount) obj)
                .toList().getLast();

        int userId = getLastStaffAccount.getUserId() + 1;

        StaffAccount newStaff = new StaffAccount(
                userId,
                email,
                password,
                fullName,
                userType
        );

        showResultLabel.setText(newStaff.toString());

        try {
            StreamMapper stream = new StreamMapper();
            ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.StaffObjects), false);
            oos.writeObject(newStaff);
            oos.close();

            Utilities.showAlert("Successfully added user", Alert.AlertType.INFORMATION);

        } catch (Exception ignored) {
            Utilities.showAlert("Something went wrong. Please try again", Alert.AlertType.ERROR);
        }
    }

    @javafx.fxml.FXML
    public void onGenerateRandomPasswordButtonClick(ActionEvent actionEvent) {
        passwordInput.setText(User.generateRandomPassword());
    }

    @javafx.fxml.FXML
    public void onShowResultButtonClick(ActionEvent actionEvent) throws IOException {
        String outputStr = "";

        List<Object> userObjects = ObjectStreamOperation.getObjectsFromFile(
                "src/main/java/csc305/parkwise/Common/Streams/UserObjects.bin"
        );

        List<StaffAccount> staffAccounts = userObjects.stream()
                .filter(obj -> obj instanceof StaffAccount)
                .map(obj -> (StaffAccount) obj )
                .toList();

        for (StaffAccount staffAccount : staffAccounts) {
            outputStr += staffAccount.getUserId() + ",";
        }

        showResultLabel.setText(outputStr);
        ObservableList<StaffAccount> staffAccountsList = FXCollections.observableList(staffAccounts);
        staffAccountsTableview.setItems(staffAccountsList);
    }
}