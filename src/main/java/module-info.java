module Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;


    opens Application to javafx.fxml;
    exports Application;
}