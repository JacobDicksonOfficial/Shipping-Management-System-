package com.assignment1.ships.module.launcher;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LauncherPreloader extends Preloader {

    private Stage preloaderStage;  // The stage used to display the preloader

    /**
     * Starts the preloader stage. This method sets up the initial stage and scene
     * for the preloader using the specified FXML file.
     *
     * @param primaryStage The primary stage for the preloader.
     * @throws Exception if an error occurs during the loading of the FXML file or setting up the scene.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/com/assignment1/ships/module/resource/initPreloader.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Handles state change notifications from the application. Specifically,
     * this method hides the preloader stage when the main application is ready
     * to start.
     *
     * @param info The state change notification information.
     */
    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if (info.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.hide();  // Hide the preloader stage when the main application is ready to start
        }
    }
}
