module com.example.javafxusuarios {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafxusuarios to javafx.fxml;
    exports com.example.javafxusuarios;
}