package com.assignment1.ships.module.launcher;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InitPreloader implements Initializable {

    public Label lblLoading;  // Label for displaying loading text
    public static Label lblLoadingg;  // Static reference to the loading label for access across different classes

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoadingg = lblLoading;  // Assign the instance label to the static reference
    }

}
