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
import main.java.entity.Lessor;
import main.java.entity.MonthPaid;
import main.java.entity.Rent;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private ListView<String> lvDebtors;
    @FXML
    private Button btnInfo;

    private OpenScreens openPropertyScreens;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RentDAO rentDAO = new RentDAO();
        List<Rent> rents = rentDAO.getList();
        List<String> rentsNotPaid = new ArrayList<>();

        Calendar entranceDate = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        for (Rent rent : rents) {
            List<MonthPaid> monthsPaid = rent.getMonthPaidList();

            entranceDate.setTime(rent.getEntranceDate());
            today.setTime(new Date());
            while (entranceDate.before(today)) {
                MonthPaid mp = new MonthPaid();
                mp.setValue(rent.getValue());
                mp.setDate(entranceDate.getTime());
                if (!monthsPaid.contains(mp)) {
                    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
                    rentsNotPaid.add(rent.getLessor().getName() + "\tData: " + dt.format(entranceDate.getTime()));
                }
                entranceDate.add(Calendar.MONTH,1);
            }
        }
        lvDebtors.setItems(FXCollections.observableList(rentsNotPaid));
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
