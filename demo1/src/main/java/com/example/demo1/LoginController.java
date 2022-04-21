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

/**
 * This class is the controller class of the LOGIN GUI
 */
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

    /**
     * This method set up the login page
     * It loads the program data from the file for further use
     */
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
    /**
     * This method is invoked when the "login" button is clicked
     * It will validate the username and password
     */
    @FXML
    protected void loginButtonOnAction() throws FileNotFoundException {
        SoundEffect.clicksound();
        if(validateLogin()){
            //validateLogin
            SoundEffect.success();
            LoginMessageLabel.setText("Successfully login!");
            nextScene(); // go to next scene when login successfully
            menu = new Menu("src/main/resources/com/Treatment/output.txt");
        }
        else{
            SoundEffect.errorsound();
            // if username and password is empty then msg will display
            LoginMessageLabel.setText("Please enter username and password");
        }
    }
    /**
     * This method is invoked when the "cancel" button is clicked
     * It will close the program
     */
    @FXML
    protected void cancelButtonOnAction(){
        SoundEffect.clicksound();
        //when cancel button is click then window will be close
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    /**
     * This method will perform login validation process
     */
    protected boolean validateLogin(){
        String username = UsernameTextField.getText();
        String password = enterPasswordField.getText();

        return user.containsKey(username) && user.get(username).equals(password);
    }
    /**
     * This method will run the menu GUI
     */
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
    /**
     * This method takes the stage and set the stage to the central based on user's scene size
     * @param stage the stage the users want to set at the central
     */
    @FXML
    protected void SetSceneOnCentral(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    /**
     * This method is invoked when the "Sign up" label is clicked
     * It will lead the user to the sign-up page
     */
    @FXML
    protected void SignUpOnClicked(){
        SoundEffect.clicksound();
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
