package com.example.javafxusuarios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import static com.example.javafxusuarios.Utilities.showAlert;

public class LoginController {
    @FXML
    private TextField nombre;
    @FXML
    private PasswordField password;


    public void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
        String name = nombre.getText();
        String pass = password.getText();
        if (nombre.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!",
                    "Please enter your name");
            return;
        }
        if (password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!",
                    "Please enter a password");
            return;
        } else {
            Utilities.conexionSql(name, pass);
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("usersView.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Users");
            stage.setScene(new Scene(root));
            stage.show();

        }
    }



}

