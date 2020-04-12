package org.example;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.dao.LessorDAO;
import main.java.dao.PropertyDAO;
import main.java.entity.Lessor;
import main.java.entity.Property;

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
            alertLogin();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyDAO propertyDAO = new PropertyDAO();
        Property property = propertyDAO.findById(1L);
        System.out.println(property.getNeighbour());
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

    public void alertLogin(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Usuário ou senha inválida",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro ao logar");
        alert.show();
    }
}

