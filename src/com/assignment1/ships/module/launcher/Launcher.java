package com.assignment1.ships.module.launcher;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void init() throws Exception {
        // Perform initialization tasks here if needed
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Show the preloader while performing initialization
        LauncherPreloader preloader = new LauncherPreloader();
        preloader.start(primaryStage);

        // Simulate some initialization process
        InitPreloader initPreloader = new InitPreloader();
        initPreloader.setPreloaderStage(primaryStage);
        initPreloader.checkFunctions();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        Application.launch(Launcher.class, args);
    }
}


