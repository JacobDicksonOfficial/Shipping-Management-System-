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
    @FXML
    private TextField portTypeField;   // New field for Port Type
    @FXML
    private TextField comsField;       // New field for COMS

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
        String portType = portTypeField.getText();  // Retrieve Port Type input
        String coms = comsField.getText();          // Retrieve COMS input

        if (portName.isEmpty() || portCode.isEmpty() || country.isEmpty() || portType.isEmpty() || coms.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Assuming Port class is updated to handle portType and coms
        portBusinessLogic.addPort(new Port(portName, portCode, country, portType, coms));

        // Close the window after saving the port
        Stage stage = (Stage) portNameField.getScene().getWindow();
        stage.close();
    }
}




