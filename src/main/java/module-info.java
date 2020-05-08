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

    uses Initializable;

    opens com.habitarium to javafx.fxml;
    opens com.habitarium.controller to javafx.fxml;
    opens com.habitarium.utils to javafx.fxml;
    exports com.habitarium;
}