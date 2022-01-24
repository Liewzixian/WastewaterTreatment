package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ManageModel {
    @FXML
    private Button DeleteButton;

    @FXML
    private Button BackButton;

    @FXML
    private Button ModifyButton;

    @FXML
    private TextField SearchModel;

    @FXML
    protected void deleteButtonOnAction(){
    }

    @FXML
    protected void BackButtonOnAction(){
        FXMLLoader fxmlLoader = new FXMLLoader(ManageModel.class.getResource("Menu-View.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 585, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
    }

    @FXML
    protected void ModifyButtonOnAction(){
    }


}
