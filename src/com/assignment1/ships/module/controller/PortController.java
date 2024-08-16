package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.business.PortBusinessLogic;
import com.assignment1.ships.module.model.Port;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableColumn<Port, String> portTypeColumn;  // New column for Port Type

    @FXML
    private TableColumn<Port, String> comsColumn;      // New column for COMS

    private PortBusinessLogic portBusinessLogic = new PortBusinessLogic();

    private ObservableList<Port> portList;

    @FXML
    public void initialize() {
        // Initialize the columns.
        portNameColumn.setCellValueFactory(new PropertyValueFactory<>("portName"));
        portCodeColumn.setCellValueFactory(new PropertyValueFactory<>("portCode"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        portTypeColumn.setCellValueFactory(new PropertyValueFactory<>("portType"));  // Bind Port Type
        comsColumn.setCellValueFactory(new PropertyValueFactory<>("coms"));          // Bind COMS

        // Load initial data
        loadPorts();
    }

    public void loadPorts() {
        portList = FXCollections.observableArrayList(portBusinessLogic.getAllPorts());
        portTableView.setItems(portList);
    }

    public void refreshPorts() {
        portList.clear();
        portList.addAll(portBusinessLogic.getAllPorts());
        portTableView.refresh();  // Optional: Call refresh to force the TableView to redraw its contents
    }

    @FXML
    private void handleAddPort() {
        try {
            // Load the FXML for the Add Port window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-port.fxml"));
            Parent root = loader.load();

            // Get the controller associated with the Add Port window
            AddPortController addPortController = loader.getController();
            addPortController.setPortBusinessLogic(portBusinessLogic);

            // Create a new stage (window) for the Add Port form
            Stage stage = new Stage();
            stage.setTitle("Add Port Details");
            stage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows until this one is closed
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Wait until the Add Port window is closed

            // Refresh the ports in the TableView after adding a new port
            refreshPorts();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


