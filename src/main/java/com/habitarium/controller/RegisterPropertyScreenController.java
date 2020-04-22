package com.habitarium.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.java.dao.PropertyDAO;
import main.java.entity.Property;

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
    private TextField txtNeighbour;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtCondo;
    @FXML
    private TextField txtApartment;
    @FXML
    private TextField txtBlockCondo;
    @FXML
    private Button btnSave;
    Property property;

    @FXML
    void save() {
        if (checkPadding()) {
            property = new Property();
            property.setBlockCondo(txtBlockCondo.getText().trim());
            property.setCity(txtCity.getText().trim());
            property.setCondo(txtCondo.getText().trim());
            property.setNeighbour(txtNeighbour.getText().trim());
            property.setPropertyNumber(txtNumber.getText().trim());
            property.setStreet(txtStreet.getText().trim());
            property.setApartment(txtApartment.getText().trim());
            PropertyDAO propertyDAO = new PropertyDAO();
            property = propertyDAO.save(property);
            saveSucess();
        } else {
            alertPadding();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setChooseTypeProperty();
        disableextField();
    }

    public void setChooseTypeProperty() {
        chooseTypeProperty.setItems(FXCollections.observableArrayList(
                "Apartamento", "Casa", "Condomínio")
        );

        chooseTypeProperty.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                if (new_value.intValue() == 0) {
                    txtCity.setDisable(false);
                    txtNeighbour.setDisable(false);
                    txtNumber.setDisable(false);
                    txtStreet.setDisable(false);
                    txtApartment.setDisable(false);
                    txtCondo.setDisable(false);
                    txtBlockCondo.setDisable(false);
                } else if (new_value.intValue() == 1) {
                    txtApartment.setDisable(true);
                    txtCondo.setDisable(true);
                    txtBlockCondo.setDisable(true);
                    txtCity.setDisable(false);
                    txtNeighbour.setDisable(false);
                    txtNumber.setDisable(false);
                    txtStreet.setDisable(false);
                    txtCondo.setText("");
                    txtBlockCondo.setText("");
                    txtApartment.setText("");
                } else if (new_value.intValue() == 2) {
                    txtCity.setDisable(false);
                    txtNeighbour.setDisable(false);
                    txtNumber.setDisable(false);
                    txtStreet.setDisable(false);
                    txtApartment.setDisable(true);
                    txtCondo.setDisable(false);
                    txtBlockCondo.setDisable(false);
                    txtApartment.setText("");
                }
            }
        });
    }

    public boolean checkPadding() {
        boolean isApartment = !txtApartment.getText().trim().equals("") && !txtCondo.getText().trim().equals("")
                && !txtBlockCondo.getText().trim().equals("") && !txtCity.getText().trim().equals("")
                && !txtNeighbour.getText().trim().equals("") && !txtNumber.getText().trim().equals("")
                && !txtStreet.getText().trim().equals("") && chooseTypeProperty.getSelectionModel().getSelectedIndex() == 0;
        boolean isHouse = !txtCity.getText().trim().equals("")
                && !txtNeighbour.getText().trim().equals("") && !txtNumber.getText().trim().equals("")
                && !txtStreet.getText().trim().equals("") && chooseTypeProperty.getSelectionModel().getSelectedIndex() == 1;
        boolean isCondo = !txtCondo.getText().trim().equals("")
                && !txtBlockCondo.getText().trim().equals("") && !txtCity.getText().trim().equals("")
                && !txtNeighbour.getText().trim().equals("") && !txtNumber.getText().trim().equals("")
                && !txtStreet.getText().trim().equals("") && chooseTypeProperty.getSelectionModel().getSelectedIndex() == 2;

        if (isApartment || isHouse || isCondo) {
            System.out.println(chooseTypeProperty.getSelectionModel().getSelectedIndex());
            return true;
        } else {
            return false;
        }
    }

    public void disableextField() {
        txtApartment.setDisable(true);
        txtCondo.setDisable(true);
        txtBlockCondo.setDisable(true);
        txtCity.setDisable(true);
        txtNeighbour.setDisable(true);
        txtNumber.setDisable(true);
        txtStreet.setDisable(true);
    }

    public void alertPadding() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Há campos em branco",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro ao preencher");
        alert.show();
    }

    public void saveSucess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Id da propriedade = " + property.getId(),
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Salvo com sucesso");
        alert.show();
    }
}
