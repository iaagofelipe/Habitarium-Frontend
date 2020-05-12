package com.habitarium.utils;

import javafx.fxml.FXMLLoader;
import java.io.IOException;

public interface OpenScreens {
    public void loadScreen(String screen, String title, Object object) throws IOException;
}
