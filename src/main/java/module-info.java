module Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires junit;


    opens Application to javafx.fxml;
    exports Application;
    exports Testing;
    exports Domain;
    exports Foundation;
    opens Foundation to javafx.fxml;
}