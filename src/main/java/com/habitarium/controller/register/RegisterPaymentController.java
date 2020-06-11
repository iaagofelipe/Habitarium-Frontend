package com.habitarium.controller.register;

import com.habitarium.utils.screen.AlertScreens;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.java.controller.MonthPaidController;
import main.java.dao.MonthPaidDAO;
import main.java.entity.MonthPaid;
import main.java.entity.Rent;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class RegisterPaymentController implements Initializable {

    @FXML
    private ChoiceBox<MonthPaid> cbSelectMonth;

    @FXML
    private ComboBox<MonthPaid> cbOwedMonths;

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
        cbOwedMonths.setItems(FXCollections.observableList(
                monthPaidController.lateMonthsInRange(rent.getMonthPaidList(), rent.getEntranceDate(), new Date())));
    }

    @FXML
    private void pay() {
        MonthPaidDAO monthPaidDAO = new MonthPaidDAO();
        MonthPaid selectedMonthPaid;

        if (!isSelected()) {
            AlertScreens.alertError("Nenhuma data selecionada!",  "Erro");
            return;
        }

        if (!rbAnticipatePayment.isSelected()) {
            selectedMonthPaid = cbOwedMonths.getSelectionModel().getSelectedItem();
        } else {
            selectedMonthPaid = cbSelectMonth.getSelectionModel().getSelectedItem();
        }
        selectedMonthPaid.setValue(Float.parseFloat(tfNewValue.getText().trim()
                .replaceAll(",", ".")));
        selectedMonthPaid.setPaid(true);

        monthPaidDAO.update(selectedMonthPaid);
        AlertScreens.alertConfirmation("", "Pagamento realizado com sucesso!");

        Stage stage = (Stage) btnConfirmPayment.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void enableAnticipatePayment() {
        cbSelectMonth.setDisable(!rbAnticipatePayment.isSelected());
        cbSelectMonth.setItems(FXCollections.observableList(
                monthPaidController.lateMonthsInRange(rent.getMonthPaidList(), new Date(), rent.getExitDate())));
        cbSelectMonth.getSelectionModel().selectFirst();

        cbOwedMonths.setDisable(rbAnticipatePayment.isSelected());
        tfNewValue.setText(String.valueOf(rent.getValue()));
    }

    private boolean isSelected() {
        return cbOwedMonths.getSelectionModel().getSelectedIndex() != -1 ||
                cbSelectMonth.getSelectionModel().getSelectedIndex() != -1;
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
