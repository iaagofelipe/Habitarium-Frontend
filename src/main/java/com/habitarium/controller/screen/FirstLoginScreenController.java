package com.habitarium.controller.screen;

import com.habitarium.utils.screen.AlertScreens;
import com.habitarium.utils.screen.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
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
        if (!check_txtPadding()) {
            AlertScreens.alertError("Há campos em branco",
                    "Erro ao preencher");
        } else if (!lenghtVerification()) {
            AlertScreens.alertError("Mínimo de 5 caractere para a senha",
                    "Erro ao preencher");
        }
        else if (pfPassword.getText().equals(pfPasswordConfirm.getText())) {
            user.setEmail(tfEmail.getText());
            user.setLogin(tfUser.getText());
            user.setPassword(pfPassword.getText());
            User update = userDAO.update(user);
            if (update == null) {
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
            final String cssDefault = "-fx-border-color: #fc2424;-fx-border-width: 4;";
            final Tooltip tooltip = new Tooltip();
            tooltip.setText("As senhas precisam ser iguais");
            pfPasswordConfirm.setStyle(cssDefault);
            pfPasswordConfirm.setTooltip(tooltip);
        }

    }

    private boolean check_txtPadding() {
        boolean checkFields = !tfEmail.getText().trim().equals("") && !tfUser.getText().trim().equals("")
                && !pfPassword.getText().trim().equals("") && !pfPasswordConfirm.getText().trim().equals("");

        return checkFields;
    }

    private boolean lenghtVerification() {
        return pfPassword.getLength() >= 5 && tfUser.getLength() >= 5;
    }

    private void closeScreen() {
        Stage stageLogin = (Stage) btnSubmit.getScene().getWindow();
        stageLogin.close();
    }
}
