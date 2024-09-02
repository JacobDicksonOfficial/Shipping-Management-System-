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

    private PortBusinessLogic portBusinessLogic;  // Business logic for managing ports

    /**
     * Initializes the application. This method is called before the start method.
     * It is used to initialize the PortBusinessLogic and load existing data.
     *
     * @throws Exception if an error occurs during initialization.
     */
    @Override
    public void init() throws Exception {
        // Initialize the PortBusinessLogic to load existing data
        portBusinessLogic = new PortBusinessLogic();
    }

    /**
     * Starts the JavaFX application. This method sets up the initial stage and
     * scene, shows the preloader, and starts background tasks for initializing
     * the application.
     *
     * @param primaryStage The primary stage for this application.
     * @throws Exception if an error occurs during startup.
     */
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
                Thread.sleep(3000);  // Simulate a delay (e.g., loading resources)
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
                        portController.setPortBusinessLogic(portBusinessLogic);  // Pass the business logic to the controller

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

    /**
     * Stops the application. This method is called when the application is about
     * to exit. It is used to save the ports and ships before the application exits.
     *
     * @throws Exception if an error occurs during shutdown.
     */
    @Override
    public void stop() throws Exception {
        // Save the ports and ships before the application exits
        portBusinessLogic.savePortsToFile();
        portBusinessLogic.saveShipsToFile();
        super.stop();
    }

    /**
     * The main entry point for the application. This method launches the JavaFX
     * application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
