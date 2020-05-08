package com.habitarium.controller;

import com.habitarium.utils.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.java.dao.PropertyDAO;
import main.java.dao.RentDAO;
import main.java.entity.Lessor;
import main.java.entity.Property;
import main.java.entity.Rent;
import main.java.enuns.Gender;

import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterRentScreenController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private DatePicker datePBorn;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtRg;
    @FXML
    private TextField txtTel1;
    @FXML
    private TextField txtTel2;
    @FXML
    private ComboBox<Gender> cbGender;
    @FXML
    private ComboBox<String> cbProperty;
    @FXML
    private TextField txtRentValue;
    @FXML
    private Spinner<Integer> spPayDay;
    @FXML
    private DatePicker datePEntrance;
    @FXML
    private DatePicker datePExit;
    @FXML
    private DatePicker datePReadjustment;
    @FXML
    private Button btnSave;

    private final String PATTERN_MATCHES_NUMBERS = "[0-9]";
    private final String PATTERN_MATCHES_RG = "[0-9]";
    private final String PATTERN_MATCHES_CPF = "[0-9]";
    private final String PATTERN_MATCHES_RENT_VALUE = "[0-9,]";
    private final int RENT_VALUE_LENGTH = 10;
    private final int TEL_LENGTH = 11;
    private final int RG_LENGTH = 12;
    private final int CPF_LENGTH = 14;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPhoneTextInput();
        setRGTextInput();
        setCPFTextInput();
        setSpinner();
        setComboBox();
        setTxtRentValue();
        getListFreeProperties();
    }

    @FXML
    void save() {
        Rent rent = new Rent();
        Lessor lessor = new Lessor();
        if (checkTxtPadding() && checkGenderPadding()) {
            lessor.setName(txtName.getText().trim());
            lessor.setCpf(txtCpf.getText().trim());
            lessor.setRg(txtRg.getText().trim());
            lessor.setTelOne(txtTel1.getText().trim());
            lessor.setTelTwo((txtTel2.getText().trim()));
            lessor.setGender(cbGender.getValue());

            try {
                Date entranceDate = Date.from(datePEntrance.getValue()
                        .atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date exitDate = Date.from(datePExit.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date readjustmentDate = Date.from(datePReadjustment.getValue()
                        .atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date BornDate = Date.from(datePBorn.getValue()
                        .atStartOfDay(ZoneId.systemDefault()).toInstant());

                String txtRentValue_replacement = txtRentValue.getText().trim().replaceAll(",", ".");
                rent.setValue(Float.parseFloat(txtRentValue_replacement));
                rent.setEntranceDate(entranceDate);
                rent.setExitDate(exitDate);
                rent.setReadjustmentDate(readjustmentDate);
                rent.setPayDay(spPayDay.getValue());
                lessor.setRent(rent);
                rent.setLessor(lessor);

                RentDAO rentDAO = new RentDAO();
                rentDAO.save(rent);

                saveSucess();
                Stage stage = (Stage) btnSave.getScene().getWindow();
                stage.close();

            } catch (java.lang.NullPointerException e) {
                alertDateInvalid();
            }
        } else {
            alertPadding();
        }
    }

    private void setSpinner() {
        int initialValue = 5;
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.
                IntegerSpinnerValueFactory(1, DateUtil.lastDayCurrentMonth(), initialValue);
        spPayDay.setValueFactory(valueFactory);
    }

    private void setComboBox() {
        ObservableList<Gender> list = FXCollections.observableArrayList(Gender.MALE, Gender.FEMALE, Gender.OTHERS);
        cbGender.setItems(FXCollections.observableList(list));
    }

    public boolean checkTxtPadding() {
        boolean registerLessor = !txtName.getText().trim().equals("") && !txtCpf.getText().trim().equals("")
                && !txtRg.getText().trim().equals("") && !txtTel1.getText().trim().equals("")
                && !txtTel2.getText().trim().equals("");
        boolean registerRent = !txtRentValue.getText().trim().equals("");
        return registerLessor && registerRent;
    }

    public boolean checkGenderPadding() {
        return cbGender.getSelectionModel().getSelectedIndex() != -1;
    }

    private void alertPadding() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Há campos em branco", ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro ao preencher");
        alert.show();
    }

    private void alertCpfInvalid() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Formato do CPF inválido", ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro de CPF");
        alert.show();
    }

    private void alertRgInvalid() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Formato do RG inválido",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro de RG");
        alert.show();
    }

    private void alertDateInvalid() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Há campos em branco ou preenchidos de forma incorreta",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro de data");
        alert.show();
    }

    public void saveSucess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("salvo com sucesso!");
        alert.show();
    }

    private void setPhoneTextInput() {
        txtTel1.addEventFilter(KeyEvent.KEY_TYPED, getPatternValidation(PATTERN_MATCHES_NUMBERS));
        txtTel1.textProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue.length() > TEL_LENGTH) {
                txtTel1.setText(oldValue);
            }
        });
        txtTel2.addEventFilter(KeyEvent.KEY_TYPED, getPatternValidation(PATTERN_MATCHES_NUMBERS));
        txtTel2.textProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue.length() > TEL_LENGTH) {
                txtTel2.setText(oldValue);
            }
        });
    }

    private void setRGTextInput() {
        txtRg.addEventFilter(KeyEvent.KEY_TYPED, getPatternValidation(PATTERN_MATCHES_RG));
        txtRg.textProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue.length() > RG_LENGTH) {
                txtRg.setText(oldValue);
            }
            if (newValue.length() == 10 && oldValue.length() <= 10) {
                txtRg.setText(oldValue + "-");
            }
        });
    }

    private void setCPFTextInput() {
        txtCpf.addEventFilter(KeyEvent.KEY_TYPED, getPatternValidation(PATTERN_MATCHES_CPF));
        txtCpf.textProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue.length() > CPF_LENGTH) {
                txtCpf.setText(oldValue);
            }
            if ((newValue.length() == 3 && oldValue.length() <= 3) || (newValue.length() == 7 &&
                    oldValue.length() <= 7)) {
                txtCpf.setText(oldValue + ".");
            }
            if (newValue.length() == 11 && oldValue.length() <= 11) {
                txtCpf.setText(oldValue + "-");
            }
        });
    }

    private void setTxtRentValue() {
        txtRentValue.addEventFilter(KeyEvent.KEY_TYPED, getPatternValidation(PATTERN_MATCHES_RENT_VALUE));
        txtRentValue.textProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue.length() > RENT_VALUE_LENGTH) {
                txtRentValue.setText(oldValue);
            }
        });
    }

    public static EventHandler<KeyEvent> getPatternValidation(String pattern) {
        return e -> {
            String typed = e.getCharacter();
            if (!typed.matches(pattern)) {
                e.consume();
            }
        };
    }

    private void getListFreeProperties() {
        PropertyDAO propertyDAO = new PropertyDAO();
        List<Property> freeProperties = propertyDAO.getPropertyNotRented();
        if (freeProperties != null && !freeProperties.isEmpty()) {
            for (Property property : freeProperties) {
                System.out.println(property);
            }
        }
    }

    
}

