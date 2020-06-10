package com.habitarium.controller.edit;

import com.habitarium.utils.date.DateUtil;
import com.habitarium.utils.screen.AlertScreens;
import com.habitarium.utils.screen.OpenRegisterPaymentScreen;
import com.habitarium.utils.screen.OpenScreens;
import javafx.collections.FXCollections;
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
import main.java.entity.MonthPaid;
import main.java.entity.Property;
import main.java.entity.Rent;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EditRentController implements Initializable {
    private final RentDAO rentDAO = new RentDAO();
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfCpf;
    @FXML
    private TextField tfTel1;
    @FXML
    private TextField tfTel2;
    @FXML
    private ListView<MonthPaid> lvMonthPaid;
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
    private Spinner<Integer> spPayDay;
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
    private List<MonthPaid> monthsPaid;
    private final String PATTERN_MATCHES_RENT_VALUE = "[0-9,]";
    private final int RENT_VALUE_LENGTH = 10;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTfValueFilter();
    }

    public void initializeScreen(Rent rent) {
        this.rent = rent;
        this.lessor = rent.getLessor();
        monthsPaid = rent.getMonthPaidList();

        tfName.setText(lessor.getName());
        tfCpf.setText(lessor.getCpf());
        tfRg.setText(lessor.getRg());
        tfTel1.setText(lessor.getTelOne());
        tfTel2.setText(lessor.getTelTwo());

        tfProperty.setText(rent.getProperty().toString());
        tfValue.setText(String.valueOf(rent.getValue()));

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.
                IntegerSpinnerValueFactory(1, DateUtil.lastDayCurrentMonth(), rent.getPayDay());
        spPayDay.setValueFactory(valueFactory);
        spPayDay.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                spPayDay.getValueFactory().setValue(oldValue);
            }
        });

        dpEntranceDate.valueProperty().setValue(Instant.ofEpochMilli(rent.getEntranceDate().getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate());
        dpExitDate.valueProperty().setValue(Instant.ofEpochMilli(rent.getExitDate().getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate());
        dpReadjustment.valueProperty().setValue(Instant.ofEpochMilli(rent.getReadjustmentDate().getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate());

        lvMonthPaid.setItems(FXCollections.observableList(monthsPaid.stream()
                .filter(MonthPaid::isPaid)
                .collect(Collectors.toList())));
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
            rent.setValue(Float.parseFloat(tfValue.getText().trim()));
            rent.setPayDay(spPayDay.getValue());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                rent.setEntranceDate(format.parse(dpEntranceDate.getEditor().getText().trim()));
                rent.setExitDate(format.parse(dpExitDate.getEditor().getText().trim()));
                rent.setReadjustmentDate(format.parse(dpReadjustment.getEditor().getText().trim()));
            } catch (ParseException e) {
                AlertScreens.alertError("Data inválida", "Erro de data");
                e.printStackTrace();
            }
            rentDAO.update(rent);
            AlertScreens.alertConfirmation("", "Aluguel Atualizado!");

            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        } else {
            AlertScreens.alertError("Há campos em branco", "Erro ao preencher");
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
        AlertScreens.alertConfirmation("", "Aluguel Deletado!");
        Stage stage = (Stage) btnDelete.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void registerPayment() {
        OpenScreens openScreens = new OpenRegisterPaymentScreen();
        try {
            openScreens.loadScreen("screen/register/registerPayment", "Registrar Pagamento", rent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkTxtPadding() {
        boolean registerLessor = !tfName.getText().trim().equals("") && !tfCpf.getText().trim().equals("")
                && !tfRg.getText().trim().equals("") && !tfTel1.getText().trim().equals("")
                && !tfTel2.getText().trim().equals("");
        boolean hasSpinnerValue = spPayDay.getValue() != null;

        return registerLessor && hasSpinnerValue;
    }

    private void setTfValueFilter() {
        tfValue.addEventFilter(KeyEvent.KEY_TYPED, getPatternValidation(PATTERN_MATCHES_RENT_VALUE));
        tfValue.textProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue.length() > RENT_VALUE_LENGTH) {
                tfValue.setText(oldValue);
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
