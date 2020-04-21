package com.habitarium.utils;

import com.habitarium.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ScreenUtils {
    public static void switchScreen(String screen, String title) throws IOException {
        URL url = App.class.getResource(screen + ".fxml");
        if (url == null) {
            throw new IOException("File \"" + screen + ".fxml\" doesn't exists.");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
