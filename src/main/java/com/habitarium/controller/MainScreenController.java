package com.habitarium.controller;

import com.habitarium.utils.ScreenUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private Button registerPropertyBtn;
    @FXML
    private Button registerRentBtn;
    @FXML
    private Button searchRentBtn;
    @FXML
    private Button searchLessorBtn;
    @FXML
    private Button searcPropertyBtn;
    @FXML
    private TableView<DebtorsTableView> tabViewDebtors;
    @FXML
    private TableColumn<DebtorsTableView, String> tabColName;
    @FXML
    private TableColumn<DebtorsTableView, Number> tabColDate;
    private ObservableList<DebtorsTableView> debtorsTableViewObservableList = FXCollections.observableArrayList();

    @FXML
    public void registerProperty() {
        try {
            ScreenUtils.switchScreen("registerPropertyScreen", "Registro de Propriedade");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registerRent() {
        try {
            ScreenUtils.switchScreen("registerRentScreen", "Registro de Aluguel");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchProperty() {
    }

    public void searchRent() {
    }

    public void searchLessor() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
    }

    public ObservableList<DebtorsTableView> getPersonData() {
        return debtorsTableViewObservableList;
    }

    public void setTableView() {
        tabColName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tabColDate.setCellValueFactory(cellData -> cellData.getValue().dayProperty());
    }

}
