package com.habitarium.controller;

import com.habitarium.utils.ScreenUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainScreenController {

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
    public void registerProperty() {
        try {
            ScreenUtils.switchScreen("registerPropertyScreen", "Registro de Propriedade");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registerRent() {
        try {
            ScreenUtils.switchScreen("registerRentScreen", "Registro de Aluguel");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchProperty() {
    }

    public void searchRent() {
    }

    public void searchLessor() {
    }
}
