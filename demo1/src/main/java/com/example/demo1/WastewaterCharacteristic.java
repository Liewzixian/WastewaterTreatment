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

/**
 * This class will run the wastewater characteristic
 */
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

    /**
     * This method initialize the program data for wastewater characteristic
     */
    @FXML
    private void initialize() {
        Standard.setItems(StandardsList);
        State.setItems(StateList);
        validate = false;
    }
    /**
     * This method is invoked when the back button is being clicked.
     * This method will bring users back to the menu scene.
     */
    @FXML
    protected void BackButtonOnAction(){
        SoundEffect.clicksound();
        Scene scene;
        scene = LoginController.LoginScene;
        Login.window.setScene(scene);
        SetSceneOnCentral(Login.window);
    }
    /**
     * This method validate the wastewater characteristic that user input and save at database while user click enter button
     */
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
     * This method is controlling the state combo box
     */
    @FXML
    protected void StateOnAction(){
        selectedState=State.getValue();
        AreaList=FXCollections.observableArrayList(sharedData.loadStateArea(selectedState));
        Area.setItems(AreaList);
    }
    /**
     * This method is controlling the area combo box
     */
    @FXML
    protected void AreaOnAction(){
        if(Area.getValue()!=null){
            pollutionLevels = sharedData.loadAreaData(selectedState,Area.getValue());
            TCod.setText(String.valueOf(pollutionLevels.getCOD()));
            TBod.setText(String.valueOf(pollutionLevels.getBOD()));
            TTss.setText(String.valueOf(pollutionLevels.getTSS()));
        }
    }
    /**
     * This method will display user the info about the standard by calling out a pop-out dialog box
     */
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
    /**
     * This method will be invoked if users click the TCod text field
     * It will clear the text in TCod text field
     */
    @FXML
    protected void CODTextFieldClickOnAction(){
        TCod.setText("");
    }
    /**
     * This method will be invoked if users click the TBod text field
     * It will clear the text in TBod text field
     */
    @FXML
    protected void BODTextFieldClickOnAction(){
        TBod.setText("");
    }
    /**
     * This method will be invoked if users click the TTss text field
     * It will clear the text in TTss text field
     */
    @FXML
    protected void TSSTextFieldClickOnAction(){
        TTss.setText("");
    }
}
