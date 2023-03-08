package com.example.dyplom5_0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Controller {

    @FXML
    private Button BConnectionDB;

    @FXML
    private PasswordField passwordUser;

    @FXML
    private TextField txtUserName;


    @FXML
    void connectionToDb(ActionEvent event) {                                       // CONNECTION TO DB
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT * FROM graduate";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet resultset = statement.executeQuery(connectQuery);

            while (resultset.next()){
                if(txtUserName.getText().equals(resultset.getString("name"))
                        && passwordUser.getText().equals(resultset.getString("password"))){
                    FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("appPage.fxml"));                       // NEXT PAGE
                    AppController appController = new AppController(resultset.getInt("PESEL"));
                    Stage window = (Stage) BConnectionDB.getScene().getWindow();
                    window.setScene(new Scene(fxmlLoader.load()));

                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    void bwzChangeB(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("loginBWZPage.fxml"));
        Stage window = (Stage) BConnectionDB.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    void BCancel(ActionEvent event) {
        passwordUser.clear();
        txtUserName.clear();
    }



    // -------------------- BWZ ---------------------


    @FXML
    private PasswordField bwzPassword;

    @FXML
    private TextField bwzUserName;
    @FXML
    private Button BwzBConnectionDB;

    @FXML
    void BwzBCancel(ActionEvent event) {
        bwzPassword.clear();
        bwzUserName.clear();
    }
    @FXML
    void bChangeToGraduate(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("loginPage.fxml"));
        Stage window = (Stage) BwzBConnectionDB.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }
    @FXML
    void bwzConnectionDB(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT * FROM bwz";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet resultset = statement.executeQuery(connectQuery);

            while (resultset.next()){
                if(bwzUserName.getText().equals(resultset.getString("name"))
                        && bwzPassword.getText().equals(resultset.getString("password"))){
                    FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("appBWZPage.fxml"));                       // NEXT PAGE
                    Stage window = (Stage) BwzBConnectionDB.getScene().getWindow();
                    window.setScene(new Scene(fxmlLoader.load()));
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
