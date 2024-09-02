package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.model.Container;
import com.assignment1.ships.module.model.Ship;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddContainerController {

    @FXML
    private TextField containerCodeField;  // TextField for entering the container code

    @FXML
    private TextField cubicField;  // TextField for entering the cubic capacity

    private Ship ship;  // The ship to which the container will be added

    /**
     * Sets the ship to which the container will be added.
     *
     * @param ship The ship object where the new container will be added.
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Handles the event of adding a container. Validates the input, creates a
     * new container, and adds it to the specified ship.
     */
    @FXML
    private void handleAddContainer() {
        String containerCode = containerCodeField.getText();  // Get container code from TextField
        double cubic;

        // Validate input
        if (containerCode.isEmpty() || cubicField.getText().isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        if (!containerCode.matches("[A-Z]{4}\\d{7}")) {  // Validate container code format
            System.out.println("Invalid container code. It should be four letters followed by seven digits.");
            return;
        }

        try {
            cubic = Double.parseDouble(cubicField.getText());  // Parse the cubic capacity
        } catch (NumberFormatException e) {
            System.out.println("Cubic must be a valid number.");
            return;
        }

        // Create a new Container object
        Container container = new Container(containerCode, cubic);
        ship.addContainer(container);  // Add container to the ship

        // Close the window after saving the container
        Stage stage = (Stage) containerCodeField.getScene().getWindow();
        stage.close();
    }
}
