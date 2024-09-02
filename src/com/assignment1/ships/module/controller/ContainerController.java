package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.business.PortBusinessLogic;
import com.assignment1.ships.module.model.Container;
import com.assignment1.ships.module.model.Ship;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ContainerController {

    @FXML
    private TableView<Container> containerTableView;  // TableView for displaying containers

    @FXML
    private TableColumn<Container, String> containerCodeColumn;  // TableColumn for container code

    @FXML
    private TableColumn<Container, Double> cubicColumn;  // TableColumn for cubic capacity

    @FXML
    private TableColumn<Container, String> statusColumn;  // TableColumn for container status

    private Ship ship;  // The ship to which the containers belong
    private PortBusinessLogic portBusinessLogic;  // Business logic for managing ports
    private ObservableList<Container> containerList;  // Observable list of containers

    /**
     * Sets the ship associated with this controller and loads the containers.
     *
     * @param ship The ship object to set.
     */
    public void setShip(Ship ship) {
        this.ship = ship;
        loadContainers();  // Load containers for the ship
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
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        // Initialize the table columns with the respective data properties
        containerCodeColumn.setCellValueFactory(new PropertyValueFactory<>("containerCode"));
        cubicColumn.setCellValueFactory(new PropertyValueFactory<>("cubic"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Set up the status column to use a ComboBox for editing
        statusColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Loaded On Ship", "Unloaded At Port"));
        statusColumn.setOnEditCommit(event -> {
            Container container = event.getRowValue();
            container.setStatus(event.getNewValue());
            portBusinessLogic.saveContainersToFile();  // Save changes to file
        });

        containerTableView.setEditable(true);  // Make the table view editable
    }

    /**
     * Loads the containers for the currently set ship into the table view.
     */
    public void loadContainers() {
        containerList = FXCollections.observableArrayList(ship.getContainers());  // Get containers from ship
        containerTableView.setItems(containerList);  // Set the table items
    }

    /**
     * Handles the action of adding a new container. Opens a new window for adding a container.
     */
    @FXML
    private void handleAddContainer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-container.fxml"));
            Parent root = loader.load();

            // Get the controller for the add container view
            AddContainerController addContainerController = loader.getController();
            addContainerController.setShip(ship);  // Set the ship in the add container controller

            // Set up and show the add container window
            Stage stage = new Stage();
            stage.setTitle("Add Container");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadContainers();  // Reload containers after adding
            portBusinessLogic.saveContainersToFile();  // Save the updated containers list

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of deleting the selected container.
     */
    @FXML
    private void handleDeleteContainer() {
        Container selectedContainer = containerTableView.getSelectionModel().getSelectedItem();
        if (selectedContainer != null) {
            containerList.remove(selectedContainer);  // Remove from the observable list
            ship.removeContainer(selectedContainer);  // Remove from the ship

            portBusinessLogic.saveContainersToFile();  // Save the updated containers list
        }
    }

    /**
     * Handles the action of viewing the pallets within the selected container.
     */
    @FXML
    private void handleViewPallets() {
        Container selectedContainer = containerTableView.getSelectionModel().getSelectedItem();
        if (selectedContainer != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/pallet.fxml"));
                Parent root = loader.load();

                // Get the controller for the pallet view
                PalletController palletController = loader.getController();
                palletController.setContainer(selectedContainer);  // Set the selected container in the pallet controller
                palletController.setPortBusinessLogic(portBusinessLogic);  // Set the business logic in the pallet controller

                // Set up and show the pallets window
                Stage stage = new Stage();
                stage.setTitle("Pallets in " + selectedContainer.getContainerCode());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please select a container first.");
        }
    }
}
