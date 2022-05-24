module Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens Application to javafx.fxml;
    exports Application;
}