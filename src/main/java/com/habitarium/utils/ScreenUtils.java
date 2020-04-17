package com.habitarium;

import com.habitarium.controller.MainScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenUtils {
    public static void switchScreen(String screen, String title) throws IOException {
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(MainScreenController.class.getResource(
                screen));
        root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
