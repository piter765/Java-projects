module com.example._3javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example._3javafx to javafx.fxml;
    exports com.example._3javafx;
}