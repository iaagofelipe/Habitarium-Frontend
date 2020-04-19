package com.habitarium.controller;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DebtorsTableView {

    private final StringProperty name;
    private final StringProperty date;
    private final LongProperty idRent;

    public DebtorsTableView(String name, String date, Long idRent) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.idRent = new SimpleLongProperty(idRent);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public long getIdRent() {
        return idRent.get();
    }

    public LongProperty idRentProperty() {
        return idRent;
    }

    public void setIdRent(long idRent) {
        this.idRent.set(idRent);
    }
}
