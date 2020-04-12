import javafx.fxml.Initializable;

module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires Habitarium.Gitflow.Maven;

    uses Initializable;

    opens org.example to javafx.fxml;
    exports org.example;
}