package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


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
            SoundEffect sound = new SoundEffect();
            sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
            LoginMessageLabel.setText("Successfully login!");
            nextScene(); // go to next scene when login successfully
            menu = new Menu("src/main/resources/com/Treatment/output.txt");
            menu.load();

        }else{
            SoundEffect sound = new SoundEffect();
            sound.playSound("src/main/resources/com/SoundEffect/error.wav");
            // if username and password is empty then msg will display
            LoginMessageLabel.setText("Please enter username and password");
        }
    }

    @FXML
    protected void cancelButtonOnAction(ActionEvent event){
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
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
                    SoundEffect sound = new SoundEffect();
                    sound.playSound("src/main/resources/com/SoundEffect/success-chime.wav");
                    LoginMessageLabel.setText("Successfully login!");
                    nextScene(); // go to next scene when login successfully
                    menu = new Menu("src/main/resources/com/Treatment/output.txt");
                    menu.load();
                } else {
                    SoundEffect sound = new SoundEffect();
                    sound.playSound("src/main/resources/com/SoundEffect/error.wav");
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
        Login.splashStage.hide();
        Login.window.setScene(scene);
        Login.window.show();
        SetSceneOnCentral(Login.window);
    }

    @FXML
    protected void SetSceneOnCentral(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
