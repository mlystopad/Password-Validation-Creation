module com.example.passwordvalidator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.passwordvalidator to javafx.fxml;
    exports com.example.passwordvalidator;
}