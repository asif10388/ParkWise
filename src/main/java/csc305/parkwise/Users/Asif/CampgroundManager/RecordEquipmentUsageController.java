package csc305.parkwise.Users.Asif.CampgroundManager;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Common.Utils.Utilities;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.Equipment;
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

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class RecordEquipmentUsageController
{
    @javafx.fxml.FXML
    private DatePicker dateOfActionDatePicker;
    @javafx.fxml.FXML
    private ComboBox<String> itemNameCombobox;
    @javafx.fxml.FXML
    private ToggleGroup actionTypeToggleGroup;
    @javafx.fxml.FXML
    private TextField quantityTextInput;
    @javafx.fxml.FXML
    private TextArea commentsTextarea;
    @javafx.fxml.FXML
    private TableColumn<Equipment, Integer> equipmentItemInStock;
    @javafx.fxml.FXML
    private TableColumn<Equipment, String> equipmentItemName;
    @javafx.fxml.FXML
    private TableColumn<Equipment, String> equipmentItemType;
    @javafx.fxml.FXML
    private TableColumn<Equipment, String> equipmentItemCategory;
    @javafx.fxml.FXML
    private TableColumn<Equipment, Integer> equipmentItemId;
    @javafx.fxml.FXML
    private TableColumn<Equipment, Boolean> equipmentItemIsInUse;
    @javafx.fxml.FXML
    private TableColumn<Equipment, String> equipmentItemStatus;
    @javafx.fxml.FXML
    private TableView<Equipment> equipmentInventoryTableview;
    @javafx.fxml.FXML
    private RadioButton returnRadioBtn;
    @javafx.fxml.FXML
    private RadioButton checkoutRadioBtn;

    public List<Equipment> getEquipmentsFromFile() throws IOException{
        StreamMapper stream = new StreamMapper();
        List<Object> campsiteObjects = ObjectStreamOperation.getObjectsFromFile(
                stream.getObjectStream(ObjectStreams.EquipmentObjects)
        );

        return campsiteObjects.stream()
                .filter(obj -> obj instanceof Equipment)
                .map(obj -> (Equipment) obj)
                .toList();
    }

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        equipmentItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        equipmentItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        equipmentItemType.setCellValueFactory(new PropertyValueFactory<>("ItemType"));
        equipmentItemIsInUse.setCellValueFactory(new PropertyValueFactory<>("isItemInUse"));
        equipmentItemInStock.setCellValueFactory(new PropertyValueFactory<>("itemsInStock"));
        equipmentItemCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        equipmentItemStatus.setCellValueFactory(new PropertyValueFactory<>("itemAvailabilityStatus"));

        List<Equipment> equipments =  getEquipmentsFromFile();
        equipments.forEach(equipment -> itemNameCombobox.getItems().add(equipment.getItemName()));

        ObservableList<Equipment> equipmentList = FXCollections.observableList(equipments);
        equipmentInventoryTableview.setItems(equipmentList);

        actionTypeToggleGroup = new ToggleGroup();

        checkoutRadioBtn.setUserData("check-out");
        checkoutRadioBtn.setToggleGroup(actionTypeToggleGroup);

        returnRadioBtn.setUserData("return");
        returnRadioBtn.setToggleGroup(actionTypeToggleGroup);
    }

    @javafx.fxml.FXML
    public void onSubmitUsageButtonClick(ActionEvent actionEvent) throws IOException {
        String selectedActionType = actionTypeToggleGroup.getSelectedToggle().getUserData().toString();

        if(
                itemNameCombobox.getValue() == null ||
                dateOfActionDatePicker.getValue() == null ||
                selectedActionType.isEmpty() ||
                quantityTextInput.getText().isBlank()){
                Utilities.showAlert("Please fill all necessary fields", Alert.AlertType.ERROR);
                return;
        }

        if(dateOfActionDatePicker.getValue().isBefore(LocalDate.now())){
            Utilities.showAlert("Date cannot be in the past", Alert.AlertType.ERROR);
            return;
        }

        String comments = commentsTextarea.getText();
        String itemName = itemNameCombobox.getValue();
        LocalDate dateOfAction = dateOfActionDatePicker.getValue();
        int quantity = Integer.parseInt(quantityTextInput.getText());

        List<Equipment> equipments =  getEquipmentsFromFile();
        Optional<Equipment> findEquipment = equipments.stream().filter(obj -> obj.getItemName().equals(itemName)).findFirst();

        for (Equipment equipment : equipments) {
          if(equipment.getItemName().equals(itemName)){
              System.out.println("Items before update: " + equipment.toString());
          }
        }

        if(findEquipment.isPresent()){
            if(selectedActionType.equals("check-out") && findEquipment.get().getItemsInStock() < quantity){
                Utilities.showAlert("Insufficient stock", Alert.AlertType.ERROR);
                return;
            }

            if(selectedActionType.equals("check-out") && findEquipment.get().getIsItemInUse()){
                Utilities.showAlert("Cannot check-out an item that's already rented", Alert.AlertType.ERROR);
                return;
            }

            boolean isUpdated = false;

            if(selectedActionType.equals("check-out") && findEquipment.get().isItemAvailabilityStatus().equals("Available")){
                findEquipment.get().setItemsInStock(findEquipment.get().getItemsInStock() - quantity);
                findEquipment.get().setIsItemInUse(true);

                if(findEquipment.get().getItemsInStock() - quantity < 10){
                    if(findEquipment.get().getItemsInStock() - quantity == 0) {
                        findEquipment.get().setItemAvailabilityStatus("Out of stock");
                    } else {
                        findEquipment.get().setItemAvailabilityStatus("Low stock");
                    }
                }
            }

            if(selectedActionType.equals("return")){
                if(!findEquipment.get().getIsItemInUse()){
                    Utilities.showAlert("Cannot return an item that's not rented!", Alert.AlertType.ERROR);
                    return;
                }

                findEquipment.get().setIsItemInUse(false);
                findEquipment.get().setItemsInStock(findEquipment.get().getItemsInStock() + quantity);

                if(findEquipment.get().getItemsInStock() > 10){
                    findEquipment.get().setItemAvailabilityStatus("Available");
                }
            }

            for (Equipment equipment : equipments) {
              if(equipment.getItemName().equals(itemName)){
                  System.out.println("Items after update: " + equipment.toString());
              }
            }

            try {
                StreamMapper stream = new StreamMapper();
                ObjectOutputStream oos = getObjectOutputStream(stream.getObjectStream(ObjectStreams.EquipmentObjects), true);

                for (Equipment equipment : equipments) {
                    oos.writeObject(equipment);
                }

                oos.close();
                Utilities.showAlert("Equipment updated successfully!", Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                Utilities.showAlert("Something went wrong! Please try again!", Alert.AlertType.ERROR);
            }
        }
    }

    private static String determineStockStatus(int stock) {
        if (stock == 0) {
            return "Out of stock";
        } else if (stock < 10) {
            return "Low stock";
        } else {
            return "Available";
        }
    }

}