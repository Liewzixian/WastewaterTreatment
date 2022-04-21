package com.example.demo1;

import com.example.demo1.dataclasses.Tech;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import static com.example.demo1.LoginController.menu;

public class addNewModel implements Initializable {
    Tech newTech;
    ObservableList<String> StageName = FXCollections.observableArrayList("PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE");
    NumTest numTest = new NumTest();
    boolean validation = true;

    @FXML
    private ComboBox<String> TStage;
    @FXML
    private Label CODSlider;
    @FXML
    private Label BODSlider;
    @FXML
    private Label TSSSlider;
    @FXML
    private TextField TModel;
    @FXML
    private Slider TCod;
    @FXML
    private Slider TBod;
    @FXML
    private Slider TTss;
    @FXML
    private TextField TAof;
    @FXML
    private TextField TEpm;
    @FXML
    private TextField TCost;
    @FXML
    private Label addNewMessageLabel;

    @FXML
    protected void addButtonOnAction() throws IOException {
        validation= TStage.getValue() != null;

        if(TModel.getText().isEmpty()){
            TModel.setText("Please Enter The Model");
            validation = false;
        }

        if(TAof.getText().isEmpty()){
            TAof.setText("Please Enter Area");
            validation = false;
        }
        else if(!numTest.isDouble(TAof.getText())){
            TAof.setText("Invalid Value");
            validation = false;
        }

        if(TCost.getText().isEmpty()){
            TCost.setText("Please Enter Cost");
            validation = false;
        }
        else if(!numTest.isDouble(TCost.getText())){
            TCost.setText("Invalid Value");
            validation = false;
        }

        if(TEpm.getText().isEmpty()){
            TEpm.setText("Please Enter Energy");
            validation = false;
        }
        else if(!numTest.isDouble(TEpm.getText())){
            TEpm.setText("Invalid Value");
            validation = false;
        }

        if(validation) {

            SoundEffect sound = new SoundEffect();
            sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");

            newTech = new Tech(String.valueOf(TStage.getValue()), String.valueOf(TModel.getText()), Double.parseDouble(String.valueOf(TTss.getValue())), Double.parseDouble(String.valueOf(TCod.getValue())), Double.parseDouble(String.valueOf(TBod.getValue())), Double.parseDouble(TAof.getText()), Double.parseDouble(TEpm.getText()), Double.parseDouble(TCost.getText()));
            menu.add(String.valueOf(TStage.getValue()), newTech);
            menu.save();
            menu.sharedData.reloadData();
            addNewMessageLabel.setText("Model Addition Successful!");
            sound.playSound("src/main/resources/com/SoundEffect/short-success.wav");
        }
        else{
            addNewMessageLabel.setText("Model Addition Failed!");
            SoundEffect sound = new SoundEffect();
            sound.playSound("src/main/resources/com/SoundEffect/error.wav");
        }
    }

    @FXML
    protected void BackButtonOnAction() {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
        Scene scene;
        scene = LoginController.LoginScene;
        Login.window.setScene(scene);
        SetSceneOnCentral(Login.window);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resources){
        TStage.setItems(StageName);
        DecimalFormat df = new DecimalFormat("#.##");
        CODSlider.setText("0%");
        BODSlider.setText("0%");
        TSSSlider.setText("0%");

        TCod.valueProperty().addListener((observable,oldNumber,newNumber)-> CODSlider.setText(df.format(newNumber.doubleValue()*100) + "%"));

        TBod.valueProperty().addListener((observable,oldNumber,newNumber)-> BODSlider.setText(df.format(newNumber.doubleValue()*100) + "%"));

        TTss.valueProperty().addListener((observable,oldNumber,newNumber)-> TSSSlider.setText(df.format(newNumber.doubleValue()*100) + "%"));
    }

    @FXML
    protected void SetSceneOnCentral(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    @FXML
    protected void AreaTextFieldClickOnAction(){
      TAof.setText("");
    }

    @FXML
    protected void EnergyTextFieldClickOnAction(){
      TEpm.setText("");
    }

    @FXML
    protected void CostTextFieldClickOnAction(){
      TCost.setText("");
    }

    @FXML
    protected void ModelTextFieldClickOnAction(){
        TModel.setText("");
    }

    }
