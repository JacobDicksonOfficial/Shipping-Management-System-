package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.model.Container;
import com.assignment1.ships.module.model.Pallet;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPalletController {

    @FXML
    private TextField companyField;  // TextField for entering the company name associated with the pallet

    @FXML
    private TextField typeOfGoodField;  // TextField for entering the type of goods on the pallet

    @FXML
    private TextField weightField;  // TextField for entering the weight of the pallet

    @FXML
    private TextField sizeField;  // TextField for entering the size of the pallet

    private Container container;  // The container to which the pallet will be added

    /**
     * Sets the container to which the pallet will be added.
     *
     * @param container The container object where the new pallet will be added.
     */
    public void setContainer(Container container) {
        this.container = container;
    }

    /**
     * Handles the event of adding a pallet. Validates the input, creates a new
     * pallet, and adds it to the specified container.
     */
    @FXML
    private void handleAddPallet() {
        String company = companyField.getText();  // Get the company name from TextField
        String typeOfGood = typeOfGoodField.getText();  // Get the type of goods from TextField
        double weight;
        double size;

        // Validate input: check if any field is empty
        if (company.isEmpty() || typeOfGood.isEmpty() || weightField.getText().isEmpty() || sizeField.getText().isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Validate input: check if weight and size are valid numbers
        try {
            weight = Double.parseDouble(weightField.getText());  // Parse the weight
            size = Double.parseDouble(sizeField.getText());  // Parse the size
        } catch (NumberFormatException e) {
            System.out.println("Weight and Size must be valid numbers.");
            return;
        }

        // Create a new Pallet object
        Pallet pallet = new Pallet(company, typeOfGood, weight, size);
        container.addPallet(pallet);  // Add the pallet to the container

        // Close the window after saving the pallet
        Stage stage = (Stage) companyField.getScene().getWindow();
        stage.close();
    }
}
