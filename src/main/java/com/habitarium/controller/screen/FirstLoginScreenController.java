package com.habitarium.controller.screen;

import com.habitarium.utils.screen.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.dao.UserDAO;
import main.java.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FirstLoginScreenController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfPasswordConf;
    @FXML
    private Button btnSubmit;

    private User user = null;
    UserDAO userDAO = new UserDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    void login() {
        if (tfPassword.getText().equals(tfPasswordConf.getText())){
            user.setEmail(tfEmail.getText());
            user.setLogin(tfUser.getText());
            user.setPassword(tfPassword.getText());
            User update = userDAO.update(user);
            if (update == null){
                alertLogin();
            } else {
                closeScreen();
                try {
                    ScreenUtils.switchScreen("screen/mainScreen", "Registro de Propriedade");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            tfPasswordConf.setText("");
            final String cssDefault = "-fx-border-color: #f8d007;-fx-border-width: 4;";
            tfPasswordConf.setStyle(cssDefault);
        }
    }

    private void closeScreen() {
        Stage stageLogin = (Stage) btnSubmit.getScene().getWindow();
        stageLogin.close();
    }

    public void alertLogin() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Login digitado já existe",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro ao alterar o login");
        alert.show();

    }

}
