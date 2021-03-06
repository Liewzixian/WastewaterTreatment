package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class will run the sign-up GUI
 */
public class SignUpController {
    @FXML
    private Label Alert;
    @FXML
    private TextField username;
    @FXML
    private TextField Password;
    private HashMap<String,String> user;
    private final NumTest numTest = new NumTest();

    /**
     * This method initialize the program data for sign up controller
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
     * This method is invoked when the back button is being clicked.
     * This method will bring users back to the login scene.
     */
    @FXML
    protected void BackButtonOnAction() {
        SoundEffect.clicksound();

        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("hello-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load(), 545, 375);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.splashStage.setScene(scene);
    }
    /**
     * This method is invoked when the "create" button is being clicked.
     * It will validate the username and password for signing up
     */
    @FXML
    protected void CreateButtonOnAction() throws IOException {
        SoundEffect.clicksound();

        String usernameText = username.getText();
        String passwordText = Password.getText();

        boolean validate = numTest.isString(usernameText) && numTest.isString(passwordText);

        username.setText(numTest.getString(usernameText));
        Password.setText(numTest.getString(passwordText));

        if(!user.containsKey(usernameText) && validate){
            user.put(usernameText,passwordText);
            Alert.setText("New User Registered. Welcome " + usernameText);
            saveUserdata();
            SoundEffect.clicksound();
            SoundEffect.success();
        }
        else {
            Alert.setText("Invalid username or password");
            SoundEffect.errorsound();
        }
    }
    /**
     * This method will save the new validated username and password to the database
     */
    public void saveUserdata() throws IOException {
        PrintWriter writer = new PrintWriter("src/main/resources/com/Treatment/users.txt", StandardCharsets.UTF_8); //save location (can add code to change location)

        for(Map.Entry<String, String> users : user.entrySet())
            writer.format("%s,%s\n",users.getKey(),users.getValue());

        writer.close(); //close writer
    }

    /**
     * This method will be invoked if users click the username text field
     * It will clear the text in username text field
     */
    @FXML
    public void UsernameFieldClickOnAction(){
        username.setText("");
    }
    /**
     * This method will be invoked if users click the password text field
     * It will clear the text in username text field
     */
    @FXML
    public void PasswordFieldClickOnAction(){
        Password.setText("");
    }
}