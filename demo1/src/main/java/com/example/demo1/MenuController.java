package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;

import java.io.IOException;

public class MenuController {

    FXMLLoader fxmlLoader = new FXMLLoader(MenuController.class.getResource("ManageModel-view.fxml"));
    FXMLLoader fxmlLoader1 = new FXMLLoader(MenuController.class.getResource("addNewModel-view.fxml"));
    FXMLLoader fxmlLoader2 = new FXMLLoader(MenuController.class.getResource("WaterChar-view.fxml"));
    FXMLLoader fxmlLoader3 = new FXMLLoader(MenuController.class.getResource("Selection-view.fxml"));

    @FXML
    protected void addButtonOnAction() {
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader1.load(), 600, 380);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
        SetSceneOnCentral();
    }

    @FXML
    protected void manageButtonOnAction() {
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1039, 560);
            //menu.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
        SetSceneOnCentral();
    }
    @FXML
    protected void afterMenuButtonOnAction() {
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader2.load(), 595, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
        SetSceneOnCentral();
    }

    @FXML
    protected void selectButtonOnAction() {
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader3.load(), 739, 483);
            //menu.save();
        } catch (IOException e) {
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


}
