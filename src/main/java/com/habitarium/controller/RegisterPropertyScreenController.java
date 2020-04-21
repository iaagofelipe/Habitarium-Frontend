package com.habitarium.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPropertyScreenController implements Initializable {

    @FXML
    private ChoiceBox<String> chooseTypeProperty;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtNeighbourneighbour;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtCondo;
    @FXML
    private TextField txtApartmentapartment;
    @FXML
    private TextField txtBlockCondoblockCondo;
    @FXML
    private Button btnSave;

    @FXML
    void save() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setChooseTypeProperty();
    }

    public void setChooseTypeProperty() {
        chooseTypeProperty.setItems(FXCollections.observableArrayList(
                "Apartamento", "Casa")
        );

        chooseTypeProperty.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                if (new_value.intValue() == 0){
                    System.out.println("Apartamento");
                } else if (new_value.intValue() == 1){
                    System.out.println("Casa");
                }
            }
        });
    }
}
