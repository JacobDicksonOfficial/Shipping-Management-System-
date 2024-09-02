package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.business.PortBusinessLogic;
import com.assignment1.ships.module.model.Port;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPortController {

    @FXML
    private TextField portNameField;  // TextField for entering the port name

    @FXML
    private TextField portCodeField;  // TextField for entering the port code

    @FXML
    private TextField countryField;  // TextField for entering the country where the port is located

    @FXML
    private TextField portTypeField;  // TextField for entering the type of the port

    @FXML
    private TextField comsField;  // TextField for entering the COMS (communications) information for the port

    private PortBusinessLogic portBusinessLogic;  // Instance of PortBusinessLogic to manage port operations

    /**
     * Sets the PortBusinessLogic instance used to manage port operations.
     *
     * @param portBusinessLogic The PortBusinessLogic instance.
     */
    public void setPortBusinessLogic(PortBusinessLogic portBusinessLogic) {
        this.portBusinessLogic = portBusinessLogic;
    }

    /**
     * Handles the event of adding a port. Validates the input, creates a new
     * Port object, and adds it using the PortBusinessLogic instance.
     */
    @FXML
    private void handleAddPort() {
        String portName = portNameField.getText();  // Retrieve the port name input
        String portCode = portCodeField.getText();  // Retrieve the port code input
        String country = countryField.getText();  // Retrieve the country input
        String portType = portTypeField.getText();  // Retrieve the port type input
        String coms = comsField.getText();  // Retrieve the COMS input

        // Validate input: check if any field is empty
        if (portName.isEmpty() || portCode.isEmpty() || country.isEmpty() || portType.isEmpty() || coms.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Create a new Port object and add it using PortBusinessLogic
        portBusinessLogic.addPort(new Port(portName, portCode, country, portType, coms));

        // Close the window after saving the port
        Stage stage = (Stage) portNameField.getScene().getWindow();
        stage.close();
    }
}






