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

public class SecondaryController {

    @FXML
    private Button registerPropertyBtn;

    @FXML
    private Button rentRegister;

    @FXML
    public void registerProperty() {
        System.out.println("Aquiiiii");
        registerPropertyBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parent root;
                try {
//                    root = FXMLLoader.load(getClass().getClassLoader().getResource("primary.fxml"), );
                    FXMLLoader fxmlLoader = new FXMLLoader(SecondaryController.class.getResource("primary.fxml"));
                    root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Registro de Propriedade");
                    stage.setScene(new Scene(root, 450, 450));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
