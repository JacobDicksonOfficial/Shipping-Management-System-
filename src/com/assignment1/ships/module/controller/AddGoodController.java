package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.model.Good;
import com.assignment1.ships.module.model.Pallet;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddGoodController {

    @FXML
    private TextField nameField;  // TextField for entering the name of the good

    @FXML
    private TextField priceField;  // TextField for entering the price of the good

    @FXML
    private TextField weightField;  // TextField for entering the weight of the good

    private Pallet pallet;  // The pallet to which the good will be added

    /**
     * Sets the pallet to which the good will be added.
     *
     * @param pallet The pallet object where the new good will be added.
     */
    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    /**
     * Handles the event of adding a good. Validates the input, creates a new
     * good, and adds it to the specified pallet.
     */
    @FXML
    private void handleAddGood() {
        String name = nameField.getText();  // Get the name of the good from TextField
        double price;
        double weight;

        // Validate input: check if any field is empty
        if (name.isEmpty() || priceField.getText().isEmpty() || weightField.getText().isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Validate input: check if price and weight are valid numbers
        try {
            price = Double.parseDouble(priceField.getText());  // Parse the price
            weight = Double.parseDouble(weightField.getText());  // Parse the weight
        } catch (NumberFormatException e) {
            System.out.println("Price and Weight must be valid numbers.");
            return;
        }

        // Create a new Good object
        Good good = new Good(name, price, weight);
        pallet.addGood(good);  // Add the good to the pallet

        // Close the window after saving the good
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
