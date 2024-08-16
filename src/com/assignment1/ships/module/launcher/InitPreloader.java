package com.assignment1.ships.module.launcher;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitPreloader implements Initializable {

    public Label lblLoading;
    public static Label lblLoadingg;
    private Stage preloaderStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoadingg = lblLoading;
    }

    public void setPreloaderStage(Stage preloaderStage) {
        this.preloaderStage = preloaderStage;
    }

    public void checkFunctions() {
        Thread t1 = new Thread(() -> {
            updateLoadingMessage("Welcome");
            sleepThread(1000);
        });

        Thread t2 = new Thread(() -> {
            updateLoadingMessage("Loading");
            sleepThread(1000);
        });


        Thread t3 = new Thread(() -> {
            updateLoadingMessage("Opening Hub");
            sleepThread(3000); // Delay for 5 seconds
            Platform.runLater(() -> {
                preloaderStage.hide(); // Hide the preloader after 5 seconds
                showMainApplicationStage();
            });
        });

        t1.start();
        t2.start();
        t3.start();
    }

    private void updateLoadingMessage(String message) {
        Platform.runLater(() -> lblLoadingg.setText(message));
        sleepThread(1000); // Simulate some processing time
    }

    private void showMainApplicationStage() {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/assignment1/ships/module/resource/port.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Shipping Management System");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sleepThread(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
