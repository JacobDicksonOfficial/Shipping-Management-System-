package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.model.Good;
import com.assignment1.ships.module.model.Pallet;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddGoodController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField weightField;

    private Pallet pallet;

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    @FXML
    private void handleAddGood() {
        String name = nameField.getText();
        double price;
        double weight;

        if (name.isEmpty() || priceField.getText().isEmpty() || weightField.getText().isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        try {
            price = Double.parseDouble(priceField.getText());
            weight = Double.parseDouble(weightField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Price and Weight must be valid numbers.");
            return;
        }

        Good good = new Good(name, price, weight);
        pallet.addGood(good);

        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
