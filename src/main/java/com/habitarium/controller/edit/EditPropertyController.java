package com.habitarium.controller.edit;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.dao.PropertyDAO;
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

    public void initializeScreen(Property property) {
        tfStreet.setText(property.getStreet());
        tfNumber.setText((property.getPropertyNumber()));
        tfNeighbour.setText(property.getNeighbour());
        tfCity.setText((property.getCity()));
        tfCondo.setText(property.getCondo());
        tfApartment.setText(property.getApartment());
        tfBlockCondo.setText(property.getBlockCondo());
    }

    @FXML
    void save() {
        if (checkPadding()) {
            Property property = new Property();
            property.setBlockCondo(tfBlockCondo.getText().trim());
            property.setCity(tfCity.getText().trim());
            property.setCondo(tfCondo.getText().trim());
            property.setNeighbour(tfNeighbour.getText().trim());
            property.setPropertyNumber(tfNumber.getText().trim());
            property.setStreet(tfStreet.getText().trim());
            property.setApartment(tfApartment.getText().trim());
            PropertyDAO propertyDAO = new PropertyDAO();
            propertyDAO.update(property);
            saveSucess();
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        } else {
            alertPadding();
        }
    }

    public boolean checkPadding() {
        boolean isApartment = !tfApartment.getText().trim().equals("") && !tfCondo.getText().trim().equals("")
                && !tfBlockCondo.getText().trim().equals("") && !tfCity.getText().trim().equals("")
                && !tfNeighbour.getText().trim().equals("") && !tfNumber.getText().trim().equals("")
                && !tfStreet.getText().trim().equals("");
        boolean isHouse = !tfCity.getText().trim().equals("")
                && !tfNeighbour.getText().trim().equals("") && !tfNumber.getText().trim().equals("")
                && !tfStreet.getText().trim().equals("");
        boolean isCondo = !tfCondo.getText().trim().equals("")
                && !tfBlockCondo.getText().trim().equals("") && !tfCity.getText().trim().equals("")
                && !tfNeighbour.getText().trim().equals("") && !tfNumber.getText().trim().equals("")
                && !tfStreet.getText().trim().equals("");

        if (isApartment || isHouse || isCondo) {
            return true;
        } else {
            return false;
        }
    }

    private void alertPadding() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Há campos em branco",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro ao preencher");
        alert.show();
    }

    private void saveSucess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Propriedade editada com sucesso!");
        alert.show();
    }
}
