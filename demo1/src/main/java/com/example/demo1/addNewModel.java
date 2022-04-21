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

/**
 * This class enable users to add new model
 */
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

    /**
     * This method is invoked when the add button is being clicked.
     * All the data that user inserted will be validated when clicking this "add"button
     * Users will get an alert message which will indicate whether if the model added successfully
     */
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

            SoundEffect.clicksound();
            newTech = new Tech(String.valueOf(TStage.getValue()), String.valueOf(TModel.getText()), Double.parseDouble(String.valueOf(TTss.getValue())), Double.parseDouble(String.valueOf(TCod.getValue())), Double.parseDouble(String.valueOf(TBod.getValue())), Double.parseDouble(TAof.getText()), Double.parseDouble(TEpm.getText()), Double.parseDouble(TCost.getText()));
            menu.sharedData.getOriginalList().get(TStage.getValue()).put(newTech.getName(),newTech);
            menu.sharedData.saveData();
            menu.sharedData.reloadData();
            addNewMessageLabel.setText("Model Addition Successful!");
            SoundEffect.clicksound();
            SoundEffect.success();
        }
        else{
            addNewMessageLabel.setText("Model Addition Failed!");
            SoundEffect.clicksound();
            SoundEffect.errorsound();
        }
    }
    /**
     * This method is invoked when the back button is being clicked.
     * This method will bring users back to the menu scene.
     */
    @FXML
    protected void BackButtonOnAction() {
        SoundEffect.clicksound();
        Scene scene;
        scene = LoginController.LoginScene;
        Login.window.setScene(scene);
        SetSceneOnCentral(Login.window);
    }
    /**
     * This method is invoked when setting up the "addNewModel" interface
     * @param url
     * @param resources
     */
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
     * This method will clear the text in the Area of footprint text field everytime the user click the text field
     */
    @FXML
    protected void AreaTextFieldClickOnAction(){
      TAof.setText("");
    }
    /**
     * This method will clear the text in the Energy per meter cube text field everytime the user click the text field
     */
    @FXML
    protected void EnergyTextFieldClickOnAction(){
      TEpm.setText("");
    }
    /**
     * This method will clear the text in the Cost text field everytime the user click the text field
     */
    @FXML
    protected void CostTextFieldClickOnAction(){
      TCost.setText("");
    }
    /**
     * This method will clear the text in the Model text field everytime the user click the text field
     */
    @FXML
    protected void ModelTextFieldClickOnAction(){
        TModel.setText("");
    }

    }
