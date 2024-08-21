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
    private TableView<Container> containerTableView;

    @FXML
    private TableColumn<Container, String> containerCodeColumn;

    @FXML
    private TableColumn<Container, Double> cubicColumn;

    @FXML
    private TableColumn<Container, String> statusColumn;

    private Ship ship;
    private PortBusinessLogic portBusinessLogic;
    private ObservableList<Container> containerList;

    public void setShip(Ship ship) {
        this.ship = ship;
        loadContainers();
    }

    public void setPortBusinessLogic(PortBusinessLogic portBusinessLogic) {
        this.portBusinessLogic = portBusinessLogic;
    }

    @FXML
    public void initialize() {
        containerCodeColumn.setCellValueFactory(new PropertyValueFactory<>("containerCode"));
        cubicColumn.setCellValueFactory(new PropertyValueFactory<>("cubic"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Loaded On Ship", "Unloaded At Port"));
        statusColumn.setOnEditCommit(event -> {
            Container container = event.getRowValue();
            container.setStatus(event.getNewValue());
            portBusinessLogic.saveContainersToFile();
        });

        containerTableView.setEditable(true);
    }

    public void loadContainers() {
        containerList = FXCollections.observableArrayList(ship.getContainers());
        containerTableView.setItems(containerList);
    }

    @FXML
    private void handleAddContainer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-container.fxml"));
            Parent root = loader.load();

            AddContainerController addContainerController = loader.getController();
            addContainerController.setShip(ship);

            Stage stage = new Stage();
            stage.setTitle("Add Container");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadContainers();
            portBusinessLogic.saveContainersToFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteContainer() {
        Container selectedContainer = containerTableView.getSelectionModel().getSelectedItem();
        if (selectedContainer != null) {
            containerList.remove(selectedContainer);
            ship.removeContainer(selectedContainer);

            portBusinessLogic.saveContainersToFile();
        }
    }

    @FXML
    private void handleViewPallets() {
        Container selectedContainer = containerTableView.getSelectionModel().getSelectedItem();
        if (selectedContainer != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/pallet.fxml"));
                Parent root = loader.load();

                PalletController palletController = loader.getController();
                palletController.setContainer(selectedContainer);
                palletController.setPortBusinessLogic(portBusinessLogic);

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



