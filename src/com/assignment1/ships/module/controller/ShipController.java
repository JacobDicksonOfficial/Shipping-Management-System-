package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.business.PortBusinessLogic;
import com.assignment1.ships.module.model.Port;
import com.assignment1.ships.module.model.Ship;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ShipController {

    @FXML
    private TableView<Ship> shipTableView;  // TableView for displaying ships

    @FXML
    private TableColumn<Ship, String> shipNameColumn;  // TableColumn for the ship name

    @FXML
    private TableColumn<Ship, String> imoNumberColumn;  // TableColumn for the IMO number

    @FXML
    private TableColumn<Ship, String> registrationColumn;  // TableColumn for the registration code

    @FXML
    private TableColumn<Ship, String> urlColumn;  // TableColumn for the ship's URL

    @FXML
    private TableColumn<Ship, Integer> capacityColumn;  // TableColumn for the ship's capacity

    @FXML
    private TableColumn<Ship, String> statusColumn;  // TableColumn for the ship's status

    private ObservableList<Ship> shipList;  // Observable list of ships

    private Port port;  // The port to which the ships belong

    private PortBusinessLogic portBusinessLogic;  // Business logic for managing ports

    /**
     * Sets the port associated with this controller and loads the ships.
     *
     * @param port The port object to set.
     */
    public void setPort(Port port) {
        this.port = port;
        loadShips();  // Load ships for the port
    }

    /**
     * Sets the PortBusinessLogic instance used to manage port operations.
     *
     * @param portBusinessLogic The PortBusinessLogic instance.
     */
    public void setPortBusinessLogic(PortBusinessLogic portBusinessLogic) {
        this.portBusinessLogic = portBusinessLogic;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        // Initialize the table columns with the respective data properties
        shipNameColumn.setCellValueFactory(new PropertyValueFactory<>("shipName"));
        imoNumberColumn.setCellValueFactory(new PropertyValueFactory<>("imoNumber"));
        registrationColumn.setCellValueFactory(new PropertyValueFactory<>("registration"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Set up the status column to use a ComboBox for editing
        statusColumn.setCellFactory(ComboBoxTableCell.forTableColumn("At Port", "At Sea"));
        statusColumn.setOnEditCommit(event -> {
            Ship ship = event.getRowValue();
            ship.setStatus(event.getNewValue());
            portBusinessLogic.saveShipsToFile();  // Save changes to file
        });

        shipTableView.setEditable(true);  // Make the table view editable
    }

    /**
     * Loads the ships for the currently set port into the table view.
     */
    public void loadShips() {
        shipList = FXCollections.observableArrayList(port.getShips());  // Get ships from port
        shipTableView.setItems(shipList);  // Set the table items
    }

    /**
     * Handles the action of adding a new ship. Opens a new window for adding a ship.
     */
    @FXML
    private void handleAddShip() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-ship.fxml"));
            Parent root = loader.load();

            // Get the controller for the add ship view
            AddShipController addShipController = loader.getController();
            addShipController.setPort(port);  // Set the port in the add ship controller

            // Set up and show the add ship window
            Stage stage = new Stage();
            stage.setTitle("Add Ship");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadShips();  // Reload ships after adding
            portBusinessLogic.saveShipsToFile();  // Save the updated ships list

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of deleting the selected ship.
     */
    @FXML
    private void handleDeleteShip() {
        Ship selectedShip = shipTableView.getSelectionModel().getSelectedItem();
        if (selectedShip != null) {
            shipList.remove(selectedShip);  // Remove from the observable list
            port.removeShip(selectedShip);  // Remove from the port
            portBusinessLogic.saveShipsToFile();  // Save the updated ships list
        }
    }

    /**
     * Handles the action of viewing the containers associated with the selected ship.
     */
    @FXML
    private void handleViewContainers() {
        Ship selectedShip = shipTableView.getSelectionModel().getSelectedItem();
        if (selectedShip != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/container.fxml"));
                Parent root = loader.load();

                // Get the controller and set the ship and PortBusinessLogic instances
                ContainerController containerController = loader.getController();
                containerController.setShip(selectedShip);  // Set the selected ship in the container controller
                containerController.setPortBusinessLogic(portBusinessLogic);  // Pass the PortBusinessLogic instance

                // Set up and show the containers window
                Stage stage = new Stage();
                stage.setTitle("Containers in " + selectedShip.getShipName());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please select a ship first.");
        }
    }
}
