package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.IOException;

public class SignUpController {
    @FXML
    private Label Alert;

    @FXML
    private TextField username;

    @FXML
    private TextField Password;
    
    @FXML
    protected void BackButtonOnAction() {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("hello-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load(), 545, 375);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.splashStage.setScene(scene);
    }

    @FXML
    protected void CreateButtonOnAction() {
       username.getText();
       Password.getText();
    }
}
