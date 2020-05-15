package com.habitarium.controller.edit;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.entity.Property;

import java.net.URL;
import java.util.ResourceBundle;


public class EditPropertyController implements Initializable {
//    @FXML
//    private Label txtLabel;

@FXML
    private TextField tfStreet;

    @FXML
    private TextField tfNumber;

    @FXML
    private TextField tfNeighbour;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfCondo;

    @FXML
    private TextField tfApartment;

    @FXML
    private TextField tfBlockCondo;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

//    public void initializeScreen(Property property) {
//        txtLabel.setText(property.getStreet());
//    }


}
