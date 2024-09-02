package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.model.Port;
import com.assignment1.ships.module.model.Ship;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddShipController {

    @FXML
    private TextField shipNameField;  // TextField for entering the ship name

    @FXML
    private TextField imoNumberField;  // TextField for entering the IMO number of the ship

    @FXML
    private TextField registrationField;  // TextField for entering the registration code of the ship

    @FXML
    private TextField urlField;  // TextField for entering the URL related to the ship

    @FXML
    private TextField capacityField;  // TextField for entering the capacity of the ship

    private Port port;  // The port to which the ship will be added

    /**
     * Sets the port to which the ship will be added.
     *
     * @param port The port object where the new ship will be added.
     */
    public void setPort(Port port) {
        this.port = port;
    }

    /**
     * Handles the event of adding a ship. Validates the input, creates a new
     * Ship object, and adds it to the specified port.
     */
    @FXML
    private void handleAddShip() {
        String shipName = shipNameField.getText();  // Retrieve the ship name input
        String imoNumber = imoNumberField.getText();  // Retrieve the IMO number input
        String registration = registrationField.getText();  // Retrieve the registration code input
        String url = urlField.getText();  // Retrieve the URL input
        int capacity;

        // Validate the input fields: check if any field is empty
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

        // Validate and parse capacity
        try {
            capacity = Integer.parseInt(capacityField.getText());  // Parse the capacity input
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
