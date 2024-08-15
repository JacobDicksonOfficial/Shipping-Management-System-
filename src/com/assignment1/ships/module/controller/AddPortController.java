package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.business.PortBusinessLogic;
import com.assignment1.ships.module.model.Port;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import javafx.stage.Modality;


import java.io.IOException;

public class AddPortController {

    @FXML
    private TextField portNameField;
    @FXML
    private TextField portCodeField;
    @FXML
    private TextField countryField;

    private PortBusinessLogic portBusinessLogic = new PortBusinessLogic();

    // Method to open the "Add Port" window
    public void openAddPortWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-port.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Port Details");
            stage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Optional: Show an error dialog here
        }
    }

    // Handler for the "Save" button
    @FXML
    private void handleAddPort() {
        String portName = portNameField.getText();
        String portCode = portCodeField.getText();
        String country = countryField.getText();

        if (portName.isEmpty() || portCode.isEmpty() || country.isEmpty()) {
            // Show an alert or handle the error if any field is empty
            System.out.println("Please fill in all fields.");
            return;
        }

        // Add the port using the business logic
        portBusinessLogic.addPort(new Port(portName, portCode, country));

        // Optionally, close the window after saving
        Stage stage = (Stage) portNameField.getScene().getWindow();
        stage.close();
    }

    // Handler for the "Print Ports" button
    @FXML
    private void handlePrintPorts() {
        portBusinessLogic.printPorts();
    }
}

