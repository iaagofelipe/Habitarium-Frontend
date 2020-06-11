package com.habitarium.controller.screen;

import com.habitarium.utils.screen.AlertScreens;
import com.habitarium.utils.screen.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfPasswordConfirm;
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
        if (pfPassword.getText().equals(pfPasswordConfirm.getText())){
            user.setEmail(tfEmail.getText());
            user.setLogin(tfUser.getText());
            user.setPassword(pfPassword.getText());
            User update = userDAO.update(user);
            if (update == null){
                AlertScreens.alertError("Login digitado já existe",
                        "Erro ao alterar o login");
            } else {
                closeScreen();
                try {
                    ScreenUtils.switchScreen("screen/mainScreen", "Registro de Propriedade");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            pfPasswordConfirm.setText("");
            final String cssDefault = "-fx-border-color: #f8d007;-fx-border-width: 4;";
            pfPassword.setStyle(cssDefault);
        }
    }

    private void closeScreen() {
        Stage stageLogin = (Stage) btnSubmit.getScene().getWindow();
        stageLogin.close();
    }
}
