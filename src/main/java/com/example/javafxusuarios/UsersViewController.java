package com.example.javafxusuarios;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;

import java.util.ResourceBundle;

import static com.example.javafxusuarios.Utilities.deleteQuery;
import static com.example.javafxusuarios.Utilities.saveQuery;

public class UsersViewController implements Initializable{

    @FXML
    TableView<Usuario> userView;
    @FXML
    TableColumn<Usuario, Integer> id;
    @FXML
    TableColumn<Usuario, String> name;
    @FXML
    TableColumn<Usuario, String> password;
    @FXML
    TextField filterText;
    @FXML
    TextField oldName;

    private ObservableList<Usuario> users=FXCollections.observableArrayList(Utilities.consulta());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.prefWidthProperty().bind(userView.widthProperty().divide(4)); // w * 1/6
        name.prefWidthProperty().bind(userView.widthProperty().divide(2.6666)); // w * 5/12
        password.prefWidthProperty().bind(userView.widthProperty().divide(2.66667)); // w * 5/12

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        userView.setItems(users);


        filtradoDeValores();
    }

    private void filtradoDeValores() {
        FilteredList<Usuario> filter= new FilteredList<>(users, b -> true);

        filterText.textProperty().addListener((observable, oldValue, newValue)->{
            filter.setPredicate(usuario -> {
                if(newValue==null || newValue.isEmpty()){
                    return true;
                }
                if(usuario.getName().indexOf(newValue)!=-1){
                    return true;
                }
                if(Integer.toString(usuario.getId()).indexOf(newValue)!=-1){
                    return true;
                }
                return false;
            });
        });

        SortedList<Usuario>sortedData = new SortedList<>(filter);
        sortedData.comparatorProperty().bind(userView.comparatorProperty());
        userView.setItems(sortedData);
    }

    public void displaySelected(MouseEvent mouseEvent) {
        Usuario user= userView.getSelectionModel().getSelectedItem();
        oldName.setText(user.getName());
    }


    public void onButtonSaveClick(ActionEvent actionEvent) {
        saveQuery(oldName.getText(), filterText.getText());
        users=FXCollections.observableArrayList(Utilities.consulta());
        userView.setItems(users);
        filtradoDeValores();
        oldName.clear();
        filterText.clear();
    }

    public void onButtonDeleteClick(ActionEvent actionEvent) {
        deleteQuery(oldName.getText());
    }
}
