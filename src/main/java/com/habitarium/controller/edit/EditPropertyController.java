package com.habitarium.controller.edit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.dao.PropertyDAO;
import main.java.entity.Property;


public class EditPropertyController {

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

    Property property = new Property();
    PropertyDAO propertyDAO = new PropertyDAO();

    public void initializeScreen(Property property) {
        this.property = property;
        tfStreet.setText(property.getStreet());
        tfNumber.setText((property.getPropertyNumber()));
        tfNeighbour.setText(property.getNeighbour());
        tfCity.setText((property.getCity()));
        tfCondo.setText(property.getCondo());
        tfApartment.setText(property.getApartment());
        tfBlockCondo.setText(property.getBlockCondo());
        initDisabled();
    }

    @FXML
    private void save() {
        if (checkPadding()) {
            property.setBlockCondo(tfBlockCondo.getText().trim());
            property.setCity(tfCity.getText().trim());
            property.setCondo(tfCondo.getText().trim());
            property.setNeighbour(tfNeighbour.getText().trim());
            property.setPropertyNumber(tfNumber.getText().trim());
            property.setStreet(tfStreet.getText().trim());
            property.setApartment(tfApartment.getText().trim());

            propertyDAO.update(property);
            Platform.runLater(() -> saveSucess());
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        } else {
            alertPadding();
        }
    }

    @FXML
    private void delete() {
        propertyDAO.delete(property.getId());
        Platform.runLater(() -> deleteSucess());
        Stage stage = (Stage) btnDelete.getScene().getWindow();
        stage.close();
    }


    private void initDisabled() {
        tfApartment.setDisable(property.getApartment().equals(""));
        tfCondo.setDisable(property.getCondo().equals(""));
        tfBlockCondo.setDisable(property.getBlockCondo().equals(""));
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Propriedade Atualizada!");
        alert.show();
    }

    private void deleteSucess() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Propriedade Deletada!");
        alert.show();
    }
}
