package org.example;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField txtFieldUsuario;

    @FXML
    public Button login;

    @FXML
    public Label warning;

    @FXML
    public void switchToMain() {
        String user = "iago";
        String pass = "12345";
        if (txtFieldUsuario.getText().equals(user) && passwordField.getText().equals(pass)) {
            try {
                App.setRoot("secondary");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            warning.setText("Usuário ou senha incorreto.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTextFields();
    }

    public void setTextFields() {
        passwordField.setFocusTraversable(false);
        txtFieldUsuario.requestFocus();

        txtFieldUsuario.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    passwordField.requestFocus();
                }
            }
        });

        passwordField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (txtFieldUsuario.getText().equals("")) {
                        txtFieldUsuario.requestFocus();
                    } else {
                        switchToMain();
                    }
                }
            }
        });
    }
}

