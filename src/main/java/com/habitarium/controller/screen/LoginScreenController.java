package com.habitarium.controller.screen;


import com.habitarium.utils.screen.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    @FXML
    private Label tfForgotMyPass;

    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField txtFieldUsuario;

    @FXML
    public Button login;


    @FXML
    public void switchToMain() {
        String user = "iago";
        String pass = "12345";
        if (txtFieldUsuario.getText().equals(user) && passwordField.getText().equals(pass)) {
            Parent root;
            try {
                Stage stageLogin = (Stage) login.getScene().getWindow();
                stageLogin.close();
                ScreenUtils.switchScreen("screen/mainScreen", "Registro de Propriedade");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alertLogin();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTextFields();
    }

    public void setTextFields() {
        passwordField.setFocusTraversable(false);
        txtFieldUsuario.requestFocus();

        txtFieldUsuario.setOnKeyReleased(keyEvent -> {
            KeyCode code = keyEvent.getCode();
            if (code == KeyCode.ENTER || code == KeyCode.TAB) {
                passwordField.requestFocus();
            }
        });

        passwordField.setOnKeyReleased(keyEvent -> {
            KeyCode code = keyEvent.getCode();
            if (code == KeyCode.ENTER || code == KeyCode.TAB) {
                if (txtFieldUsuario.getText().equals("")) {
                    txtFieldUsuario.requestFocus();
                } else {
                    switchToMain();
                }
            }
        });
    }

    public void alertLogin(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Usuário ou senha inválido",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro na tentativa de login");
        alert.show();

    }
}

