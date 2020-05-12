package com.habitarium.controller;

import com.habitarium.utils.ScreenUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
            FXMLLoader loader = ScreenUtils.loaderObject("editProperty");
            Parent root = loader.load();
            EditPropertyController editPropertyController = loader.getController();
            editPropertyController.initializeScreen(selectedItem);
            Stage stage = new Stage();
            stage.setTitle("Visualizar Propriedade");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
