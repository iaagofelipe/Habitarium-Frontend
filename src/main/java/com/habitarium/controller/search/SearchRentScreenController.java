package com.habitarium.controller.search;

import com.habitarium.utils.screen.OpenEditRentScreen;
import com.habitarium.utils.screen.OpenScreens;
import com.habitarium.utils.screen.Reloadable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.java.dao.RentDAO;
import main.java.entity.Rent;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SearchRentScreenController implements Initializable, Reloadable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private ListView<Rent> listViewPane;

    private ObservableList<Rent> rentObservableList;
    private ObservableList<Rent> searchResult;
    private OpenScreens openEditRentScreens;
//    public boolean isEditopen;

    private final RentDAO rentDAO = new RentDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openEditRentScreens = new OpenEditRentScreen();
        openEditRentScreens.setReload(this);
        setListViewPane();
        searchRent();
    }

    public List<Rent> searchListRent(String searchStr){
        List<Rent> items = listViewPane.getItems();
        List<BoundExtractedResult<Rent>> result = FuzzySearch.extractSorted(searchStr, items,
                Rent::toString, 57);
        return result.stream().map(BoundExtractedResult::getReferent).collect(Collectors.toList());
    }

    private void searchRent() {
        List<Rent> items = listViewPane.getItems();
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            List<BoundExtractedResult<Rent>> result = FuzzySearch.extractSorted(newValue, items,
                    Rent::toString, 57);

            searchResult = FXCollections.observableList(result.stream()
                    .map(BoundExtractedResult::getReferent).collect(Collectors.toList()));
            listViewPane.setItems(searchResult);
            System.out.println(result);
            if (tfSearch.getText().length() == 0) {
                listViewPane.setItems(rentObservableList);
            }
        });
    }

    public void setListViewPane() {
//        isEditopen = false;
        List<Rent> rentList = rentDAO.getList();
        if (!rentList.isEmpty()) {
            rentObservableList = FXCollections.observableList(rentList.stream()
                    .filter(r -> r.getLessor() != null && r.getProperty() != null)
                    .collect(Collectors.toList()));
            listViewPane.setItems(rentObservableList);
        }
    }

    @FXML
    private void eventOpenEditRents() {
        if(listViewPane.getSelectionModel().getSelectedIndex() != -1){
//            Platform.runLater(() -> isEditopen = true);
            Rent selectedItemRent = listViewPane.getSelectionModel().getSelectedItem();
            try {
                openEditRentScreens.loadScreen("screen/edit/editRent", "Editor de Aluguéis", selectedItemRent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onActionBtnSearch() {
        rentObservableList = FXCollections.observableList(searchListRent(tfSearch.getText()));
        listViewPane.setItems(rentObservableList);
    }

    @Override
    public void reload() {
        System.out.println("reloading...");
        ObservableList<Rent> temp = FXCollections.observableList(rentObservableList);
        listViewPane.setItems(temp);
    }
}
