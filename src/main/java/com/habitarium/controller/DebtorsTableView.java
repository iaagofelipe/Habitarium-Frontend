package com.habitarium.controller;

import javafx.beans.property.*;

public class DebtorsTableView {

    private final StringProperty name;
    private final IntegerProperty day;
    private final LongProperty idRent;

    public DebtorsTableView(String name, int day, Long idRent) {
        this.name = new SimpleStringProperty(name);
        this.day = new SimpleIntegerProperty(day);
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

    public int getDay() {
        return day.get();
    }

    public IntegerProperty dayProperty() {
        return day;
    }

    public void setDay(int day) {
        this.day.set(day);
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
