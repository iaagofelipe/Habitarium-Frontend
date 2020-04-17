package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        registerPropertyBtn.setOnAction(event -> {
            Parent root;
            try {
                ScreenUtils.switchScreen("registerPropertyScreen.fxml", "Registro de Propriedade");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void registerRent() {
        registerRentBtn.setOnAction(event -> {
            Parent root;
            try {
                ScreenUtils.switchScreen("registerRentScreen.fxml", "Registro de Aluguel");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void searchProperty() {
    }

    public void searchRent() {
    }

    public void searchLessor() {
    }
}
