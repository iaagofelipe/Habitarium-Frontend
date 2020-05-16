package com.habitarium.utils.screen;

import com.habitarium.App;
import com.habitarium.controller.edit.EditPropertyController;
import com.habitarium.controller.search.searchPropertyScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.entity.Property;

import java.io.IOException;
import java.net.URL;

public class UpdateSearchPropertyScreen implements OpenScreens {
    @Override
    public void loadScreen(String screen, String title, Object object) throws IOException {
        Property property = (Property) object;
        FXMLLoader fxmlLoader;
        URL url = App.class.getResource(screen + ".fxml");
        if (url == null) {
            throw new IOException("File \"" + screen + ".fxml\" doesn't exists.");
        } else {
            fxmlLoader = new FXMLLoader(url);
            fxmlLoader.load();
            searchPropertyScreenController updateList = fxmlLoader.getController();
            System.out.println(updateList);
            updateList.updateListView(property);
        }
    }
}
