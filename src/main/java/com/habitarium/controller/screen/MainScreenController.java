package com.habitarium.controller.screen;

import com.habitarium.utils.screen.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import main.java.entity.Rent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private Button registerPropertyBtn;
    @FXML
    private Button registerRentBtn;
    @FXML
    private Button searchRentBtn;
    @FXML
    private Button searchLessorBtn;
    @FXML
    private Button searcPropertyBtn;
    @FXML
    private ListView<Rent> lvDebtors;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void searchProperty() {
        try {
            ScreenUtils.switchScreen("screen/search/searchProperty", "Procura de Propriedades");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchRent() {
        try {
            ScreenUtils.switchScreen("screen/search/searchRent", "Procura de Aluguéis");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
