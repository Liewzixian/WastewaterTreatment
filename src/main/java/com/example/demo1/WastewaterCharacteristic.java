package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;


import static com.example.demo1.LoginController.menu;


public class WastewaterCharacteristic {

    ObservableList <String> StandardsList = FXCollections.observableArrayList("Standard A","Standard B");
    Input input = new Input();
    String Tss,Bss,Css,SelectedStandard;

    int [] standard ={1,2};

    @FXML
    private TextField TCod;

    @FXML
    private TextField TBod;

    @FXML
    private TextField TTss;

    @FXML
    private ComboBox Standard;

    @FXML
    private Label StandardAlert;

    @FXML
    private void initialize() {
        Standard.setItems(StandardsList);
    }

    @FXML
    protected void BackButtonOnAction(){
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
        FXMLLoader fxmlLoader = new FXMLLoader(WastewaterCharacteristic.class.getResource("Menu-View.fxml"));
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load(), 585, 400);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
        SetSceneOnCentral(Login.window);
    }

    @FXML
    protected void EnterButtonOnAction() throws FileNotFoundException {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
        Css=TCod.getText();
        TCod.setText(input.getDouble(Css));
        Bss=TBod.getText();
        TBod.setText(input.getDouble(Bss));
        Tss=TTss.getText();
        TTss.setText(input.getDouble(Tss));
        SelectedStandard=(String)Standard.getValue();

        if (Standard.getValue() == null) {
            sound = new SoundEffect();
            sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
            Input.validate = 1;
            StandardAlert.setText("Please Select A Standard");
        }

        if(Input.validate == 0){

            if(Standard.getValue()=="Standard A")
                menu.showAllResults(new Initial(Double.parseDouble(Tss),Double.parseDouble(Css),Double.parseDouble(Bss)),standard[0]);
            else
                menu.showAllResults(new Initial(Double.parseDouble(Tss),Double.parseDouble(Css),Double.parseDouble(Bss)),standard[1]);

            FXMLLoader fxmlLoader = new FXMLLoader(WastewaterCharacteristic.class.getResource("DisplayResultView.fxml"));
            Scene scene = null;

            try {
                scene = new Scene(fxmlLoader.load(), 992, 632);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Login.window.setScene(scene);
            SetSceneOnCentral(Login.window);
        }
        else{
            Input.validate =0;
        }
    }

    @FXML
    protected void SetSceneOnCentral(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
