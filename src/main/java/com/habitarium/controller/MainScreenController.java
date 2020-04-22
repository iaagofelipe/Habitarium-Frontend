package com.habitarium.controller;

import com.habitarium.utils.DateUtil;
import com.habitarium.utils.ScreenUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.controller.RentController;
import main.java.entity.Rent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    private TableColumn<DebtorsTableView, String> tabColDate;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        loadRentDebtorsInTableView();
    }

    public void setTableView() {
        tabColName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tabColDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        tabViewDebtors.setItems(getPersonData());
    }

    public ObservableList<DebtorsTableView> getPersonData() {
        return debtorsTableViewObservableList;
    }

    public void addRowtabViewDebtors(String name, String date, Long id){
        debtorsTableViewObservableList.add(new DebtorsTableView(name, date, id));
    }

    public void loadRentDebtorsInTableView(){
        List<Rent> rentList = RentController.checkIfYouPaid();
        for (Rent rent : rentList){
            String date = DateUtil.datePaid(rent.getAmountPaidMonth(), rent.getEntranceDate(), rent.getPayDay());
            addRowtabViewDebtors(rent.getLessor().getName(), date, rent.getId());
        }
    }

    public void searchProperty() {
    }

    public void searchRent() {
    }

    public void searchLessor() {
    }

}