package csc305.parkwise.Users.Asif.CampgroundManager;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.CampsiteBooking;
import csc305.parkwise.Users.Asif.CampgroundManager.OccupancyReports.OccupancyReport;
import csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts.StaffAccount;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class CamperEntriesController
{
    @javafx.fxml.FXML
    private ComboBox<Integer> selectCampsiteBookingCombobox;
    @javafx.fxml.FXML
    private Label campsiteBookingCheckInDateLabel;
    @javafx.fxml.FXML
    private Label campsiteBookingIdLabel;
    @javafx.fxml.FXML
    private Label campsiteBookingCheckOutDateLabel;
    @javafx.fxml.FXML
    private Label campsiteBookingUserIdLabel;
    @javafx.fxml.FXML
    private Label campsiteBookingStatusLabel;
    @javafx.fxml.FXML
    private Button checkOutButton;
    @javafx.fxml.FXML
    private Button checkInButton;

    public List<CampsiteBooking> getCampsiteBookingsFromFile() throws IOException {
        StreamMapper stream = new StreamMapper();
        List<Object> campsiteBookingObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.CampsiteBookingObjects)
        );

        return campsiteBookingObjects.stream()
                .filter(obj -> obj instanceof CampsiteBooking)
                .map(obj -> (CampsiteBooking) obj)
                .toList();
    }

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        checkInButton.setDisable(true);
        checkOutButton.setDisable(true);

        List<CampsiteBooking> campsiteBookings = getCampsiteBookingsFromFile();
        campsiteBookings.forEach(campsiteBooking -> selectCampsiteBookingCombobox.getItems().add(campsiteBooking.getBookingId()));
    }

    @javafx.fxml.FXML
    public void onCheckoutButtonClick(ActionEvent actionEvent) throws IOException {
        List<CampsiteBooking> campsiteBookings = getCampsiteBookingsFromFile();

        Optional<CampsiteBooking> findCampsiteBooking = campsiteBookings.stream()
                .filter(campsiteBooking -> campsiteBooking.getBookingId() == selectCampsiteBookingCombobox.getValue())
                .findFirst();

        if (findCampsiteBooking.isPresent()) {
            findCampsiteBooking.get().setBookingStatus("Checked-Out");

            try {
                StreamMapper stream = new StreamMapper();
                ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.CampsiteBookingObjects), true);

                for (CampsiteBooking campsiteBooking : campsiteBookings) {
                    oos.writeObject(campsiteBooking);
                }

                oos.close();

                Utilities.showAlert("Successfully checked-out user", Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
            }
        }
    }

    @javafx.fxml.FXML
    public void onCheckinButtonClick(ActionEvent actionEvent) throws IOException {
        List<CampsiteBooking> campsiteBookings = getCampsiteBookingsFromFile();

        Optional<CampsiteBooking> findCampsiteBooking = campsiteBookings.stream()
                .filter(campsiteBooking -> campsiteBooking.getBookingId() == selectCampsiteBookingCombobox.getValue())
                .findFirst();

        if (findCampsiteBooking.isPresent()) {
            findCampsiteBooking.get().setBookingStatus("Checked-In");

            try {
                StreamMapper stream = new StreamMapper();
                ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.CampsiteBookingObjects), true);

                for (CampsiteBooking campsiteBooking : campsiteBookings) {
                    oos.writeObject(campsiteBooking);
                }

                oos.close();

                Utilities.showAlert("Successfully checked-in user", Alert.AlertType.INFORMATION);
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
            campsiteBookingUserIdLabel.setText(String.valueOf(findCampsite.get().getUserId()));
            campsiteBookingIdLabel.setText(String.valueOf(findCampsite.get().getCampsiteId()));
            campsiteBookingStatusLabel.setText(String.valueOf(findCampsite.get().getBookingStatus()));
            campsiteBookingCheckInDateLabel.setText(String.valueOf(findCampsite.get().getCheckInDate()));
            campsiteBookingCheckOutDateLabel.setText(String.valueOf(findCampsite.get().getCheckOutDate()));

            if(Objects.equals(findCampsite.get().getBookingStatus(), "Active")){
                checkInButton.setDisable(false);
                return;
            }

            if (Objects.equals(findCampsite.get().getBookingStatus(), "Checked-In")) {
                checkOutButton.setDisable(false);
                return;
            }

            if (Objects.equals(findCampsite.get().getBookingStatus(), "Checked-Out")) {
                checkInButton.setDisable(false);
                return;
            }
        }
    }
}