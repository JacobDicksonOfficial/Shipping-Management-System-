package com.assignment1.ships.module.launcher;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InitPreloader implements Initializable {

    public Label lblLoading;
    public static Label lblLoadingg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoadingg = lblLoading;
    }

}
