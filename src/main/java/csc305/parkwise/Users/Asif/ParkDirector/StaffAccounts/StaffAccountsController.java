package csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;

import static csc305.parkwise.Common.Utils.ObjectStreamOperation.getObjectInputStream;
import static csc305.parkwise.Common.Utils.ObjectStreamOperation.getObjectOutputStream;

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
    private TableView<StaffAccount> stattAccountsTableview;
    @javafx.fxml.FXML
    private Label showUsersLabel;

    @javafx.fxml.FXML
    public void initialize() {
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
    public void onAddUserButtonClick(ActionEvent actionEvent) {
        String password = passwordInput.getText();
        String email = emailAddressInput.getText();
        String fullName = fullNameTextInput.getText();
        String userType = userTypeCombobox.getValue();

        StaffAccount newStaff = new StaffAccount(
                12345678,
                email,
                password,
                fullName,
                userType
        );

        System.out.println(newStaff.toString());

        try {
            ObjectOutputStream oos = getObjectOutputStream("src/main/java/csc305/parkwise/Common/Streams/UserObjects.bin");

            oos.writeObject(newStaff);
            oos.close();

            System.out.println("Successfully Added User");

        } catch (Exception ignored) {}
    }

    @javafx.fxml.FXML
    public void onGetUsersButtonClick(ActionEvent actionEvent) {
        ObjectInputStream ois = null;
        StringBuilder outputStr = new StringBuilder();

        try{
            ois = getObjectInputStream("src/main/java/csc305/parkwise/Common/Streams/UserObjects.bin");

            while(true){
                try {
                    StaffAccount staff = (StaffAccount)ois.readObject();
                    outputStr.append(staff.toString()).append("\n");
                } catch (EOFException | ClassNotFoundException e){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(ois != null){
                try{
                    ois.close();
                } catch (Exception ignored) {}
            }
        }

        showUsersLabel.setText(outputStr.toString());
    }
}