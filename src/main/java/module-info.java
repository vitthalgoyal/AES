module com.example.aes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aes to javafx.fxml;
    exports com.example.aes;
    exports com.example.aes.controllers;
    opens com.example.aes.controllers to javafx.fxml;
}