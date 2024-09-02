package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.business.PortBusinessLogic;
import com.assignment1.ships.module.model.Port;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PortController {

    @FXML
    private TableView<Port> portTableView;  // TableView for displaying ports

    @FXML
    private TableColumn<Port, String> portNameColumn;  // TableColumn for the port name

    @FXML
    private TableColumn<Port, String> portCodeColumn;  // TableColumn for the port code

    @FXML
    private TableColumn<Port, String> countryColumn;  // TableColumn for the country of the port

    @FXML
    private TableColumn<Port, String> portTypeColumn;  // TableColumn for the type of the port

    @FXML
    private TableColumn<Port, String> comsColumn;  // TableColumn for the COMS (communications) of the port

    private PortBusinessLogic portBusinessLogic;  // Business logic for managing ports

    /**
     * Sets the PortBusinessLogic instance used to manage port operations and loads the ports.
     *
     * @param portBusinessLogic The PortBusinessLogic instance.
     */
    public void setPortBusinessLogic(PortBusinessLogic portBusinessLogic) {
        this.portBusinessLogic = portBusinessLogic;
        loadPorts();  // Load ports when the business logic is set
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        // Initialize the table columns with the respective data properties
        portNameColumn.setCellValueFactory(new PropertyValueFactory<>("portName"));
        portCodeColumn.setCellValueFactory(new PropertyValueFactory<>("portCode"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        portTypeColumn.setCellValueFactory(new PropertyValueFactory<>("portType"));
        comsColumn.setCellValueFactory(new PropertyValueFactory<>("coms"));
    }

    /**
     * Loads the ports from the PortBusinessLogic and displays them in the TableView.
     */
    public void loadPorts() {
        portTableView.setItems(FXCollections.observableArrayList(portBusinessLogic.getAllPorts()));
    }

    /**
     * Handles the action of adding a new port. Opens a new window for adding a port.
     */
    @FXML
    private void handleAddPort() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-port.fxml"));
            Parent root = loader.load();

            // Get the controller for the add port view
            AddPortController addPortController = loader.getController();
            addPortController.setPortBusinessLogic(portBusinessLogic);  // Pass the business logic to the add port controller

            // Set up and show the add port window
            Stage stage = new Stage();
            stage.setTitle("Add Port");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadPorts();  // Reload ports after adding

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of deleting the selected port.
     */
    @FXML
    private void handleDeletePort() {
        Port selectedPort = portTableView.getSelectionModel().getSelectedItem();
        if (selectedPort != null) {
            portBusinessLogic.deletePort(selectedPort);  // Delete the selected port
            loadPorts();  // Reload ports after deletion
        }
    }

    /**
     * Handles the action of viewing the ships associated with the selected port.
     */
    @FXML
    private void handleViewShips() {
        Port selectedPort = portTableView.getSelectionModel().getSelectedItem();
        if (selectedPort != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/ship.fxml"));
                Parent root = loader.load();

                // Get the controller for the ships view
                ShipController shipController = loader.getController();
                shipController.setPort(selectedPort);  // Pass the selected port to the ShipController
                shipController.setPortBusinessLogic(portBusinessLogic);  // Pass the business logic to the ShipController

                // Set up and show the ships window
                Stage stage = new Stage();
                stage.setTitle("Ships in " + selectedPort.getPortName());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please select a port first.");
        }
    }

    /**
     * Handles the action of clearing all ports.
     */
    @FXML
    private void handleClearFacility() {
        portBusinessLogic.clearAllPorts();  // Clear all ports
        loadPorts();  // Reload ports after clearing
    }
}
