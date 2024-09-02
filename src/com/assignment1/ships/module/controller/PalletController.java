package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.business.PortBusinessLogic;
import com.assignment1.ships.module.model.Container;
import com.assignment1.ships.module.model.Pallet;
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

public class PalletController {

    @FXML
    private TableView<Pallet> palletTableView;  // TableView for displaying pallets

    @FXML
    private TableColumn<Pallet, String> companyColumn;  // TableColumn for the company name associated with the pallet

    @FXML
    private TableColumn<Pallet, String> typeOfGoodColumn;  // TableColumn for the type of goods on the pallet

    @FXML
    private TableColumn<Pallet, Double> weightColumn;  // TableColumn for the weight of the pallet

    @FXML
    private TableColumn<Pallet, Double> sizeColumn;  // TableColumn for the size of the pallet

    private Container container;  // The container to which the pallets belong
    private PortBusinessLogic portBusinessLogic;  // Business logic for managing ports
    private ObservableList<Pallet> palletList;  // Observable list of pallets

    /**
     * Sets the container associated with this controller and loads the pallets.
     *
     * @param container The container object to set.
     */
    public void setContainer(Container container) {
        this.container = container;
        loadPallets();  // Load pallets for the container
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
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        typeOfGoodColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfGood"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));

        palletTableView.setEditable(false);  // Make the table view non-editable
    }

    /**
     * Loads the pallets for the currently set container into the table view.
     */
    public void loadPallets() {
        palletList = FXCollections.observableArrayList(container.getPallets());  // Get pallets from container
        palletTableView.setItems(palletList);  // Set the table items
    }

    /**
     * Handles the action of adding a new pallet. Opens a new window for adding a pallet.
     */
    @FXML
    private void handleAddPallet() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-pallet.fxml"));
            Parent root = loader.load();

            // Get the controller for the add pallet view
            AddPalletController addPalletController = loader.getController();
            addPalletController.setContainer(container);  // Set the container in the add pallet controller

            // Set up and show the add pallet window
            Stage stage = new Stage();
            stage.setTitle("Add Pallet");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadPallets();  // Reload pallets after adding
            portBusinessLogic.savePalletsToFile();  // Save the updated pallets list

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of deleting the selected pallet.
     */
    @FXML
    private void handleDeletePallet() {
        Pallet selectedPallet = palletTableView.getSelectionModel().getSelectedItem();
        if (selectedPallet != null) {
            palletList.remove(selectedPallet);  // Remove from the observable list
            container.removePallet(selectedPallet);  // Remove from the container
            portBusinessLogic.savePalletsToFile();  // Save the updated pallets list
        }
    }

    /**
     * Handles the action of viewing the goods within the selected pallet.
     */
    @FXML
    private void handleViewGoods() {
        Pallet selectedPallet = palletTableView.getSelectionModel().getSelectedItem();
        if (selectedPallet != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/good.fxml"));
                Parent root = loader.load();

                // Get the controller for the goods view
                GoodController goodController = loader.getController();
                goodController.setPallet(selectedPallet);  // Set the selected pallet in the goods controller
                goodController.setPortBusinessLogic(portBusinessLogic);  // Set the business logic in the goods controller

                // Set up and show the goods window
                Stage stage = new Stage();
                stage.setTitle("Goods in " + selectedPallet.getTypeOfGood());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please select a pallet first.");
        }
    }
}
