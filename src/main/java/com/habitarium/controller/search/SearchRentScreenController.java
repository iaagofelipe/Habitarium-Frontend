package com.habitarium.controller.search;

import com.habitarium.utils.screen.OpenEditRentScreen;
import com.habitarium.utils.screen.OpenScreens;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.java.dao.RentDAO;
import main.java.entity.Rent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchRentScreenController implements Initializable {
    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private ListView<Rent> listViewPane;
    private ObservableList<Rent> rentObservableList;
    private OpenScreens openScreens;

    RentDAO rentDAO = new RentDAO();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListViewPane();
        openScreens = new OpenEditRentScreen();
    }

    public List<Rent> searchListRent(String searchStr){
        List<Rent> rents = rentDAO.getList();
        List<Rent> rentsReturn = new ArrayList<>();
        for (Rent rent : rents){
            String propertyToLower = rent.toString().toLowerCase();
            if(propertyToLower.contains(searchStr.toLowerCase())){
                rentsReturn.add(rent);
            }
        }
        return rentsReturn;
    }

    private void setListViewPane() {
        List<Rent> rentList = rentDAO.getList();
        if (!rentList.isEmpty()) {
            rentObservableList = FXCollections.observableList(rentList);
            listViewPane.setItems(rentObservableList);
        }
    }

    @FXML
    private void eventOpenEditRents() {
        Rent selectedItemRent = listViewPane.getSelectionModel().getSelectedItem();
        try {
            openScreens.loadScreen("screen/edit/editRent", "Editor de Aluguéis", selectedItemRent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onActionBtnSearch() {
        rentObservableList = FXCollections.observableList(searchListRent(tfSearch.getText()));
        listViewPane.setItems(rentObservableList);
    }
}
