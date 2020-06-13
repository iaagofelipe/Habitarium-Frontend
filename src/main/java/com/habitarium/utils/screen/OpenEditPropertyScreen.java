package com.habitarium.utils.screen;

import com.habitarium.App;
import com.habitarium.controller.edit.EditPropertyController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.entity.Property;

import java.io.IOException;
import java.net.URL;

public class OpenEditPropertyScreen implements OpenScreens {
    private OpenScreens openPropertyScreens;
    @Override
    public void loadScreen(String screen, String title, Object object) throws IOException {
        Property property = (Property) object;
        FXMLLoader fxmlLoader;
        URL url = App.class.getResource(screen + ".fxml");
        if (url == null) {
            throw new IOException("File \"" + screen + ".fxml\" doesn't exists.");
        } else {
            fxmlLoader = new FXMLLoader(url);
            Parent root = fxmlLoader.load();
            EditPropertyController editPropertyController = fxmlLoader.getController();
            editPropertyController.initializeScreen(property);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    @Override
    public void setReload(Reloadable reloadable) {
        return;
    }
}
