package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.model.Port;
import com.assignment1.ships.module.model.Ship;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddShipController {

    @FXML
    private TextField shipNameField;

    @FXML
    private TextField imoNumberField;

    @FXML
    private TextField registrationField;

    @FXML
    private TextField urlField;

    @FXML
    private TextField capacityField;

    private Port port;

    public void setPort(Port port) {
        this.port = port;
    }

    @FXML
    private void handleAddShip() {
        String shipName = shipNameField.getText();
        String imoNumber = imoNumberField.getText();
        String registration = registrationField.getText();
        String url = urlField.getText();
        int capacity;

        // Validate the input fields
        if (shipName.isEmpty() || imoNumber.isEmpty() || registration.isEmpty() || url.isEmpty() || capacityField.getText().isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Validate IMO number format (IMO followed by seven digits)
        if (!imoNumber.matches("IMO\\d{7}")) {
            System.out.println("Invalid IMO number. It should be 'IMO' followed by seven digits.");
            return;
        }

        // Validate registration format (1 to 3 uppercase letters)
        if (!registration.matches("[A-Z]{1,3}")) {
            System.out.println("Invalid registration. It should be 1 to 3 uppercase letters.");
            return;
        }

        try {
            capacity = Integer.parseInt(capacityField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Capacity must be a valid number.");
            return;
        }

        // Create a new Ship object
        Ship ship = new Ship(shipName, imoNumber, registration, url, capacity);
        port.addShip(ship);  // Add the ship to the selected port

        // Close the window after saving the ship
        Stage stage = (Stage) shipNameField.getScene().getWindow();
        stage.close();
    }
}




