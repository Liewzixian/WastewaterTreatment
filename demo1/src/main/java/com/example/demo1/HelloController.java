package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label LoginMessageLabel;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private TextField UsernameTextField;

    @FXML
    protected void loginButtonOnAction() {

        if(!UsernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()){
            validateLogin();
        }else{
            LoginMessageLabel.setText("Please enter username and password");
        }
    }

    @FXML
    protected void cancelButtonOnAction(){
        Stage stage =(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void validateLogin(){

    }
}