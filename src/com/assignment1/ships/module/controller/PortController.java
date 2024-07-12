package com.assignment1.ships.module.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PortController {

    @FXML
    private TextField noteTextField;

    @FXML
    private Button addNoteButton;

    @FXML
    private void initialize() {
        // Initialization code, if needed
    }

    @FXML
    private void handleAddNote() {
        String noteText = noteTextField.getText();
        // Process the noteText, e.g., save to a database, display on UI, etc.
        System.out.println("Added note: " + noteText);
    }
}

