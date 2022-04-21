package com.example.demo1;

import com.example.demo1.dataclasses.PollutionLevels;
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

import java.io.IOException;
import java.util.Objects;

import static com.example.demo1.LoginController.menu;

public class WastewaterCharacteristic {
    SharedData sharedData = menu.sharedData;
    ObservableList <String> StandardsList = FXCollections.observableArrayList("Standard A","Standard B");
    ObservableList<String> StateList=FXCollections.observableArrayList(sharedData.getStates());
    ObservableList<String> AreaList=FXCollections.observableArrayList();
    static Stage stage = new Stage();

    PollutionLevels pollutionLevels;
    String selectedState;

    NumTest numTest = new NumTest();
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

    private boolean validate;

    @FXML
    private void initialize() {
        Standard.setItems(StandardsList);
        State.setItems(StateList);
        validate = false;
    }

    @FXML
    protected void BackButtonOnAction(){
        SoundEffect.clicksound();
        Scene scene;
        scene = LoginController.LoginScene;
        Login.window.setScene(scene);
        SetSceneOnCentral(Login.window);
    }

    @FXML
    protected void EnterButtonOnAction() {

        SoundEffect.clicksound();

        Css=TCod.getText();
        TCod.setText(numTest.getDouble(Css));
        Bss=TBod.getText();
        TBod.setText(numTest.getDouble(Bss));
        Tss=TTss.getText();
        TTss.setText(numTest.getDouble(Tss));
        SelectedStandard = Standard.getValue();

        if (Standard.getValue() == null) {
            SoundEffect.errorsound();
            validate = false;
            StandardAlert.setText("Please Select A Standard");
        }
        else
            validate = true;

        if(validate && numTest.isDouble(Css) && numTest.isDouble(Bss) && numTest.isDouble(Tss)){
            if(Objects.equals(Standard.getValue(), "Standard A"))
                menu.addResultsToTable(new PollutionLevels(Double.parseDouble(Tss),Double.parseDouble(Css),Double.parseDouble(Bss)),0);
            else
                menu.addResultsToTable(new PollutionLevels(Double.parseDouble(Tss),Double.parseDouble(Css),Double.parseDouble(Bss)),1);

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
            pollutionLevels = sharedData.loadAreaData(selectedState,Area.getValue());
            TCod.setText(String.valueOf(pollutionLevels.getCOD()));
            TBod.setText(String.valueOf(pollutionLevels.getBOD()));
            TTss.setText(String.valueOf(pollutionLevels.getTSS()));
        }
    }

    @FXML
    protected void ClickInfoOnAction(){
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(WastewaterCharacteristic.class.getResource("StandardInfo-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 380, 180);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();
        SetSceneOnCentral(stage);
    }
    @FXML
    protected void CODTextFieldClickOnAction(){
        TCod.setText("");
    }

    @FXML
    protected void BODTextFieldClickOnAction(){
        TBod.setText("");
    }

    @FXML
    protected void TSSTextFieldClickOnAction(){
        TTss.setText("");
    }
}
