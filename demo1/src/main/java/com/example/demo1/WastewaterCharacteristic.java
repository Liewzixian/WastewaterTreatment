package com.example.demo1;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class WastewaterCharacteristic {

    ObservableList <String> StandardsList = FXCollections.observableArrayList("Standard A","Standard B");

    @FXML
    private Button EnterButton;

    @FXML
    private Button BackButton;

    @FXML
    protected void BackButtonOnAction(){
    }

    @FXML
    protected void EnterButtonOnAction(){
    }

    @FXML
    private TextField TCod;

    @FXML
    private TextField TBod;

    @FXML
    private TextField TTss;

    @FXML
    private ComboBox Standard;

    @FXML
    private void initialize() {
        Standard.setItems(StandardsList);
    }
}
