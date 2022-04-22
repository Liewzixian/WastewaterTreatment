package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Screen;
import java.io.IOException;

/**
 * This class is the controller class of menu GUI
 */
public class MenuController {
    @FXML
    private RadioButton BGM;
    SoundEffect soundEffect = new SoundEffect();

    /**
     * This method is invoked when the "Add New Model" button is being clicked.
     * It will lead the user to add new model GUI
     */
    @FXML
    protected void addButtonOnAction() {
        SoundEffect.clicksound();
        Scene scene = null;
        try {
            scene = new Scene(new FXMLLoader(MenuController.class.getResource("addNewModel-view.fxml")).load(), 600, 380);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
        SetSceneOnCentral();
    }
    /**
     * This method is invoked when the "Manage Model" button is being clicked.
     * It will lead the user to manage model GUI
     */

    @FXML
    protected void manageButtonOnAction() {
        SoundEffect.clicksound();
        Scene scene = null;
        try {
            scene = new Scene(new FXMLLoader(MenuController.class.getResource("ManageModel-view.fxml")).load(), 1239, 560);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
        SetSceneOnCentral();
    }
    /**
     * This method is invoked when the "Continue" button is being clicked.
     * It will lead the user to Wastewater Characteristic GUI
     */
    @FXML
    protected void afterMenuButtonOnAction() {
        SoundEffect.clicksound();
        Scene scene = null;
        try {
            scene = new Scene(new FXMLLoader(MenuController.class.getResource("WaterChar-view.fxml")).load(), 600, 425);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
        SetSceneOnCentral();
    }
    /**
     * This method is invoked when the "Model Selection" button is being clicked.
     * It will lead the user to model selection GUI
     */
    @FXML
    protected void selectButtonOnAction() {
        SoundEffect.clicksound();
        Scene scene = null;
        try {
            scene = new Scene(new FXMLLoader(MenuController.class.getResource("Selection-view.fxml")).load(), 739, 483);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
        SetSceneOnCentral();
    }
    /**
     * This method takes the stage and set the stage to the central based on user's scene size
     */
    @FXML
    protected void SetSceneOnCentral(){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        Login.window.setX((primScreenBounds.getWidth() - Login.window.getWidth()) / 2);
        Login.window.setY((primScreenBounds.getHeight() - Login.window.getHeight()) / 2);
    }
    /**
     * This method is invoked when the BGM radio button is being clicked.
     * It will play the bgm and stop
     */
    @FXML
    protected void BGMButtonOnAction(){
        if(BGM.isSelected()){
            soundEffect.playBGM("src/main/resources/com/SoundEffect/BGM.wav");
        }else{
            soundEffect.BGMClip.stop();
        }
    }

}
