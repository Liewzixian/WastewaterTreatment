package com.example.demo1;

import com.example.demo1.dataclasses.Tech;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private Button BackButton;
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
        validation = true;

        if(TModel.getText().isEmpty()){
            TModel.setText("Please Enter The Model");
            validation = false;
        }

        if(TAof.getText().isEmpty()){
            TAof.setText("Please Enter Area");
        }
        else if(!numTest.isDouble(TAof.getText())){
            TAof.setText("Invalid Value");
            validation = false;
        }

        if(TCost.getText().isEmpty()){
            TCost.setText("Please Enter Cost");
        }
        else if(!numTest.isDouble(TCost.getText())){
            TCost.setText("Invalid Value");
            validation = false;
        }

        if(TEpm.getText().isEmpty()){
            TEpm.setText("Please Enter Energy");
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
        }
        else{
            addNewMessageLabel.setText("Model Addition Failed!");
        }
    }

    @FXML
    protected void BackButtonOnAction() {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu-View.fxml"));
            Stage stage = (Stage) BackButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
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
    }
