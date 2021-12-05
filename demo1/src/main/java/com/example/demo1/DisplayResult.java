package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class DisplayResult {
    ObservableList<String> Preference = FXCollections.observableArrayList("Best Overall","Cost Effectiveness ");

    @FXML
    private Button BackButton;

    @FXML
    protected void BackButtonOnAction(){
    }
    @FXML
    private ComboBox SelectPreference;

    @FXML
    private void initialize() {
        SelectPreference.setItems(Preference);
    }
}
