package com.habitarium.controller.register;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class RegisterPaymentController {

    @FXML
    private ChoiceBox<?> cbSelectMonth;

    @FXML
    private ComboBox<?> cbbOwedMonths;

    @FXML
    private TextField tfDiferentValue;

    @FXML
    private Button btnConfirmPayment;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblShowTotal;

    @FXML
    private RadioButton rbAnticipatePayment;

}
