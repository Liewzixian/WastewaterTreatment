package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.EventObject;


public class LoginController {
    static Menu menu;

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
    protected void loginButtonOnAction(ActionEvent event) throws FileNotFoundException {
        // if username and password is filled up then go to validateLogin()
        if(!UsernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()){
            //validateLogin();
            LoginMessageLabel.setText("Successfully login!");
            nextScene(); // go to next scene when login successfully
            menu = new Menu("src/main/resources/com/Treatment/output.txt");
            menu.load();

        }else{
            // if username and password is empty then msg will display
            LoginMessageLabel.setText("Please enter username and password");
        }
    }

    @FXML
    protected void cancelButtonOnAction(ActionEvent event){
        //when cancel button is click then window will be close
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void validateLogin() {
        // connecting to database
        ConnectionDB connectNow = new ConnectionDB();
        Connection connectDB = connectNow.main();

        // making statement to check if there is existing data or not
        String verifyLogin = "SELECT count(1) FROM login WHERE username = '" + UsernameTextField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";

        try {
            //create statement that is connected to database
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                // if result is 1 means data exist in database
                if (queryResult.getInt(1) == 1) {
                    LoginMessageLabel.setText("Successfully login!");
                    nextScene(); // go to next scene when login successfully
                    menu = new Menu("src/main/resources/com/Treatment/output.txt");
                    menu.load();
                } else {
                    LoginMessageLabel.setText("Invalid login!");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();

        }

    }

    // go to next scene when user login successfully AND login button is clicked
    public void nextScene(){
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("Menu-View.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load(), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
    }
}
