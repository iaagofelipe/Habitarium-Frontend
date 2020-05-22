package com.habitarium.utils.screen;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertScreens {

    public static void alertPadding() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Há campos em branco",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro ao preencher");
        alert.show();
    }

    public static void alertDateInvalid() {
        Alert alert = new Alert(Alert.AlertType.ERROR,
                "Data inválida",
                ButtonType.OK);
        alert.setTitle("");
        alert.setHeaderText("Erro de data");
        alert.show();
    }
}
