package com.example.demo1;

import com.example.demo1.dataclasses.Initial;
import com.example.demo1.dataclasses.Location;
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
import java.util.ArrayList;
import java.util.Objects;

import static com.example.demo1.LoginController.menu;

public class WastewaterCharacteristic {
    SharedData sharedData = menu.sharedData;
    ObservableList <String> StandardsList = FXCollections.observableArrayList("Standard A","Standard B");
    ObservableList<String> StateList=FXCollections.observableArrayList(sharedData.getStates());
    ObservableList<String> AreaList=FXCollections.observableArrayList();
    Location locations;
    String selectedState;

    ArrayList<String> AreaArrayList;
    Input input = new Input();
    String Tss,Bss,Css,SelectedStandard;

    @FXML
    private TextField TCod;
    @FXML
    private TextField TBod;
    @FXML
    private TextField TTss;
    @FXML
    private ComboBox<String> Standard;
    @FXML
    private ComboBox<String> State;
    @FXML
    private ComboBox<String> Area;
    @FXML
    private Label StandardAlert;

    private String clickSound;

    @FXML
    private void initialize() {
        Standard.setItems(StandardsList);
        State.setItems(StateList);
        clickSound = "src/main/resources/com/SoundEffect/clicksound.wav";
    }

    @FXML
    protected void BackButtonOnAction(){
        SoundEffect sound = new SoundEffect();
        sound.playSound(clickSound);
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
    protected void EnterButtonOnAction() {
        SoundEffect sound = new SoundEffect();
        sound.playSound(clickSound);
        Css=TCod.getText();
        TCod.setText(input.getDouble(Css));
        Bss=TBod.getText();
        TBod.setText(input.getDouble(Bss));
        Tss=TTss.getText();
        TTss.setText(input.getDouble(Tss));
        SelectedStandard = Standard.getValue();

        if (Standard.getValue() == null) {
            sound = new SoundEffect();
            sound.playSound(clickSound);
            Input.validate = 1;
            StandardAlert.setText("Please Select A Standard");
        }

        if(Input.validate == 0){

            if(Objects.equals(Standard.getValue(), "Standard A"))
                menu.showAllResults(new Initial(Double.parseDouble(Tss),Double.parseDouble(Css),Double.parseDouble(Bss)),0);
            else
                menu.showAllResults(new Initial(Double.parseDouble(Tss),Double.parseDouble(Css),Double.parseDouble(Bss)),1);

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
            Input.validate = 0;
        }
    }

    @FXML
    protected void SetSceneOnCentral(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    @FXML
    protected void StateOnAction(){
        selectedState=State.getValue();
        AreaList=FXCollections.observableArrayList(sharedData.loadStateArea(selectedState));
        Area.setItems(AreaList);
    }

    @FXML
    protected void AreaOnAction(){
        if(Area.getValue()!=null){
            locations=sharedData.loadAreaData(selectedState,Area.getValue());
            TCod.setText(String.valueOf(locations.getCOD()));
            TBod.setText(String.valueOf(locations.getBOD()));
            TTss.setText(String.valueOf(locations.getTSS()));
        }
    }
}
