package csc305.parkwise.Users.Asif.CampgroundManager;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.Campsite;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.CampsiteBooking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AssignRangerController
{

    @javafx.fxml.FXML
    private Label bookingStatusLabel;
    @javafx.fxml.FXML
    private Label bookerNameLabel;
    @javafx.fxml.FXML
    private Label campsiteLabel;
    @javafx.fxml.FXML
    private ComboBox selectCampsiteBookingCombobox;
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
    private TableColumn<CampsiteBooking, LocalDate> campsiteBookingCheckInDateColumn;
    @javafx.fxml.FXML
    private TableColumn<CampsiteBooking, Integer> campsiteBookingCampsiteIdColumn;
    @javafx.fxml.FXML
    private TableColumn<CampsiteBooking, Integer> campsiteBookingUserIdColumn;
    @javafx.fxml.FXML
    private TableColumn<CampsiteBooking, LocalDate> campsiteBookingCheckOutDateColumn;

    public List<CampsiteBooking> getCampsiteBookingsFromFile() throws IOException {
        StreamMapper stream = new StreamMapper();
        List<Object> campsiteBookingObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.CampsiteBookingObjects)
        );

        System.out.println(campsiteBookingObjects.toString());

        return campsiteBookingObjects.stream()
                .filter(obj -> obj instanceof CampsiteBooking)
                .map(obj -> (CampsiteBooking) obj)
                .toList();
    }

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        campsiteBookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        campsiteBookingUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        campsiteBookingCampsiteIdColumn.setCellValueFactory(new PropertyValueFactory<>("campsiteId"));
        campsiteBookingCheckInDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        campsiteBookingCheckOutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

        List<CampsiteBooking> campsiteBookings =  getCampsiteBookingsFromFile();

        ObservableList<CampsiteBooking> campsiteBookingList = FXCollections.observableList(campsiteBookings);
        campsiteBookingTableview.setItems(campsiteBookingList);
    }

    @javafx.fxml.FXML
    public void onAssignCamperToSiteButtonClick(ActionEvent actionEvent) {
    }

    @Deprecated
    public void onSubmitRequestButtonClick(ActionEvent actionEvent) {
    }
}