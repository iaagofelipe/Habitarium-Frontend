package org.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainScreenController {

    @FXML
    private Button registerPropertyBtn;

    @FXML
    private Button registerRentBtn;

    @FXML
    public void registerProperty() {
        registerPropertyBtn.setOnAction(event -> {
            Parent root;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainScreenController.class.getResource(
                        "registerPropertyScreen.fxml"));
                root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Registro de Propriedade");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();
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
                FXMLLoader fxmlLoader = new FXMLLoader(MainScreenController.class.getResource(
                        "registerRentScreen.fxml"));
                root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Registro de Aluguel");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
