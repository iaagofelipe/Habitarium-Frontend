package com.habitarium.controller.edit;

import com.habitarium.utils.screen.AlertScreens;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.java.dao.LessorDAO;
import main.java.dao.PropertyDAO;
import main.java.dao.RentDAO;
import main.java.entity.Lessor;
import main.java.entity.Property;
import main.java.entity.Rent;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class EditRentController implements Initializable {
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfCpf;
    @FXML
    private TextField tfTel1;
    @FXML
    private TextField tfTel2;
    @FXML
    private ListView lvMonthPaid;
    @FXML
    private TextField tfRg;
    @FXML
    private TextField tfValue;
    @FXML
    private DatePicker dpEntranceDate;
    @FXML
    private DatePicker dpReadjustment;
    @FXML
    private DatePicker dpExitDate;
    @FXML
    private TextField tfPayDay;
    @FXML
    private Button btnSave;
    @FXML
    private TextField tfProperty;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnMakePayment;

    private Rent rent;
    private Lessor lessor;
    private final RentDAO rentDAO = new RentDAO();
    private final String PATTERN_MATCHES_RENT_VALUE = "[0-9,]";
    private final int RENT_VALUE_LENGTH = 10;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTxtRentValue();
    }

    public void initializeScreen(Rent rent) {
        this.rent = rent;
        this.lessor = rent.getLessor();

        tfName.setText(lessor.getName());
        tfCpf.setText(lessor.getCpf());
        tfRg.setText(lessor.getRg());
        tfTel1.setText(lessor.getTelOne());
        tfTel2.setText(lessor.getTelTwo());

        tfProperty.setText(rent.getProperty().toString());
        tfValue.setText(String.valueOf(rent.getValue()).replace(".", ","));
        tfPayDay.setText(String.valueOf(rent.getPayDay()));

        dpEntranceDate.valueProperty().setValue(Instant.ofEpochMilli(rent.getEntranceDate().getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate());
        dpExitDate.valueProperty().setValue(Instant.ofEpochMilli(rent.getExitDate().getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate());
        dpReadjustment.valueProperty().setValue(Instant.ofEpochMilli(rent.getReadjustmentDate().getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate());
    }

    @FXML
    private void save() {
        if (checkTxtPadding()) {
            lessor.setName(tfName.getText().trim());
            lessor.setCpf(tfCpf.getText().trim());
            lessor.setRg(tfRg.getText().trim());
            lessor.setTelOne(tfTel1.getText().trim());
            lessor.setTelTwo(tfTel2.getText().trim());

            rent.setLessor(lessor);
            rent.setValue(Float.parseFloat(tfValue.getText().trim().replaceAll(",",".")));
            rent.setPayDay(Integer.parseInt(tfPayDay.getText().trim()));

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                rent.setEntranceDate(format.parse(dpEntranceDate.getEditor().getText().trim()));
                rent.setExitDate(format.parse(dpEntranceDate.getEditor().getText().trim()));
                rent.setReadjustmentDate(format.parse(dpReadjustment.getEditor().getText().trim()));
            } catch (ParseException e) {
                AlertScreens.alertDateInvalid();
                e.printStackTrace();
            }
            rentDAO.update(rent);
            saveSucess();

            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        } else {
            AlertScreens.alertPadding();
        }
    }

    @FXML
    private void delete() {
        PropertyDAO propertyDAO = new PropertyDAO();
        Property property = propertyDAO.findById(rent.getProperty().getId());
        property.setRent(null);

        LessorDAO lessorDAO = new LessorDAO();
        lessorDAO.delete(rent.getLessor().getId());

        rentDAO.delete(rent.getId());
        deleteSucess();
        Stage stage = (Stage) btnDelete.getScene().getWindow();
        stage.close();
    }

    private boolean checkTxtPadding() {
        boolean registerLessor = !tfName.getText().trim().equals("") && !tfCpf.getText().trim().equals("")
                && !tfRg.getText().trim().equals("") && !tfTel1.getText().trim().equals("")
                && !tfTel2.getText().trim().equals("");
        boolean registerRent = !tfPayDay.getText().trim().equals("");
        return registerLessor && registerRent;
    }

    private void setTxtRentValue() {
        tfValue.addEventFilter(KeyEvent.KEY_TYPED, getPatternValidation(PATTERN_MATCHES_RENT_VALUE));
        tfValue.textProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue.length() > RENT_VALUE_LENGTH) {
                tfValue.setText(oldValue);
            }
        });
    }

    private static EventHandler<KeyEvent> getPatternValidation(String pattern) {
        return e -> {
            String typed = e.getCharacter();
            if (!typed.matches(pattern)) {
                e.consume();
            }
        };
    }

    private void saveSucess() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Aluguel Atualizado!");
        alert.show();
    }

    private void deleteSucess() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Aluguel Deletado!");
        alert.show();
    }
}