module com.habitarium {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.habitarium.back;
    requires java.desktop;
    requires fuzzywuzzy;

    opens com.habitarium to javafx.fxml;
    opens com.habitarium.controller.search to javafx.fxml;
    opens com.habitarium.controller.register to javafx.fxml;
    opens com.habitarium.controller.edit to javafx.fxml;
    opens com.habitarium.controller.screen to javafx.fxml;
    exports com.habitarium;
}