package org.example;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

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
    public Label warning;

    @FXML
    public void switchToMain() {
        String user = "iago";
        String pass = "12345";
        if (txtFieldUsuario.getText().equals(user) && passwordField.getText().equals(pass)) {
            Parent root;
            try {
                Stage stageLogin = (Stage) login.getScene().getWindow();
                stageLogin.close();
                FXMLLoader fxmlLoader = new FXMLLoader(MainScreenController.class.getResource(
                        "mainScreen.fxml"));
                root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Registro de Propriedade");
                stage.setScene(new Scene(root));
                stage.show();
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Usuário ou senha inválida",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro ao logar");
        alert.show();
    }
}

