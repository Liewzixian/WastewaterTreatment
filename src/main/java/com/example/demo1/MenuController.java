package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Screen;
import java.io.IOException;

public class MenuController {
    @FXML
    private RadioButton BGM;
     SoundEffect soundEffect=new SoundEffect();

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

    @FXML
    protected void SetSceneOnCentral(){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        Login.window.setX((primScreenBounds.getWidth() - Login.window.getWidth()) / 2);
        Login.window.setY((primScreenBounds.getHeight() - Login.window.getHeight()) / 2);
    }

    @FXML
    protected void BGMButtonOnAction(){
        if(BGM.isSelected()){
            soundEffect.playBGM("src/main/resources/com/SoundEffect/BGM.wav");
        }else{
            soundEffect.BGMClip.stop();
        }
    }

}
