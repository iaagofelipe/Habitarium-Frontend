package com.habitarium.controller.screen;


import com.habitarium.utils.screen.AlertScreens;
import com.habitarium.utils.screen.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import main.java.dao.UserDAO;
import main.java.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField txtFieldUsuario;
    @FXML
    public Button login;
    @FXML
    private Label tfForgotMyPass;

    @FXML
    public void switchToMain() {
        UserDAO UserDAO = new UserDAO();
        User user = null;
        user = UserDAO.findByLogin("admin");

        if (user != null) {
            if (user.getLogin().equals("admin") && user.getPassword().equals("admin")) {
                // TODO chamar aqui a tela de redefinicao de login
            }
        } else {
            user = UserDAO.findByLogin(txtFieldUsuario.getText());
            if (user != null) {
                if (passwordField.getText().equals(user.getPassword())) {
                    try {
                        Stage stageLogin = (Stage) login.getScene().getWindow();
                        stageLogin.close();
                        ScreenUtils.switchScreen("screen/mainScreen", "Registro de Propriedade");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    AlertScreens.alertError("Usuário ou senha inválido", "Erro na tentativa de login");
                }
            }
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
}

