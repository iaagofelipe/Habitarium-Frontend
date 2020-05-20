import javafx.fxml.Initializable;

module com.habitarium {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires Habitarium.Gitflow.Maven;
    requires java.desktop;
    requires fuzzywuzzy;

    uses Initializable;

    opens com.habitarium to javafx.fxml;
    opens com.habitarium.controller.search to javafx.fxml;
    opens com.habitarium.controller.register to javafx.fxml;
    opens com.habitarium.controller.edit to javafx.fxml;
    opens com.habitarium.controller.screen to javafx.fxml;
    exports com.habitarium;
}