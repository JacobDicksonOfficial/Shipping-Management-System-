package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.model.Container;
import com.assignment1.ships.module.model.Pallet;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPalletController {

    @FXML
    private TextField companyField;

    @FXML
    private TextField typeOfGoodField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField sizeField;

    private Container container;

    public void setContainer(Container container) {
        this.container = container;
    }

    @FXML
    private void handleAddPallet() {
        String company = companyField.getText();
        String typeOfGood = typeOfGoodField.getText();
        double weight;
        double size;

        // Validate input
        if (company.isEmpty() || typeOfGood.isEmpty() || weightField.getText().isEmpty() || sizeField.getText().isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        try {
            weight = Double.parseDouble(weightField.getText());
            size = Double.parseDouble(sizeField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Weight and Size must be valid numbers.");
            return;
        }

        // Create a new Pallet object
        Pallet pallet = new Pallet(company, typeOfGood, weight, size);
        container.addPallet(pallet);

        // Close the window after saving the pallet
        Stage stage = (Stage) companyField.getScene().getWindow();
        stage.close();
    }
}
