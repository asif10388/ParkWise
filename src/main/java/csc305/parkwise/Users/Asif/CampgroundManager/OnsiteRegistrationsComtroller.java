package csc305.parkwise.Users.Asif.CampgroundManager;

import csc305.parkwise.Common.Models.User;
import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.Campsite;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.CampsiteBooking;
import csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts.StaffAccount;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class OnsiteRegistrationsComtroller
{
    @javafx.fxml.FXML
    private DatePicker checkInDatePicker;
    @javafx.fxml.FXML
    private Label payableAmountLabel;
    @javafx.fxml.FXML
    private ComboBox<String> selectCampsiteCombobox;
    @javafx.fxml.FXML
    private ComboBox<Integer> userIdCombobox;
    @javafx.fxml.FXML
    private DatePicker checkOutDatePicker;
    @javafx.fxml.FXML
    private ComboBox<Integer> parkRangersCombobox;
    @javafx.fxml.FXML
    private CheckBox paidCheckbox;
    @javafx.fxml.FXML
    private Button reserveCampsiteButton;

    private double payableAmount;

    public double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public List<Campsite> getCampsitesFromFile() throws IOException {
        StreamMapper stream = new StreamMapper();
        List<Object> campsiteObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.CampsiteObjects)
        );

        System.out.println(campsiteObjects.toString());

        return campsiteObjects.stream()
                .filter(obj -> obj instanceof Campsite)
                .map(obj -> (Campsite) obj)
                .toList();
    }

    public List<User> getUsersFromFile() throws IOException {
        StreamMapper stream = new StreamMapper();
        List<Object> userObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.UserObjects)
        );

        return userObjects.stream()
                .filter(obj -> obj instanceof User)
                .map(obj -> (User) obj)
                .toList();
    }

    public List<StaffAccount> getParkRangersFromFile() throws IOException {
        StreamMapper stream = new StreamMapper();
        List<Object> userObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.StaffObjects)
        );

        return userObjects.stream()
                .filter(obj -> obj instanceof StaffAccount)
                .map(obj -> (StaffAccount) obj)
                .filter(obj -> Objects.equals(obj.getUserType(), "Park Ranger"))
                .toList();
    }

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        checkInDatePicker.setDisable(true);
        checkOutDatePicker.setDisable(true);
        reserveCampsiteButton.setDisable(true);

        List<User> users = getUsersFromFile();
        List<Campsite> campsites =  getCampsitesFromFile();
        List<StaffAccount> parkRangers = getParkRangersFromFile();

        users.forEach(user -> userIdCombobox.getItems().add(user.getUserId()));
        parkRangers.forEach(ranger -> parkRangersCombobox.getItems().add(ranger.getUserId()));
        campsites.forEach(campsite -> selectCampsiteCombobox.getItems().add(campsite.getCampsiteName()));
    }

    @javafx.fxml.FXML
    public void onReserveCampsiteButtonClick(ActionEvent actionEvent) throws IOException {
        if(selectCampsiteCombobox.getValue() == null ||
            parkRangersCombobox.getValue() == null ||
            userIdCombobox.getValue() == null ||
            checkInDatePicker.getValue() == null ||
            checkOutDatePicker.getValue() == null
        ) {
            Utilities.showAlert("Please fill in all the fields", Alert.AlertType.ERROR);
        }

        int userId = userIdCombobox.getValue();
        boolean isPaid = paidCheckbox.isSelected();
        int parkRangerId = parkRangersCombobox.getValue();

        List<Campsite> campsites =  getCampsitesFromFile();
        Optional<Campsite> findCampsite = campsites.stream()
                .filter(campsite -> Objects.equals(campsite.getCampsiteName(), selectCampsiteCombobox.getValue()))
                .findFirst();

        if(findCampsite.isPresent()) {
            CampsiteBooking newCampsiteBooking = new CampsiteBooking(
                    userId,
                    isPaid,
                    1001,
                    findCampsite.get().getCampsiteId(),
                    parkRangerId,
                    this.getPayableAmount(),
                    "Active",
                    checkInDatePicker.getValue(),
                    LocalDate.now(),
                    checkOutDatePicker.getValue()
            );

            System.out.println(newCampsiteBooking.toString());

            try {
                StreamMapper stream = new StreamMapper();
                ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.CampsiteBookingObjects), false);
                oos.writeObject(newCampsiteBooking);

                oos.close();
                Utilities.showAlert("Campsite booked successfully!", Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
            }
        }
    }

    @javafx.fxml.FXML
    public void onCampsiteValueSelected(ActionEvent actionEvent) {
        checkInDatePicker.setDisable(false);
    }

    @javafx.fxml.FXML
    public void onCheckInDateValueSelected(ActionEvent actionEvent) {
        if(checkInDatePicker.getValue().isBefore(LocalDate.now())) {
            Utilities.showAlert("Please select a check-in date", Alert.AlertType.ERROR);
            checkInDatePicker.setValue(LocalDate.now());
        } else {
            checkOutDatePicker.setDisable(false);
        }
    }

    @javafx.fxml.FXML
    public void onCheckOutDateValueSelected(ActionEvent actionEvent) throws IOException {
        if(checkOutDatePicker.getValue().isBefore(checkInDatePicker.getValue())) {
            Utilities.showAlert("Check out date must be after check-in date", Alert.AlertType.ERROR);
            checkOutDatePicker.setValue(checkInDatePicker.getValue());
            return;
        }

        List<Campsite> campsites =  getCampsitesFromFile();
        Optional<Campsite> findCampsite = campsites.stream()
                .filter(campsite -> Objects.equals(campsite.getCampsiteName(), selectCampsiteCombobox.getValue()))
                .findFirst();

        if(findCampsite.isPresent()) {
            int days = checkOutDatePicker.getValue().compareTo(checkInDatePicker.getValue());
            double getPrice = findCampsite.get().getCampsitePerDayFee() * days;

            payableAmountLabel.setText("BDT " + String.valueOf(getPrice));
            this.setPayableAmount(getPrice);
        }
    }

    @javafx.fxml.FXML
    public void onPaidCheckboxSelected(ActionEvent actionEvent) {
        reserveCampsiteButton.setDisable(!paidCheckbox.isSelected());
    }
}