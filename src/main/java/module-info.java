module com.example.onlineshopdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.onlineshopdemo to javafx.fxml;
    exports com.example.onlineshopdemo;
}