package com.habitarium.controller.register;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import main.java.controller.MonthPaidController;
import main.java.entity.MonthPaid;
import main.java.entity.Rent;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterPaymentController implements Initializable {

    @FXML
    private ChoiceBox<MonthPaid> cbSelectMonth;

    @FXML
    private ComboBox<MonthPaid> cbbOwedMonths;

    @FXML
    private TextField tfNewValue;

    @FXML
    private Button btnConfirmPayment;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblShowTotal;

    @FXML
    private RadioButton rbAnticipatePayment;

    private Rent rent;
    private final int RENT_VALUE_LENGTH = 10;
    private final String PATTERN_MATCHES_RENT_VALUE = "[0-9,]";
    private final MonthPaidController monthPaidController = new MonthPaidController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTfNewValueFilter();
    }

    public void initializeScreen(Rent rent) {
        this.rent = rent;
        cbbOwedMonths.setItems(FXCollections.observableList(
                monthPaidController.lateMonthsInRange(rent.getMonthPaidList(), rent.getEntranceDate(), new Date())));
    }

    @FXML
    private void pay() {

    }

    @FXML
    private void enableAnticipatePayment() {
        cbSelectMonth.setDisable(!rbAnticipatePayment.isSelected());
        cbSelectMonth.setItems(FXCollections.observableList(
                monthPaidController.lateMonthsInRange(rent.getMonthPaidList(), new Date(), rent.getExitDate())));
    }


    private void setTfNewValueFilter() {
        tfNewValue.addEventFilter(KeyEvent.KEY_TYPED, getPatternValidation(PATTERN_MATCHES_RENT_VALUE));
        tfNewValue.textProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue.length() > RENT_VALUE_LENGTH) {
                tfNewValue.setText(oldValue);
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
}
