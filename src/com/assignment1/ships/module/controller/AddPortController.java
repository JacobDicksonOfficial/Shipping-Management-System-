package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.business.PortBusinessLogic;
import com.assignment1.ships.module.model.Port;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPortController {

    @FXML
    private TextField portNameField;
    @FXML
    private TextField portCodeField;
    @FXML
    private TextField countryField;

    private PortBusinessLogic portBusinessLogic;

    // Method to set the PortBusinessLogic instance
    public void setPortBusinessLogic(PortBusinessLogic portBusinessLogic) {
        this.portBusinessLogic = portBusinessLogic;
    }

    // Handler for the "Save" button
    @FXML
    private void handleAddPort() {
        String portName = portNameField.getText();
        String portCode = portCodeField.getText();
        String country = countryField.getText();

        if (portName.isEmpty() || portCode.isEmpty() || country.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        portBusinessLogic.addPort(new Port(portName, portCode, country));

        // Close the window after saving the port
        Stage stage = (Stage) portNameField.getScene().getWindow();
        stage.close();
    }
}




