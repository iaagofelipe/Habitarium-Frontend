package com.habitarium.controller.search;

import com.habitarium.utils.screen.OpenEditPropertyScreen;
import com.habitarium.utils.screen.OpenScreens;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.java.dao.PropertyDAO;
import main.java.entity.Property;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SearchPropertyScreenController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private ListView<Property> listViewPane;
    private ObservableList<Property> propertyObservableList;
    private ObservableList<Property> searchResult;
    private OpenScreens openScreens;
    private boolean isEditScreenWasCalled = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListViewPane();
        openScreens = new OpenEditPropertyScreen();
        searchProperty();
    }

    public List<Property> searchListProperty(String searchStr) {
        List<Property> items = listViewPane.getItems();
        List<BoundExtractedResult<Property>> result = FuzzySearch.extractSorted(searchStr, items,
                Property::toString, 50);
        return result.stream().map(BoundExtractedResult::getReferent).collect(Collectors.toList());
    }

    private void searchProperty() {
        List<Property> items = listViewPane.getItems();
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            List<BoundExtractedResult<Property>> result = FuzzySearch.extractSorted(newValue, items,
                    Property::toString, 50);

            searchResult = FXCollections.observableList(result.stream()
                    .map(BoundExtractedResult::getReferent).collect(Collectors.toList()));
            listViewPane.setItems(searchResult);
            System.out.println(result);
            if (tfSearch.getText().length() == 0) {
                listViewPane.setItems(propertyObservableList);
            }
        });
    }

    public void setListViewPane() {
        PropertyDAO propertyDAO = new PropertyDAO();
        List<Property> propertyList = propertyDAO.getList();
        if (!propertyList.isEmpty()) {
            propertyObservableList = FXCollections.observableList(propertyList);
            listViewPane.setItems(propertyObservableList);
        }
    }

    @FXML
    private void eventOpenEditProperties() {
        isEditScreenWasCalled = true;
        Property selectedItemProperty = listViewPane.getSelectionModel().getSelectedItem();
        try {
            openScreens.loadScreen("screen/edit/editProperty", "Editor de propriedade", selectedItemProperty);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onActionBtnSearch() {
        propertyObservableList = FXCollections.observableList(searchListProperty(tfSearch.getText()));
        listViewPane.setItems(propertyObservableList);
    }

    public boolean isEditScreenWasCalled() {
        return isEditScreenWasCalled;
    }

    public void setEditScreenWasCalled(boolean editScreenWasCalled) {
        isEditScreenWasCalled = editScreenWasCalled;
    }
}
