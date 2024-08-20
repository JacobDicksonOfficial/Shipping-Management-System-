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
    private TableView<Ship> shipTableView;

    @FXML
    private TableColumn<Ship, String> shipNameColumn;

    @FXML
    private TableColumn<Ship, String> imoNumberColumn;

    @FXML
    private TableColumn<Ship, String> registrationColumn;

    @FXML
    private TableColumn<Ship, String> urlColumn;

    @FXML
    private TableColumn<Ship, Integer> capacityColumn;

    @FXML
    private TableColumn<Ship, String> statusColumn;

    private ObservableList<Ship> shipList;

    private Port port;

    private PortBusinessLogic portBusinessLogic;

    public void setPort(Port port) {
        this.port = port;
        loadShips();
    }

    public void setPortBusinessLogic(PortBusinessLogic portBusinessLogic) {
        this.portBusinessLogic = portBusinessLogic;
    }

    @FXML
    public void initialize() {
        // Initialize the columns
        shipNameColumn.setCellValueFactory(new PropertyValueFactory<>("shipName"));
        imoNumberColumn.setCellValueFactory(new PropertyValueFactory<>("imoNumber"));
        registrationColumn.setCellValueFactory(new PropertyValueFactory<>("registration"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Set up ComboBoxTableCell for the status column
        statusColumn.setCellFactory(ComboBoxTableCell.forTableColumn("At Port", "At Sea"));
        statusColumn.setOnEditCommit(event -> {
            Ship ship = event.getRowValue();
            ship.setStatus(event.getNewValue());
            portBusinessLogic.saveShipsToFile();  // Save the updated status to the file
        });

        shipTableView.setEditable(true);  // Enable editing for the table
    }

    public void loadShips() {
        shipList = FXCollections.observableArrayList(port.getShips());
        shipTableView.setItems(shipList);
    }

    @FXML
    private void handleAddShip() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-ship.fxml"));
            Parent root = loader.load();

            AddShipController addShipController = loader.getController();
            addShipController.setPort(port);

            Stage stage = new Stage();
            stage.setTitle("Add Ship");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadShips();

            // Save ships to file after adding a ship
            portBusinessLogic.saveShipsToFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteShip() {
        Ship selectedShip = shipTableView.getSelectionModel().getSelectedItem();
        if (selectedShip != null) {
            shipList.remove(selectedShip);
            port.removeShip(selectedShip);
            portBusinessLogic.saveShipsToFile();  // Save ships to file after deleting a ship
        }
    }
}
