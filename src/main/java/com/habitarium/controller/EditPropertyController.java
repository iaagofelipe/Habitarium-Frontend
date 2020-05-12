package com.habitarium.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.java.entity.Property;


public class EditPropertyController {
    @FXML
    private Label txtLabel;

    public void initializeScreen(Property property) {
        txtLabel.setText(property.getStreet());
    }
}
