package com.example.javafxusuarios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.javafxusuarios.Utilities.insertQuery;
import static com.example.javafxusuarios.Utilities.showAlert;

public class InputController implements Initializable {
    @FXML
    PasswordField pass1;
    @FXML
    PasswordField pass2;
    @FXML
    TextField name;
    public void onButtonSaveClick(ActionEvent actionEvent) throws IOException {
        if(pass1.getText().isEmpty() || pass2.getText().isEmpty() || name.getText().isEmpty() ){
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please enter all fields");
            return;
        }
        if(pass1.getText().equals(pass2.getText())){
            String n = name.getText();
            String p=pass1.getText();
            insertQuery(n, p);
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("usersView.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Users");
            stage.setScene(new Scene(root));
            stage.show();
        }else{
            showAlert(Alert.AlertType.ERROR, "Error", "Password doesn't match");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
