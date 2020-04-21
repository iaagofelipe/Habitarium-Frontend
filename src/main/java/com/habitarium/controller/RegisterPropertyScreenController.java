package com.habitarium.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
        if (checkPadding()) {
            System.out.println("true");
        } else {
            alertLogin();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setChooseTypeProperty();
        setTextField();
    }

    public void setChooseTypeProperty() {
        chooseTypeProperty.setItems(FXCollections.observableArrayList(
                "Apartamento", "Casa", "Condomínio")
        );

        chooseTypeProperty.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                if (new_value.intValue() == 0) {
                    txtCity.setDisable(false);
                    txtNeighbourneighbour.setDisable(false);
                    txtNumber.setDisable(false);
                    txtStreet.setDisable(false);
                    txtApartmentapartment.setDisable(false);
                    txtCondo.setDisable(false);
                    txtBlockCondoblockCondo.setDisable(false);
                } else if (new_value.intValue() == 1) {
                    txtApartmentapartment.setDisable(true);
                    txtCondo.setDisable(true);
                    txtBlockCondoblockCondo.setDisable(true);
                    txtCity.setDisable(false);
                    txtNeighbourneighbour.setDisable(false);
                    txtNumber.setDisable(false);
                    txtStreet.setDisable(false);
                }
            }
        });
    }

    public boolean checkPadding() {
        boolean isApartment = !txtApartmentapartment.getText().trim().equals("") && !txtCondo.getText().trim().equals("")
                && !txtBlockCondoblockCondo.getText().trim().equals("") && !txtCity.getText().trim().equals("")
                && !txtNeighbourneighbour.getText().trim().equals("") && !txtNumber.getText().trim().equals("")
                && !txtStreet.getText().trim().equals("") && chooseTypeProperty.getSelectionModel().getSelectedIndex() == 0;
        boolean isHouse = !txtCity.getText().trim().equals("")
                && !txtNeighbourneighbour.getText().trim().equals("") && !txtNumber.getText().trim().equals("")
                && !txtStreet.getText().trim().equals("") && chooseTypeProperty.getSelectionModel().getSelectedIndex() == 1;
        boolean isCondo = !txtCondo.getText().trim().equals("")
                && !txtBlockCondoblockCondo.getText().trim().equals("") && !txtCity.getText().trim().equals("")
                && !txtNeighbourneighbour.getText().trim().equals("") && !txtNumber.getText().trim().equals("")
                && !txtStreet.getText().trim().equals("") && chooseTypeProperty.getSelectionModel().getSelectedIndex() == 2;

        if (isApartment || isHouse || isCondo) {
            System.out.println(chooseTypeProperty.getSelectionModel().getSelectedIndex());
            return true;
        } else {
            return false;
        }
    }

    public void setTextField() {
        txtApartmentapartment.setDisable(true);
        txtCondo.setDisable(true);
        txtBlockCondoblockCondo.setDisable(true);
        txtCity.setDisable(true);
        txtNeighbourneighbour.setDisable(true);
        txtNumber.setDisable(true);
        txtStreet.setDisable(true);
    }

    public void alertLogin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Há campos em branco",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro ao preencher");
        alert.show();
    }
}
