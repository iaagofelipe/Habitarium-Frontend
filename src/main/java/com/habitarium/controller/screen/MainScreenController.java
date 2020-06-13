package com.habitarium.controller.screen;

import com.habitarium.utils.screen.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import main.java.controller.MonthPaidController;
import main.java.dao.RentDAO;
import main.java.entity.MonthPaid;
import main.java.entity.Rent;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private Button registerPropertyBtn;
    @FXML
    private Button registerRentBtn;
    @FXML
    private Button searchRentBtn;
    @FXML
    private Button searchPropertyBtn;
    @FXML
    private ListView<MonthPaid> lvDebtors;
    @FXML
    private Button btnInfo;

    private OpenScreens openScreen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLvDebtors();
    }

    public void setLvDebtors() {
        RentDAO rentDAO = new RentDAO();
        MonthPaidController monthPaidController = new MonthPaidController();
        ObservableList<MonthPaid> result = FXCollections.observableArrayList();
        List<Rent> rents = rentDAO.getList();
        for (Rent rent : rents) {
            result.addAll(monthPaidController.lateMonthsInRange(rent.getMonthPaidList(), rent.getEntranceDate(),
                    new Date()));
        }
        lvDebtors.setItems(result);

        lvDebtors.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MonthPaid>() {
            @Override
            public void changed(ObservableValue<? extends MonthPaid> observable, MonthPaid oldValue, MonthPaid newValue) {
                if (lvDebtors.getSelectionModel().getSelectedIndex() != -1) {
                    Rent selectedItemRent = lvDebtors.getSelectionModel().getSelectedItem().getRent();
                    try {
                        openScreen = new OpenEditRentScreen();
                        openScreen.loadScreen("screen/edit/editRent", "Editor de Aluguéis", selectedItemRent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    public void registerProperty() {
        try {
            ScreenUtils.switchScreen("screen/register/registerPropertyScreen", "Registro de Propriedade");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registerRent() {
        try {
            ScreenUtils.switchScreen("screen/register/registerRentScreen", "Registro de Aluguel");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setBtnInfo() {
        try {
            ScreenUtils.switchScreen("screen/utils/developInfo", "Informações dos Desenvolvedores");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchProperty() {
        openScreen = new OpenSearchPropertyScreen();
        try {
            openScreen.loadScreen("screen/search/searchProperty", "Procura de Propriedades", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchRent() {
        openScreen = new OpenSearchRentScreen();
        try {
            openScreen.loadScreen("screen/search/searchRent", "Procura de Aluguéis", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
