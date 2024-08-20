package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.model.Container;
import com.assignment1.ships.module.model.Ship;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddContainerController {

    @FXML
    private TextField containerCodeField;

    @FXML
    private TextField cubicField;

    private Ship ship;

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @FXML
    private void handleAddContainer() {
        String containerCode = containerCodeField.getText();
        double cubic;

        // Validate input
        if (containerCode.isEmpty() || cubicField.getText().isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        if (!containerCode.matches("[A-Z]{4}\\d{7}")) {
            System.out.println("Invalid container code. It should be four letters followed by seven digits.");
            return;
        }

        try {
            cubic = Double.parseDouble(cubicField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Cubic must be a valid number.");
            return;
        }

        // Create a new Container object
        Container container = new Container(containerCode, cubic);
        ship.addContainer(container);

        // Close the window after saving the container
        Stage stage = (Stage) containerCodeField.getScene().getWindow();
        stage.close();
    }
}


