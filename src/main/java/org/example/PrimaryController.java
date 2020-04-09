package org.example;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PrimaryController {

    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField txtFieldUsuario;

    @FXML
    public Button login;

    @FXML
    public Label warning;

    @FXML
    public void switchToMain(ActionEvent event) throws IOException {
        String user = "iago";
        String pass = "12345";
        if(txtFieldUsuario.getText().equals(user) && passwordField.getText().equals(pass)){
            App.setRoot("secondary");
        } else {
            warning.setText("Usuário ou senha incorreto.");
        }
    }

}
