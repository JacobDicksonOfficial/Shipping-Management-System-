package com.assignment1.ships.module.launcher;

import com.assignment1.ships.module.business.PortBusinessLogic;
import com.assignment1.ships.module.controller.PortController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    private PortBusinessLogic portBusinessLogic;

    @Override
    public void init() throws Exception {
        // Initialize the PortBusinessLogic to load existing data
        portBusinessLogic = new PortBusinessLogic();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Show the preloader
        Stage preloaderStage = new Stage();
        Parent preloaderRoot = FXMLLoader.load(getClass().getResource("/com/assignment1/ships/module/resource/initPreloader.fxml"));
        Scene preloaderScene = new Scene(preloaderRoot);
        preloaderStage.setScene(preloaderScene);
        preloaderStage.show();

        // Simulate some background work
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Simulate long initialization
                Thread.sleep(3000);
                return null;
            }

            @Override
            protected void succeeded() {
                // Load the main application window (port.fxml)
                Platform.runLater(() -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/assignment1/ships/module/resource/port.fxml"));
                        Parent root = loader.load();
                        PortController portController = loader.getController();
                        portController.setPortBusinessLogic(portBusinessLogic);

                        primaryStage.setScene(new Scene(root));
                        primaryStage.setTitle("Shipping Management System");
                        primaryStage.show();

                        // Close the preloader
                        preloaderStage.hide();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        };

        // Start the background task
        new Thread(task).start();
    }

    @Override
    public void stop() throws Exception {
        // Save the ports and ships before the application exits
        portBusinessLogic.savePortsToFile();
        portBusinessLogic.saveShipsToFile();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}





