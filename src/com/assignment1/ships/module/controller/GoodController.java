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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GoodController {

    @FXML
    private TableView<Good> goodTableView;

    @FXML
    private TableColumn<Good, String> nameColumn;

    @FXML
    private TableColumn<Good, Double> priceColumn;

    @FXML
    private TableColumn<Good, Double> weightColumn;

    private Pallet pallet;
    private PortBusinessLogic portBusinessLogic;
    private ObservableList<Good> goodList;

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
        loadGoods();
    }

    public void setPortBusinessLogic(PortBusinessLogic portBusinessLogic) {
        this.portBusinessLogic = portBusinessLogic;
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        goodTableView.setEditable(false);
    }

    public void loadGoods() {
        goodList = FXCollections.observableArrayList(pallet.getGoods());
        goodTableView.setItems(goodList);
    }

    @FXML
    private void handleAddGood() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-good.fxml"));
            Parent root = loader.load();

            AddGoodController addGoodController = loader.getController();
            addGoodController.setPallet(pallet);

            Stage stage = new Stage();
            stage.setTitle("Add Good");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadGoods();
            portBusinessLogic.saveGoodsToFile(); // Save after adding a good

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRemoveGood() {
        Good selectedGood = goodTableView.getSelectionModel().getSelectedItem();
        if (selectedGood != null) {
            goodList.remove(selectedGood);
            pallet.removeGood(selectedGood);
            portBusinessLogic.saveGoodsToFile(); // Save after removing a good
        }
    }
}



