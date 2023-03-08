package com.example.dyplom5_0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AppController {

    public AppController(){}
    public long userPesel = 0;
    public AppController(long userPesel){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ArrayList<String> users = new ArrayList<>();
        String connectQuery = "SELECT * FROM graduate WHERE PESEL = " + userPesel;

        try{
            Statement statement = connectDB.createStatement();
            ResultSet resultset = statement.executeQuery(connectQuery);

            while (resultset.next()){
                System.out.println(resultset.getString("name"));
                System.out.println(resultset.getString("PESEL"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        this.userPesel = userPesel;
    }

    @FXML
    private Button btnAplikowac;

    @FXML
    private Button btnBackToLogin;

    @FXML
    private Button btnDokumenty;

    @FXML
    private Button btnKonto;

    @FXML
    private Button btnRejestracje;

    @FXML
    void btnAplikowacAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("aplikowacPage.fxml"));                       // NEXT PAGE
        Stage window = (Stage) btnAplikowac.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    void btnDokumentyAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("dokumentyMain.fxml"));                       // NEXT PAGE
        Stage window = (Stage) btnDokumenty.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    void btnKontoAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("kontoMain.fxml"));                       // NEXT PAGE
        Stage window = (Stage) btnKonto.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    void btnRejestracjeAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("rejestracjeMain.fxml"));                       // NEXT PAGE
        Stage window = (Stage) btnRejestracje.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }
    @FXML
    void btnBackToLoginAction(ActionEvent event)  {


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT * FROM graduate";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet resultset = statement.executeQuery(connectQuery);

            while (resultset.next()){
                if("Anton".equals(resultset.getString("name"))){
                    FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("loginPage.fxml"));                       // NEXT PAGE
                    Stage window = (Stage) btnBackToLogin.getScene().getWindow();
                    window.setScene(new Scene(fxmlLoader.load()));
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
