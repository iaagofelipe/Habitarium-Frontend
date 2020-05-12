package com.habitarium.controller;

import com.habitarium.utils.ScreenUtils;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.java.dao.PropertyDAO;
import main.java.entity.Property;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class searchPropertyScreenController implements Initializable {
    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private ListView<Property> listViewPane;

    private ObservableList<Property> propertyObservableList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListViewPane();
    }

    private void setListViewPane() {
        PropertyDAO propertyDAO = new PropertyDAO();
        List<Property> propertyList = propertyDAO.getList();
        if (!propertyList.isEmpty()) {
            propertyObservableList = FXCollections.observableList(propertyList);
            listViewPane.setItems(propertyObservableList);
        }
    }

    @FXML
    private void eventOpenEditProperties() {
        Property selectedItem = listViewPane.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/com/habitarium/editProperty.fxml"));
            Parent root = loader.load();

            EditPropertyController editPropertyController = loader.getController();
            editPropertyController.initializeScreen(selectedItem);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            ScreenUtils.switchScreen("editProperty", "Editar Propriedade");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
