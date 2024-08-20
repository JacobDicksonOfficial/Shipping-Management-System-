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
    private TableView<Port> portTableView;

    @FXML
    private TableColumn<Port, String> portNameColumn;

    @FXML
    private TableColumn<Port, String> portCodeColumn;

    @FXML
    private TableColumn<Port, String> countryColumn;

    @FXML
    private TableColumn<Port, String> portTypeColumn;

    @FXML
    private TableColumn<Port, String> comsColumn;

    private PortBusinessLogic portBusinessLogic;

    public void setPortBusinessLogic(PortBusinessLogic portBusinessLogic) {
        this.portBusinessLogic = portBusinessLogic;
        loadPorts();
    }

    @FXML
    public void initialize() {
        // Initialize the columns
        portNameColumn.setCellValueFactory(new PropertyValueFactory<>("portName"));
        portCodeColumn.setCellValueFactory(new PropertyValueFactory<>("portCode"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        portTypeColumn.setCellValueFactory(new PropertyValueFactory<>("portType"));
        comsColumn.setCellValueFactory(new PropertyValueFactory<>("coms"));
    }

    public void loadPorts() {
        portTableView.setItems(FXCollections.observableArrayList(portBusinessLogic.getAllPorts()));
    }

    @FXML
    private void handleAddPort() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-port.fxml"));
            Parent root = loader.load();

            AddPortController addPortController = loader.getController();
            addPortController.setPortBusinessLogic(portBusinessLogic);

            Stage stage = new Stage();
            stage.setTitle("Add Port");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadPorts();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeletePort() {
        Port selectedPort = portTableView.getSelectionModel().getSelectedItem();
        if (selectedPort != null) {
            portBusinessLogic.deletePort(selectedPort);
            loadPorts();
        }
    }

    @FXML
    private void handleViewShips() {
        Port selectedPort = portTableView.getSelectionModel().getSelectedItem();
        if (selectedPort != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/ship.fxml"));
                Parent root = loader.load();

                ShipController shipController = loader.getController();
                shipController.setPort(selectedPort);  // Pass the selected port to the ShipController
                shipController.setPortBusinessLogic(portBusinessLogic);  // Pass the PortBusinessLogic instance

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

    @FXML
    private void handleClearFacility() {
        portBusinessLogic.clearAllPorts();
        loadPorts();
    }
}
