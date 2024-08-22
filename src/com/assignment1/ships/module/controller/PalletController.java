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
    private TableView<Pallet> palletTableView;

    @FXML
    private TableColumn<Pallet, String> companyColumn;

    @FXML
    private TableColumn<Pallet, String> typeOfGoodColumn;

    @FXML
    private TableColumn<Pallet, Double> weightColumn;

    @FXML
    private TableColumn<Pallet, Double> sizeColumn;

    private Container container;
    private PortBusinessLogic portBusinessLogic;
    private ObservableList<Pallet> palletList;

    public void setContainer(Container container) {
        this.container = container;
        loadPallets();
    }

    public void setPortBusinessLogic(PortBusinessLogic portBusinessLogic) {
        this.portBusinessLogic = portBusinessLogic;
    }

    @FXML
    public void initialize() {
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        typeOfGoodColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfGood"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));

        palletTableView.setEditable(false);
    }

    public void loadPallets() {
        palletList = FXCollections.observableArrayList(container.getPallets());
        palletTableView.setItems(palletList);
    }

    @FXML
    private void handleAddPallet() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/add-pallet.fxml"));
            Parent root = loader.load();

            AddPalletController addPalletController = loader.getController();
            addPalletController.setContainer(container);

            Stage stage = new Stage();
            stage.setTitle("Add Pallet");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadPallets();
            portBusinessLogic.savePalletsToFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeletePallet() {
        Pallet selectedPallet = palletTableView.getSelectionModel().getSelectedItem();
        if (selectedPallet != null) {
            palletList.remove(selectedPallet);
            container.removePallet(selectedPallet);
            portBusinessLogic.savePalletsToFile();
        }
    }

    @FXML
    private void handleViewGoods() {
        Pallet selectedPallet = palletTableView.getSelectionModel().getSelectedItem();
        if (selectedPallet != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/good.fxml"));
                Parent root = loader.load();

                GoodController goodController = loader.getController();
                goodController.setPallet(selectedPallet);
                goodController.setPortBusinessLogic(portBusinessLogic);

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
