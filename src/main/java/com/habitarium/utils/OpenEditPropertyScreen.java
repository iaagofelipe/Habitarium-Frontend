package com.habitarium.utils;

import com.habitarium.App;
import com.habitarium.controller.edit.EditPropertyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.entity.Property;

import java.io.IOException;
import java.net.URL;

public class OpenEditPropertyScreen implements OpenScreens{
    @Override
    public void loadScreen(String screen, String title, Object object) throws IOException {
        Property property = (Property) object;
        FXMLLoader fxmlLoader;
        URL url = App.class.getResource(screen + ".fxml");
        if (url == null) {
            throw new IOException("File \"" + screen + ".fxml\" doesn't exists.");
        } else{
            fxmlLoader = new FXMLLoader(url);
            FXMLLoader loader = fxmlLoader;
            Parent root = loader.load();
            EditPropertyController editPropertyController = loader.getController();
            editPropertyController.initializeScreen(property);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }
}
