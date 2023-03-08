package com.example.dyplom5_0;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AplikowacPageController implements  Initializable{
    ArrayList<String> cities;
    ArrayList<String> universitiesList;
    @FXML
    private Button ButtonGoToMainPage;
    @FXML
    private Button findButton;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtDepartment;
    @FXML
    private TableView<Universities> tablePanel;
    @FXML
    private TableColumn<Universities, Integer> tablePanelId;
    @FXML
    private TableColumn<Universities, String> tablePanelUniversity;
    @FXML
    private TableColumn<Universities, String> tablePanelCity;
    @FXML
    private TextField txtUniversity;

    @FXML
    void findButton(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<Universities> universities = FXCollections.observableArrayList();
        String cityQuery = "SELECT * FROM uniwersity";
        if(txtCity.getText().equals("") && txtUniversity.getText().equals("")){
            try{
                Statement statement = connectDB.createStatement();
                ResultSet resultset = statement.executeQuery(cityQuery);

                while (resultset.next()){
                    universities.add(new Universities(resultset.getInt("id"), resultset.getString("name"), resultset.getString("city") ));
                }

            } catch (Exception e){
                e.printStackTrace();
            }

        }
        tablePanel.setItems(universities);

    }
    @FXML
    void mainPageButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("appPage.fxml"));                       // NEXT PAGE
        Stage window = (Stage) ButtonGoToMainPage.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablePanelId.setCellValueFactory( new PropertyValueFactory<Universities, Integer>("id"));
        tablePanelUniversity.setCellValueFactory( new PropertyValueFactory<Universities, String>("city"));
        tablePanelCity.setCellValueFactory( new PropertyValueFactory<Universities, String>("name"));

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        cities = new ArrayList<>();
        universitiesList = new ArrayList<>();

        String cityQuery = "SELECT city FROM uniwersity";
        String universityQuery = "SELECT name FROM uniwersity";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet resultset = statement.executeQuery(cityQuery);

            while (resultset.next()){
                cities.add(resultset.getString(1));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        try{
            Statement statement = connectDB.createStatement();
            ResultSet resultset = statement.executeQuery(universityQuery);

            while (resultset.next()){
                universitiesList.add(resultset.getString(1));
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }















    /*
    public void initialize() {








        // min 3 max 24
    }
   */
}
