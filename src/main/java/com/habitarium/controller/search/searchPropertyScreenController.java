package com.habitarium.controller.search;

import com.habitarium.utils.OpenEditPropertyScreen;
import com.habitarium.utils.OpenScreens;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private OpenScreens openScreens;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListViewPane();
        openScreens = new OpenEditPropertyScreen();
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
        Property selectedItemProperty = listViewPane.getSelectionModel().getSelectedItem();
        try {
            openScreens.loadScreen("editProperty", "Editor de propriedade", selectedItemProperty);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
