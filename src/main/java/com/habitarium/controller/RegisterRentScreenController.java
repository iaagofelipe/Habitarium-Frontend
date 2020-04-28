package com.habitarium.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.dao.LessorDAO;
import main.java.dao.RentDAO;
import main.java.entity.Lessor;
import main.java.entity.Property;
import main.java.entity.Rent;
import main.java.enuns.Gender;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterRentScreenController implements Initializable {
    @FXML
    private TextField txtName;

    @FXML
    private DatePicker chooseBornDate;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtRg;

    @FXML
    private TextField txtTel1;

    @FXML
    private TextField txtTel2;

    @FXML
    private ComboBox<Gender> chooseGender;

    @FXML
    private ComboBox<String> chooseProperty;

    @FXML
    private TextField txtValorAluguel;

    @FXML
    private Spinner<Integer> ChoosePayDay;

    @FXML
    private DatePicker chooseEntranceDate;

    @FXML
    private DatePicker chooseExitDate;

    @FXML
    private DatePicker chooseReadjustment;

    @FXML
    private Button rentBtnSave;
    Rent rent;
    Lessor lessor;

    @FXML
    void save() {
        if (checkTxtPadding()) {
            rent = new Rent();
            lessor = new Lessor();
            lessor.setName(txtName.getText().trim());
            lessor.setCpf(txtCpf.getText().trim());
            lessor.setRg(txtRg.getText().trim());
            lessor.setTelOne(txtTel1.getText().trim());
            lessor.setTelTwo((txtTel2.getText().trim()));
            LessorDAO lessorDAO = new LessorDAO();
            lessor = lessorDAO.save(lessor);

            //Isso ainda é necessário implementar as datas, que não consegui
            rent.setValue(Float.parseFloat(txtValorAluguel.getText().trim()));
            RentDAO rentDAO = new RentDAO();
            rent = rentDAO.save(rent);

            saveSucess();
            Stage stage = (Stage) rentBtnSave.getScene().getWindow();
            stage.close();
        } else {
            alertPadding();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("passei por aqui");
        setSpinner();
    }
    void setSpinner(){
        Spinner<Integer> spinner = new Spinner<>();
        int initialValue = 5;
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, initialValue);
        spinner.setValueFactory(valueFactory);
    }

    public boolean checkTxtPadding() {
        boolean registerLessor = !txtName.getText().trim().equals("") && !txtCpf.getText().trim().equals("")
                && !txtRg.getText().trim().equals("") && !txtTel1.getText().trim().equals("")
                && !txtTel2.getText().trim().equals("");
        boolean registerRent = !txtValorAluguel.getText().trim().equals("");
        return registerLessor;
    }

//    public boolean checkDate_EnumPadding() {
//        boolean date_and_enum = chooseBornDate.get
//    }

    private void alertPadding() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Há campos em branco",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro ao preencher");
        alert.show();
    }
    public void saveSucess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("salvo com sucesso!");
        alert.show();
    }
}
