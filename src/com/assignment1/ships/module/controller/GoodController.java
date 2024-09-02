package com.assignment1.ships.module.controller;

import com.assignment1.ships.module.business.PortBusinessLogic;
import com.assignment1.ships.module.model.Good;
import com.assignment1.ships.module.model.Pallet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoodController {

    @FXML
    private TableView<Good> goodTableView;  // TableView for displaying goods

    @FXML
    private TableColumn<Good, String> nameColumn;  // TableColumn for the name of the goods

    @FXML
    private TableColumn<Good, Double> priceColumn;  // TableColumn for the price of the goods

    @FXML
    private TableColumn<Good, Double> weightColumn;  // TableColumn for the weight of the goods

    @FXML
    private TextField searchField;  // TextField for searching goods by name

    private Pallet pallet;  // The pallet to which the goods belong
    private PortBusinessLogic portBusinessLogic;  // Business logic for managing ports
    private ObservableList<Good> goodList;  // Observable list of goods

    /**
     * Sets the pallet associated with this controller and loads the goods.
     *
     * @param pallet The pallet object to set.
     */
    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
        loadGoods();  // Load goods for the pallet
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
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        goodTableView.setEditable(false);  // Make the table view non-editable

        // Handle search field input to filter goods by name
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchGoodsByName(newValue));
    }

    /**
     * Loads the goods for the currently set pallet into the table view.
     */
    public void loadGoods() {
        goodList = FXCollections.observableArrayList(pallet.getGoods());  // Get goods from pallet
        goodTableView.setItems(goodList);  // Set the table items
    }

    /**
     * Searches for goods by name and updates the table view.
     *
     * @param name The name of the goods to search for.
     */
    private void searchGoodsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            goodTableView.setItems(goodList);  // Reset to the full list if the search is empty
            return;
        }

        List<Good> foundGoods = new ArrayList<>();
        for (Pallet pallet : portBusinessLogic.getAllPallets()) {  // Search in all pallets
            for (Good good : pallet.getGoods()) {
                if (good.getName().equalsIgnoreCase(name.trim())) {
                    foundGoods.add(good);  // Add goods that match the search term
                }
            }
        }

        goodTableView.setItems(FXCollections.observableArrayList(foundGoods));  // Update table with found goods
    }

    /**
     * Handles the action of adding a new good. Opens a new window for adding a good.
     */
    @FXML
    private void handleAddGood() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-good.fxml"));
            Parent root = loader.load();

            // Get the controller for the add good view
            AddGoodController addGoodController = loader.getController();
            addGoodController.setPallet(pallet);  // Set the pallet in the add good controller

            // Set up and show the add good window
            Stage stage = new Stage();
            stage.setTitle("Add Good");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadGoods();  // Reload goods after adding
            portBusinessLogic.saveGoodsToFile();  // Save the updated goods list

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of removing the selected good.
     */
    @FXML
    private void handleRemoveGood() {
        Good selectedGood = goodTableView.getSelectionModel().getSelectedItem();
        if (selectedGood != null) {
            goodList.remove(selectedGood);  // Remove from the observable list
            pallet.removeGood(selectedGood);  // Remove from the pallet
            portBusinessLogic.saveGoodsToFile();  // Save the updated goods list
        }
    }
}
