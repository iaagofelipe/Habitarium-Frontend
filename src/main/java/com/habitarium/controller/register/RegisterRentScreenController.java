package com.habitarium.controller.register;

import com.habitarium.utils.date.DateUtil;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private ComboBox<Property> cbProperty;
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
    private final int RG_LENGTH = 13;
    private final int CPF_LENGTH = 14;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPhoneTextInput();
        setRGTextInput();
        setCPFTextInput();
        setSpinner();
        setComboBox();
        setTxtRentValue();
        setCbPropertyNotRented();
    }

    @FXML
    private void save() {
        Rent rent = new Rent();
        Lessor lessor = new Lessor();
        boolean isPropertyEmpty = cbProperty.getSelectionModel().isEmpty();
        if (checkTxtPadding() && checkGenderPadding() && !isPropertyEmpty) {
            lessor.setName(txtName.getText().trim());
            lessor.setCpf(txtCpf.getText().trim());
            lessor.setRg(txtRg.getText().trim());
            lessor.setTelOne(txtTel1.getText().trim());
            lessor.setTelTwo((txtTel2.getText().trim()));
            lessor.setGender(cbGender.getValue());

            try {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                Date entranceDate = formato.parse(datePEntrance.getEditor().getText());
                Date exitDate = formato.parse(datePExit.getEditor().getText());
                Date readjustmentDate = formato.parse(datePReadjustment.getEditor().getText());
                Date bornDate = formato.parse(datePBorn.getEditor().getText());

                lessor.setBornDate(bornDate);

                String txtRentValue_replacement = txtRentValue.getText().trim().replaceAll(",", ".");
                rent.setValue(Float.parseFloat(txtRentValue_replacement));
                rent.setEntranceDate(entranceDate);
                rent.setExitDate(exitDate);
                rent.setReadjustmentDate(readjustmentDate);
                rent.setPayDay(spPayDay.getValue());
                lessor.setRent(rent);
                rent.setLessor(lessor);

                RentDAO rentDAO = new RentDAO();

                Property property = cbProperty.getSelectionModel().getSelectedItem();
                PropertyDAO propertyDAO = new PropertyDAO();
                property.setRent(rent);
                rent.setProperty(property);
                rentDAO.save(rent);
                propertyDAO.update(property);

                saveSucess();
                Stage stage = (Stage) btnSave.getScene().getWindow();
                stage.close();

            } catch (ParseException e) {
                alertDateInvalid();
                e.printStackTrace();
            }
        } else if (isPropertyEmpty) {
            alertPropertyNotSelected();
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

    private void setCbPropertyNotRented() {
        try {
            PropertyDAO propertyDAO = new PropertyDAO();
            ObservableList<Property> freeProperties = FXCollections.observableArrayList(propertyDAO.getPropertyNotRented());
            if (!freeProperties.isEmpty()) {
                cbProperty.setItems(freeProperties);
            }
        } catch (ExceptionInInitializerError e) {
            System.out.println(e.getException());
        }
    }

    public static EventHandler<KeyEvent> getPatternValidation(String pattern) {
        return e -> {
            String typed = e.getCharacter();
            if (!typed.matches(pattern)) {
                e.consume();
            }
        };
    }

    private void alertPadding() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Há campos em branco", ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro ao preencher");
        alert.show();
    }

    private void alertDateInvalid() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Data inválida",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro de data");
        alert.show();
    }

    private void alertPropertyNotSelected() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Nenhuma propriedade selecionada",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro: Propriedade vazia");
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

