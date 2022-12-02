package com.example.javafxusuarios;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class Utilities {
    private static String databaseUrl ="jdbc:mysql://localhost/liceo";
    private static Connection conexion;
    public static void conexionSql(String user, String password) {
        try {
            //LoginController.showAlert(Alert.AlertType.INFORMATION, "inf", user+ " " + password);
            conexion= DriverManager.getConnection(databaseUrl, user, password);
        }  catch (SQLException e) {
            //e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Form Error!",
                    "Database connection is not avaliable con");
        }


    }
    public static ArrayList<Usuario> consulta(){
        ArrayList<Usuario>users=new ArrayList<Usuario>();
        Statement stmt = null;
        try {
            stmt = conexion.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM alumno");
        while (rs.next()) {
            Usuario user = new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3));
            users.add(user);

            //System.out.println(user.getPassword().getBytes());

        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static void saveQuery(String oldName, String newName){
        Statement stmt = null;
        String query ="UPDATE alumno " +
                "SET nombre = '"+ newName +
                "' WHERE nombre LIKE '"+oldName+"'";
        try {
            stmt = conexion.createStatement();

            int filas= stmt.executeUpdate(query);

            } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static void deleteQuery(String name){
        Statement stmt = null;
        String query = "DELETE FROM alumno " +
                "WHERE nombre LIKE '" + name +"'";
        try {
            stmt = conexion.createStatement();

            stmt.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertQuery(String name, String password){
        Statement stmt = null;
        String query="INSERT INTO alumno(nombre, password)" +
                "VALUES('"+name+"','"+password+"')";
        try {
            stmt = conexion.createStatement();

            stmt.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
