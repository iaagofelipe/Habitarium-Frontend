module org.example {
    requires javafx.controls;
    requires javafx.fxml;
//    requires java.persistence;
    requires Habitarium.Gitflow.Maven;

    opens org.example to javafx.fxml;
    exports org.example;
}