package com.example.demo1;

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;


public class LoginController {
    static Menu menu;

    static Scene LoginScene = null;

    @FXML
    private Button cancelButton;

    @FXML
    private Label LoginMessageLabel;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private TextField UsernameTextField;

    private HashMap<String,String> user;

    @FXML
    private void initialize() throws FileNotFoundException {

        user = new HashMap<>();
        String[] hold = new String[2]; //make array of strings with 5 elements

        File file = new File("src/main/resources/com/Treatment/users.txt"); //load location
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()){ //tokenize string using , and stop when list is empty
            StringTokenizer st = new StringTokenizer(sc.nextLine(),",");

            while (st.hasMoreTokens()) { //temporarily save info of treatment in each loop

                for (int count = 0; count < 2; count++)
                    hold[count] = st.nextToken();
                user.put(hold[0],hold[1]);
            }
        }
        sc.close();
    }

    @FXML
    protected void loginButtonOnAction() throws FileNotFoundException {
        // if username and password is filled up then go to validateLogin()
        if(validateLogin()){
            //validateLogin();
            SoundEffect sound = new SoundEffect();
            sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
            LoginMessageLabel.setText("Successfully login!");
            nextScene(); // go to next scene when login successfully
            menu = new Menu("src/main/resources/com/Treatment/output.txt");
            menu.load();

        }
        else{
            SoundEffect sound = new SoundEffect();
            sound.playSound("src/main/resources/com/SoundEffect/error.wav");
            // if username and password is empty then msg will display
            LoginMessageLabel.setText("Please enter username and password");
        }
    }

    @FXML
    protected void cancelButtonOnAction(){
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
        //when cancel button is click then window will be close
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    protected boolean validateLogin(){
        String username = UsernameTextField.getText();
        String password = enterPasswordField.getText();

        return user.containsKey(username) && user.get(username).equals(password);
    }

    public void nextScene(){
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("Menu-View.fxml"));
        try {
            LoginScene = new Scene(loader.load(), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.splashStage.hide();
        Login.window.setScene(LoginScene);
        Login.window.show();
        SetSceneOnCentral(Login.window);
    }

    @FXML
    protected void SetSceneOnCentral(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    @FXML
    protected void SignUpOnClicked(){
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("SignUp-View.fxml"));
        Scene Scene = null;
        try {
            Scene = new Scene(loader.load(), 545, 365);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.splashStage.setScene(Scene);
        SetSceneOnCentral(Login.splashStage);
    }
}
