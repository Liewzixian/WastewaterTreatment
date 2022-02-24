package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.demo1.LoginController.menu;

public class addNewModel {
    Tech newTech;

    @FXML
    private Button BackButton;

    @FXML
    private TextField TStage;

    @FXML
    private TextField TModel;

    @FXML
    private TextField TCod;

    @FXML
    private TextField TBod;

    @FXML
    private TextField TTss;

    @FXML
    private TextField TAof;

    @FXML
    private TextField TEpm;

    @FXML
    protected void addButtonOnAction() {
        //connecting to database
        ConnectionDB connectNow = new ConnectionDB();
        Connection connectDB = connectNow.main();

        // making insert statement to insert new data into database
        String InsertFields = "INSERT INTO addmodel (Stage,ModelName,COD,BOD,TSS,Foot,Meter) VALUES('";
        String InsertValues = TStage.getText() + "','" + TModel.getText() + "','" + TCod.getText() + "','" + TBod.getText() + "','" + TTss.getText() + "','" + TAof.getText() + "','" + TEpm.getText() + "')";
        String InsertToAdd = InsertFields + InsertValues;


        try {
            //Creating a statement that connects to database and update the database
            /*Statement statement = connectDB.createStatement();
            statement.executeUpdate(InsertToAdd);

            // if new data is inserted into the database , then msg will display
            addnewMessageLabel.setText("Added Successfully!");*/

            newTech = new Tech(String.valueOf(TModel.getText()), Double.parseDouble(TTss.getText()), Double.parseDouble(TCod.getText()), Double.parseDouble(TBod.getText()), Double.parseDouble(TAof.getText()), Double.parseDouble(TEpm.getText()));
            menu.add(Integer.parseInt(TStage.getText()), newTech);
            menu.save();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    protected void BackButtonOnAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu-View.fxml"));
            Stage stage = (Stage) BackButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
