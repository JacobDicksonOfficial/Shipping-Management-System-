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
        shipNameColumn.setCellValueFactory(new PropertyValueFactory<>("shipName"));
        imoNumberColumn.setCellValueFactory(new PropertyValueFactory<>("imoNumber"));
        registrationColumn.setCellValueFactory(new PropertyValueFactory<>("registration"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusColumn.setCellFactory(ComboBoxTableCell.forTableColumn("At Port", "At Sea"));
        statusColumn.setOnEditCommit(event -> {
            Ship ship = event.getRowValue();
            ship.setStatus(event.getNewValue());
            portBusinessLogic.saveShipsToFile();
        });

        shipTableView.setEditable(true);
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
            portBusinessLogic.saveShipsToFile();
        }
    }

    @FXML
    private void handleViewContainers() {
        Ship selectedShip = shipTableView.getSelectionModel().getSelectedItem();
        if (selectedShip != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/container.fxml"));
                Parent root = loader.load();

                // Get the controller and set the ship and PortBusinessLogic instances
                ContainerController containerController = loader.getController();
                containerController.setShip(selectedShip);
                containerController.setPortBusinessLogic(portBusinessLogic);  // Pass the PortBusinessLogic instance

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

