package com.habitarium.controller.screen;

import com.habitarium.utils.screen.OpenScreens;
import com.habitarium.utils.screen.OpenSearchPropertyScreen;
import com.habitarium.utils.screen.OpenSearchRentScreen;
import com.habitarium.utils.screen.ScreenUtils;
import javafx.collections.FXCollections;
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

    private OpenScreens openPropertyScreens;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RentDAO rentDAO = new RentDAO();
        MonthPaidController monthPaidController = new MonthPaidController();
        List<Rent> rents = rentDAO.getList();
        for (Rent rent : rents) {
            lvDebtors.setItems(
                    FXCollections.observableList(
                            monthPaidController.lateMonthsInRange(rent.getMonthPaidList(), rent.getEntranceDate(),
                                    new Date())));
        }
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
        openPropertyScreens = new OpenSearchPropertyScreen();
        try {
            openPropertyScreens.loadScreen("screen/search/searchProperty", "Procura de Propriedades", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchRent() {
        openPropertyScreens = new OpenSearchRentScreen();
        try {
            openPropertyScreens.loadScreen("screen/search/searchRent", "Procura de Aluguéis", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
